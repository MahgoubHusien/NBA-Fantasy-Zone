import os
import pandas as pd
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueGameLog, ScoreboardV2
from datetime import datetime, timedelta
import time
from requests.exceptions import ReadTimeout

# Load environment variables from .env file
load_dotenv()

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

# List to store problematic rows for debugging
problematic_rows = []

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
                print(f"Values: {values}")
            except Exception as e:
                print(f"Error with row {index} in line_score_df: {line}")
                print(f"Exception message: {e}")
                problematic_rows.append(line)
                continue

    except Exception as e:
        print(f"Error fetching line score data: {e}")

# Write problematic rows to a CSV file
problematic_rows_df = pd.DataFrame(problematic_rows)
problematic_rows_df.to_csv('problematic_line_scores.csv', index=False)

print("Problematic rows written to problematic_line_scores.csv")
