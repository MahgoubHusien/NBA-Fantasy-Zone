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

def check_and_create_tables(conn):
    create_common_player_info_table_query = """
    CREATE TABLE IF NOT EXISTS common_player_info (
        player_id INT PRIMARY KEY,
        first_name VARCHAR(100),
        last_name VARCHAR(100),
        team_id INT,
        team_name VARCHAR(100),
        team_abbreviation VARCHAR(20),
        jersey VARCHAR(20),
        position VARCHAR(20),
        height VARCHAR(20),
        weight VARCHAR(20),
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
    
    create_league_dash_player_stats_table_query = """
    CREATE TABLE IF NOT EXISTS league_dash_player_stats (
        player_id INT PRIMARY KEY,
        player_name VARCHAR(100),
        team_abbreviation VARCHAR(20),
        age INT,
        gp INT,
        w INT,
        l INT,
        w_pct FLOAT,
        min FLOAT,
        fgm FLOAT,
        fga FLOAT,
        fg_pct FLOAT,
        fg3m FLOAT,
        fg3a FLOAT,
        fg3_pct FLOAT,
        ftm FLOAT,
        fta FLOAT,
        ft_pct FLOAT,
        oreb FLOAT,
        dreb FLOAT,
        reb FLOAT,
        ast FLOAT,
        tov FLOAT,
        stl FLOAT,
        blk FLOAT,
        blka FLOAT,
        pf FLOAT,
        pfd FLOAT,
        pts FLOAT,
        plus_minus FLOAT,
        nba_fantasy_pts FLOAT,
        dd2 INT,
        td3 INT
    );
    """
    
    with conn.cursor() as cur:
        cur.execute(create_common_player_info_table_query)
        cur.execute(create_league_dash_player_stats_table_query)
        conn.commit()
    print("Tables `common_player_info` and `league_dash_player_stats` checked and created if not exists.")

check_and_create_tables(conn)

def fetch_all_player_ids():
    player_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
    return player_stats['PLAYER_ID'].tolist()

def fetch_and_insert_player_info(player_id, conn):
    try:
        # Fetch common player info
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
            'WEIGHT': player_info_dict.get('WEIGHT', '')
        }
        
        # Fetch league dash stats
        league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
        player_stats_dict = league_dash_stats[league_dash_stats['PLAYER_ID'] == player_id].to_dict(orient='records')[0]
        
        player_data.update({
            'PTS': player_stats_dict.get('PTS', 0.0),
            'AST': player_stats_dict.get('AST', 0.0),
            'REB': player_stats_dict.get('REB', 0.0),
            'GP': player_stats_dict.get('GP', 0),
            'MIN': player_stats_dict.get('MIN', 0.0),
            'FG_PCT': player_stats_dict.get('FG_PCT', 0.0),
            'FT_PCT': player_stats_dict.get('FT_PCT', 0.0),
            'FG3_PCT': player_stats_dict.get('FG3_PCT', 0.0)
        })
        
        query_common_player_info = """
        INSERT INTO common_player_info (player_id, first_name, last_name, team_id, team_name, team_abbreviation, jersey, position, height, weight, pts, ast, reb, gp, min, fg_pct, ft_pct, fg3_pct)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (player_id) DO NOTHING;
        """
        with conn.cursor() as cur:
            cur.execute(query_common_player_info, (
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
        print(f"Error fetching or inserting player info for player_id {player_id}: {e}")
        conn.rollback()  # Rollback the transaction on error

def fetch_and_insert_league_dash_stats(conn):
    try:
        league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
        
        query_league_dash_stats = """
        INSERT INTO league_dash_player_stats (player_id, player_name, team_abbreviation, age, gp, w, l, w_pct, min, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, oreb, dreb, reb, ast, tov, stl, blk, blka, pf, pfd, pts, plus_minus, nba_fantasy_pts, dd2, td3)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (player_id) DO NOTHING;
        """
        
        with conn.cursor() as cur:
            for index, row in league_dash_stats.iterrows():
                cur.execute(query_league_dash_stats, (
                    row['PLAYER_ID'], row['PLAYER_NAME'], row['TEAM_ABBREVIATION'], row['AGE'], row['GP'], row['W'], row['L'], row['W_PCT'], row['MIN'], row['FGM'],
                    row['FGA'], row['FG_PCT'], row['FG3M'], row['FG3A'], row['FG3_PCT'], row['FTM'], row['FTA'], row['FT_PCT'], row['OREB'], row['DREB'],
                    row['REB'], row['AST'], row['TOV'], row['STL'], row['BLK'], row['BLKA'], row['PF'], row['PFD'], row['PTS'], row['PLUS_MINUS'], row['NBA_FANTASY_PTS'],
                    row['DD2'], row['TD3']
                ))
            conn.commit()
        print("Inserted league dash player stats.")
    except Exception as e:
        print(f"Error fetching or inserting league dash player stats: {e}")
        conn.rollback()  # Rollback the transaction on error

player_ids = fetch_all_player_ids()

batch_size = 

for i in range(0, len(player_ids), batch_size):
    batch = player_ids[i:i+batch_size]
    for player_id in batch:
        fetch_and_insert_player_info(player_id, conn)
    print(f"Batch {i//batch_size + 1} processed, sleeping for 30 seconds.")
    time.sleep(30)

fetch_and_insert_league_dash_stats(conn)

conn.close()
