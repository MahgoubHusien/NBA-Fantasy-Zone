import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueGameLog, BoxScoreTraditionalV3, ScoreboardV2
from datetime import datetime
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

def fetch_games(season='2023-24', season_type='Regular Season'):
    retries = 5
    for i in range(retries):
        try:
            game_log = LeagueGameLog(season=season, season_type_all_star=season_type)
            games_df = game_log.get_data_frames()[0]
            return games_df
        except ReadTimeout:
            if i < retries - 1:
                print(f"Timeout error occurred. Retrying {retries - i - 1} more times...")
                time.sleep(5)  # wait before retrying
            else:
                raise

def fetch_player_stats(game_id):
    retries = 5
    for i in range(retries):
        try:
            boxscore_traditional = BoxScoreTraditionalV3(game_id=game_id)
            player_stats = boxscore_traditional.get_data_frames()[0]
            return player_stats
        except ReadTimeout:
            if i < retries - i - 1:
                print(f"Timeout error occurred. Retrying {retries - i - 1} more times...")
                time.sleep(5)  # wait before retrying
            else:
                raise

def fetch_future_matchups(date):
    retries = 5
    for i in range(retries):
        try:
            scoreboard = ScoreboardV2(game_date=date)
            games = scoreboard.get_data_frames()[0]
            return games
        except ReadTimeout:
            if i < retries - i - 1:
                print(f"Timeout error occurred. Retrying {retries - i - 1} more times...")
                time.sleep(5)  # wait before retrying
            else:
                raise

# Convert percentage to string format "xx.xx%"
def format_percentage(value):
    return f"{round(value * 100, 2):.2f}%" if pd.notnull(value) else None

# Fetch games
games_df = fetch_games()

# Get only the last 20 games
games_df = games_df.tail(20)

# Print the number of games fetched
print("Number of games fetched:", len(games_df))

# Print columns to verify
print("Games columns:", games_df.columns)

# Get today's date
today = datetime.today().strftime('%Y-%m-%d')

# Insert data into respective tables
for index, game in games_df.iterrows():
    game_id = game['GAME_ID']
    game_date = game['GAME_DATE']

    # Insert game details
    cursor.execute("""
        INSERT INTO games (game_id, season_id, game_date, home_team_id, home_team_name, home_team_abbreviation, visitor_team_id, visitor_team_name, visitor_team_abbreviation, matchup, wl, min, pts, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, oreb, dreb, reb, ast, stl, blk, tov, pf, plus_minus)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (game_id) DO NOTHING
    """, (
        game_id, game['SEASON_ID'], game_date, game['TEAM_ID'], game['TEAM_NAME'], game['TEAM_ABBREVIATION'], None, None, None,
        game['MATCHUP'], game['WL'], game['MIN'], game['PTS'], game['FGM'], game['FGA'], format_percentage(game['FG_PCT']), game['FG3M'],
        game['FG3A'], format_percentage(game['FG3_PCT']), game['FTM'], game['FTA'], format_percentage(game['FT_PCT']), game['OREB'], game['DREB'], game['REB'],
        game['AST'], game['STL'], game['BLK'], game['TOV'], game['PF'], game['PLUS_MINUS']
    ))
    
    if game_date < today:
        # Fetch and process player stats for past games
        player_stats = fetch_player_stats(game_id)
        
        # Print columns to verify
        print("Player stats columns:", player_stats.columns)
        
        # Insert player stats
        for _, player in player_stats.iterrows():
            cursor.execute("""
                INSERT INTO player_box_stats (game_id, player_id, player_name, team_id, team_abbreviation, min, pts, reb, ast, stl, blk, tov, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (game_id, player_id) DO NOTHING
            """, (
                game_id, player['personId'], f"{player['firstName']} {player['familyName']}", player['teamId'], player['teamTricode'],
                player['minutes'], player['points'], player['reboundsTotal'], player['assists'], player['steals'], player['blocks'], player['turnovers'],
                player['fieldGoalsMade'], player['fieldGoalsAttempted'], format_percentage(player['fieldGoalsPercentage']), player['threePointersMade'], player['threePointersAttempted'], format_percentage(player['threePointersPercentage']),
                player['freeThrowsMade'], player['freeThrowsAttempted'], format_percentage(player['freeThrowsPercentage'])
            ))
    else:
        # Fetch matchups for future games
        future_games = fetch_future_matchups(game_date)
        
        # Print columns to verify
        print("Future games columns:", future_games.columns)
        print("Future games data:", future_games.head())
        
        # Insert future matchups
        for _, future_game in future_games.iterrows():
            cursor.execute("""
                INSERT INTO future_matchups (game_id, game_date, home_team_id, visitor_team_id, home_team_abbreviation, visitor_team_abbreviation, home_team_name, visitor_team_name)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (game_id) DO NOTHING
            """, (
                future_game['GAME_ID'], future_game['GAME_DATE_EST'], future_game['HOME_TEAM_ID'], future_game['VISITOR_TEAM_ID'],
                future_game['HOME_TEAM_ABBREVIATION'], future_game['VISITOR_TEAM_ABBREVIATION'], future_game['HOME_TEAM_NAME'],
                future_game['VISITOR_TEAM_NAME']
            ))

# Commit the transactions
conn.commit()

# Close the connection
cursor.close()
conn.close()

print("Data inserted into PostgreSQL database successfully.")
