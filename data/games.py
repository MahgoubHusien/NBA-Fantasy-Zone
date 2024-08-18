import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueGameLog, BoxScoreTraditionalV2
from datetime import datetime
import time
from requests.exceptions import ReadTimeout
import numpy as np

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

# Create tables if they do not exist
cursor.execute("""
    CREATE TABLE IF NOT EXISTS games (
        game_id INT PRIMARY KEY,
        season_id INT,
        game_date VARCHAR,
        team_id INT,
        team_name VARCHAR,
        team_abbreviation VARCHAR,
        matchup VARCHAR,
        wl VARCHAR(1),
        min INT,
        fgm INT,
        fga INT,
        fg_pct FLOAT,
        fg3m INT,
        fg3a INT,
        fg3_pct FLOAT,
        ftm INT,
        fta INT,
        ft_pct FLOAT,
        oreb INT,
        dreb INT,
        reb INT,
        ast INT,
        stl INT,
        blk INT,
        tov INT,
        pf INT,
        pts INT,
        plus_minus FLOAT,
        video_available BOOLEAN
    );

    CREATE TABLE IF NOT EXISTS player_box_stats (
        game_id INT,
        team_id INT,
        team_abbreviation VARCHAR,
        player_id INT,
        player_name VARCHAR,
        start_position VARCHAR,
        min VARCHAR,
        fgm INT,
        fga INT,
        fg_pct FLOAT,
        fg3m INT,
        fg3a INT,
        fg3_pct FLOAT,
        ftm INT,
        fta INT,
        ft_pct FLOAT,
        oreb INT,
        dreb INT,
        reb INT,
        ast INT,
        stl INT,
        blk INT,
        tov INT,
        pf INT,
        pts INT,
        plus_minus FLOAT,
        PRIMARY KEY (game_id, player_id)
    );
""")

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
            boxscore = BoxScoreTraditionalV2(game_id=game_id)
            player_stats = boxscore.get_data_frames()[0]
            return player_stats
        except ReadTimeout:
            if i < retries - 1:
                print(f"Timeout error occurred. Retrying {retries - i - 1} more times...")
                time.sleep(5)  # wait before retrying
            else:
                raise

# Function to handle NaN values
def safe_int(value):
    return int(value) if not pd.isna(value) else 0

def safe_float(value):
    return float(value) if not pd.isna(value) else 0.0

# Fetch games
games_df = fetch_games()

# Limit to the last 38 games
games_df = games_df.tail(38)

# Print the number of games fetched
print("Number of games fetched:", len(games_df))

# Print columns to verify
print("Games columns:", games_df.columns)

# Get today's date as a string
today = datetime.today().strftime('%Y-%m-%d')

# Iterate through each game
for index, game in games_df.iterrows():
    game_id = game['GAME_ID']
    game_date = game['GAME_DATE']

    # Insert game details
    cursor.execute("""
        INSERT INTO games (game_id, season_id, game_date, team_id, team_name, team_abbreviation, matchup, wl, min, pts, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, oreb, dreb, reb, ast, stl, blk, tov, pf, plus_minus, video_available)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (game_id) DO NOTHING
    """, (
        int(game_id), int(game['SEASON_ID']), game_date, int(game['TEAM_ID']), game['TEAM_NAME'], game['TEAM_ABBREVIATION'],
        game['MATCHUP'], game['WL'], int(game['MIN']), int(game['PTS']), int(game['FGM']), int(game['FGA']), float(game['FG_PCT']), int(game['FG3M']),
        int(game['FG3A']), float(game['FG3_PCT']), int(game['FTM']), int(game['FTA']), float(game['FT_PCT']), int(game['OREB']), int(game['DREB']), int(game['REB']),
        int(game['AST']), int(game['STL']), int(game['BLK']), int(game['TOV']), int(game['PF']), float(game['PLUS_MINUS']), bool(game['VIDEO_AVAILABLE'])
    ))

    # Fetch player stats only for past games
    if game_date < today:
        player_stats = fetch_player_stats(game_id)  # Ensure player_stats is defined here
        
        # Print columns to verify
        print("Player stats columns:", player_stats.columns)
        
        # Insert player stats
        for _, player in player_stats.iterrows():
            cursor.execute("""
                INSERT INTO player_box_stats (game_id, team_id, team_abbreviation, player_id, player_name, start_position, min, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, oreb, dreb, reb, ast, stl, blk, tov, pf, pts, plus_minus)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (game_id, player_id) DO NOTHING
            """, (
                safe_int(player['GAME_ID']), safe_int(player['TEAM_ID']), player['TEAM_ABBREVIATION'], safe_int(player['PLAYER_ID']), player['PLAYER_NAME'], 
                player['START_POSITION'], player['MIN'], safe_int(player['FGM']), safe_int(player['FGA']), safe_float(player['FG_PCT']), safe_int(player['FG3M']), 
                safe_int(player['FG3A']), safe_float(player['FG3_PCT']), safe_int(player['FTM']), safe_int(player['FTA']), safe_float(player['FT_PCT']), 
                safe_int(player['OREB']), safe_int(player['DREB']), safe_int(player['REB']), safe_int(player['AST']), safe_int(player['STL']), safe_int(player['BLK']), 
                safe_int(player['TO']), safe_int(player['PF']), safe_int(player['PTS']), safe_float(player['PLUS_MINUS'])
            ))

# Commit the transactions
conn.commit()

# Close the connection
cursor.close()
conn.close()

print("Data inserted into PostgreSQL database successfully.")
