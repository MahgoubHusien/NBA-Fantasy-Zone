import os
import time
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import leaguedashplayerstats, commonplayerinfo

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

def check_and_create_table(conn):
    create_table_query = """
    CREATE TABLE IF NOT EXISTS common_player_info (
        player_id INT PRIMARY KEY,
        first_name VARCHAR(100),
        last_name VARCHAR(100),
        team_id INT,
        team_name VARCHAR(100),
        team_abbreviation VARCHAR(20),
        jersey VARCHAR(10),
        position VARCHAR(10),
        height VARCHAR(10),
        weight VARCHAR(10),
        pts FLOAT,
        ast FLOAT,
        reb FLOAT,
        gp INT,
        min FLOAT,
        fg_pct FLOAT,
        ft_pct FLOAT,
        fg3_pct FLOAT
    );
    """
    with conn.cursor() as cur:
        cur.execute(create_table_query)
        conn.commit()
    print("Table `player_info` checked and created if not exists.")

check_and_create_table(conn)

def fetch_all_player_ids():
    player_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
    return player_stats['PLAYER_ID'].tolist()

def fetch_and_insert_player_info(player_id, conn):
    try:
        player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
        player_info_dict = player_info.to_dict(orient='records')[0]

        player_data = {
            'PERSON_ID': player_info_dict.get('PERSON_ID', None),
            'FIRST_NAME': player_info_dict.get('FIRST_NAME', ''),
            'LAST_NAME': player_info_dict.get('LAST_NAME', ''),
            'TEAM_ID': player_info_dict.get('TEAM_ID', None),
            'TEAM_NAME': player_info_dict.get('TEAM_NAME', ''),
            'TEAM_ABBREVIATION': player_info_dict.get('TEAM_ABBREVIATION', ''),
            'JERSEY': player_info_dict.get('JERSEY', ''),
            'POSITION': player_info_dict.get('POSITION', ''),
            'HEIGHT': player_info_dict.get('HEIGHT', ''),
            'WEIGHT': player_info_dict.get('WEIGHT', ''),
            'PTS': player_info_dict.get('PTS', 0.0),
            'AST': player_info_dict.get('AST', 0.0),
            'REB': player_info_dict.get('REB', 0.0),
            'GP': player_info_dict.get('GP', 0),
            'MIN': player_info_dict.get('MIN', 0.0),
            'FG_PCT': player_info_dict.get('FG_PCT', 0.0),
            'FT_PCT': player_info_dict.get('FT_PCT', 0.0),
            'FG3_PCT': player_info_dict.get('FG3_PCT', 0.0)
        }

        query = """
        INSERT INTO player_info (player_id, first_name, last_name, team_id, team_name, team_abbreviation, jersey, position, height, weight, pts, ast, reb, gp, min, fg_pct, ft_pct, fg3_pct)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (player_id) DO NOTHING;
        """
        with conn.cursor() as cur:
            cur.execute(query, (
                player_data['PERSON_ID'],
                player_data['FIRST_NAME'],
                player_data['LAST_NAME'],
                player_data['TEAM_ID'],
                player_data['TEAM_NAME'],
                player_data['TEAM_ABBREVIATION'],
                player_data['JERSEY'],
                player_data['POSITION'],
                player_data['HEIGHT'],
                player_data['WEIGHT'],
                player_data['PTS'],
                player_data['AST'],
                player_data['REB'],
                player_data['GP'],
                player_data['MIN'],
                player_data['FG_PCT'],
                player_data['FT_PCT'],
                player_data['FG3_PCT']
            ))
            conn.commit()
        print(f"Inserted player info for {player_data['FIRST_NAME']} {player_data['LAST_NAME']}")
    except Exception as e:
        print(f"Error fetching or inserting data for player_id {player_id}: {e}")
        conn.rollback()  # Rollback the transaction on error

player_ids = fetch_all_player_ids()

batch_size = 5

for i in range(0, len(player_ids), batch_size):
    batch = player_ids[i:i+batch_size]
    for player_id in batch:
        fetch_and_insert_player_info(player_id, conn)
    print(f"Batch {i//batch_size + 1} processed, sleeping for 60 seconds.")
    time.sleep(30)

conn.close()
