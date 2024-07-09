import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueGameLog, ScoreboardV2
from datetime import datetime, timedelta
import time
from requests.exceptions import ReadTimeout

# Load environment variables from .env file
load_dotenv()

# Read connection parameters from environment variables
conn_params = {
    'dbname': os.getenv('DB_NAME'),
    'user': os.getenv('DB_USER'),
    'password': os.getenv('DB_PASSWORD'),
    'host': os.getenv('DB_HOST'),
    'port': int(os.getenv('DB_PORT'))
}

# Establish a connection to the database
conn = psycopg2.connect(**conn_params)
cursor = conn.cursor()

def fetch_scoreboard(date):
    retries = 5
    for i in range(retries):
        try:
            scoreboard = ScoreboardV2(game_date=date)
            return scoreboard
        except ReadTimeout:
            if i < retries - 1:
                print(f"Timeout error occurred. Retrying {retries - i - 1} more times...")
                time.sleep(5)  # wait before retrying
            else:
                raise

def fetch_last_20_game_dates(season='2023-24', season_type='Regular Season'):
    game_log = LeagueGameLog(season=season, season_type_all_star=season_type)
    games_df = game_log.get_data_frames()[0]
    last_20_games_df = games_df.tail(20)
    game_dates = last_20_games_df['GAME_DATE'].unique()
    return game_dates

# Fetch the last 20 game dates of the season
game_dates = fetch_last_20_game_dates()

# Fetch scoreboard data for each game date
for game_date in game_dates:
    print(f"Fetching data for date: {game_date}")
    scoreboard = fetch_scoreboard(game_date)

    # Get the different data frames
    game_header_df = scoreboard.game_header.get_data_frame()
    line_score_df = scoreboard.line_score.get_data_frame()
    east_conf_standings_df = scoreboard.east_conf_standings_by_day.get_data_frame()
    west_conf_standings_df = scoreboard.west_conf_standings_by_day.get_data_frame()

    # Print the number of rows fetched for each data frame
    print(f"Date: {game_date}")
    print("Number of game headers fetched:", len(game_header_df))
    print("Number of line scores fetched:", len(line_score_df))
    print("Number of eastern conference standings fetched:", len(east_conf_standings_df))
    print("Number of western conference standings fetched:", len(west_conf_standings_df))

    # Print columns to verify
    print("Game Header columns:", game_header_df.columns)
    print("Line Score columns:", line_score_df.columns)
    print("East Conference Standings columns:", east_conf_standings_df.columns)
    print("West Conference Standings columns:", west_conf_standings_df.columns)

    try:
        # Insert game header data
        for index, game in game_header_df.iterrows():
            cursor.execute("""
                INSERT INTO game_header (game_id, game_date_est, game_sequence, game_status_id, game_status_text, home_team_id, visitor_team_id, season, live_period, live_pc_time, natl_tv_broadcaster_abbreviation, home_tv_broadcaster_abbreviation, away_tv_broadcaster_abbreviation, live_period_time_bcast, arena_name, wh_status, wnba_commissioner_flag)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (game_id) DO NOTHING
            """, (
                game['GAME_ID'], game['GAME_DATE_EST'], game['GAME_SEQUENCE'], game['GAME_STATUS_ID'], game['GAME_STATUS_TEXT'],
                game['HOME_TEAM_ID'], game['VISITOR_TEAM_ID'], game['SEASON'], game['LIVE_PERIOD'], game['LIVE_PC_TIME'],
                game['NATL_TV_BROADCASTER_ABBREVIATION'], game['HOME_TV_BROADCASTER_ABBREVIATION'], game['AWAY_TV_BROADCASTER_ABBREVIATION'],
                game['LIVE_PERIOD_TIME_BCAST'], game['ARENA_NAME'], game['WH_STATUS'], game.get('WNBA_COMMISSIONER_FLAG', None)
            ))
        conn.commit()
    except Exception as e:
        print(f"Error inserting game header data: {e}")
        conn.rollback()

    try:
        # Insert line score data with additional debugging and validation
        for index, line in line_score_df.iterrows():
            try:
                # Ensure all values are present and correct
                values = (
                    line['GAME_ID'], line['TEAM_ID'], line['TEAM_ABBREVIATION'], line['TEAM_CITY_NAME'], line['TEAM_NAME'],
                    line['PTS_QTR1'], line['PTS_QTR2'], line['PTS_QTR3'], line['PTS_QTR4'], line.get('PTS_OT1', 0), line.get('PTS_OT2', 0),
                    line.get('PTS_OT3', 0), line.get('PTS_OT4', 0), line.get('PTS_OT5', 0), line.get('PTS_OT6', 0), line.get('PTS_OT7', 0),
                    line.get('PTS_OT8', 0), line.get('PTS_OT9', 0), line.get('PTS_OT10', 0), line['PTS'], round(line['FG_PCT'], 2), round(line['FT_PCT'], 2), round(line['FG3_PCT'], 2), line['AST'], line['REB'], line['TOV']
                )
                print(f"Inserting line score data: {values}")
                cursor.execute("""
                    INSERT INTO line_score (game_id, team_id, team_abbreviation, team_city_name, team_name, pts_qtr1, pts_qtr2, pts_qtr3, pts_qtr4, pts_ot1, pts_ot2, pts_ot3, pts_ot4, pts_ot5, pts_ot6, pts_ot7, pts_ot8, pts_ot9, pts_ot10, pts, fg_pct, ft_pct, fg3_pct, ast, reb, tov)
                    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                    ON CONFLICT (game_id, team_id) DO NOTHING
                """, values)
            except Exception as e:
                print(f"Error with row {index} in line_score_df: {line}")
                print(f"Exception message: {e}")
                conn.rollback()
                continue
        conn.commit()
    except Exception as e:
        print(f"Error inserting line score data: {e}")
        conn.rollback()

    try:
        # Insert eastern conference standings data
        for index, standing in east_conf_standings_df.iterrows():
            cursor.execute("""
                INSERT INTO eastern_conf_standings (team_id, league_id, standings_date, conference, team, g, w, l, w_pct, home_record, road_record)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (team_id) DO NOTHING
            """, (
                standing['TEAM_ID'], standing['LEAGUE_ID'], game_date, standing['CONFERENCE'], standing['TEAM'],
                standing['G'], standing['W'], standing['L'], round(standing['W_PCT'], 2), standing['HOME_RECORD'], standing['ROAD_RECORD']
            ))
        conn.commit()
    except Exception as e:
        print(f"Error inserting eastern conference standings data: {e}")
        conn.rollback()

    try:
        # Insert western conference standings data
        for index, standing in west_conf_standings_df.iterrows():
            cursor.execute("""
                INSERT INTO western_conf_standings (team_id, league_id, standings_date, conference, team, g, w, l, w_pct, home_record, road_record)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (team_id) DO NOTHING
            """, (
                standing['TEAM_ID'], standing['LEAGUE_ID'], game_date, standing['CONFERENCE'], standing['TEAM'],
                standing['G'], standing['W'], standing['L'], round(standing['W_PCT'], 2), standing['HOME_RECORD'], standing['ROAD_RECORD']
            ))
        conn.commit()
    except Exception as e:
        print(f"Error inserting western conference standings data: {e}")
        conn.rollback()

# Close the connection
cursor.close()
conn.close()

print("Data inserted into PostgreSQL database successfully.")
