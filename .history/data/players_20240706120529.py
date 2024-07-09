import time
import pandas as pd
import psycopg2
from nba_api.stats.endpoints import leaguedashplayerstats, commonplayerinfo

# Define connection parameters for PostgreSQL
conn_params = {
    'dbname': 'your_dbname',
    'user': 'mahgoubhusien',
    'password': 'Lemlem11.',
    'host': 'localhost',
    'port': '5432'
}

# Establish a connection to the database
conn = psycopg2.connect(**conn_params)

def check_and_create_table(conn):
    create_table_query = """
    CREATE TABLE IF NOT EXISTS player_info (
        player_id INT PRIMARY KEY,
        first_name VARCHAR(50),
        last_name VARCHAR(50),
        team_id INT,
        team_name VARCHAR(50),
        team_abbreviation VARCHAR(10),
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

# Ensure the table is created before inserting any data
check_and_create_table(conn)

def fetch_all_player_ids():
    # Fetching player statistics for the current season
    player_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
    return player_stats['PLAYER_ID'].tolist()

def fetch_and_insert_player_info(player_id, conn):
    try:
        player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
        player_info_dict = player_info.to_dict(orient='records')[0]

        query = """
        INSERT INTO player_info (player_id, first_name, last_name, team_id, team_name, team_abbreviation, jersey, position, height, weight, pts, ast, reb, gp, min, fg_pct, ft_pct, fg3_pct)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (player_id) DO NOTHING;
        """
        with conn.cursor() as cur:
            cur.execute(query, (
                player_info_dict['PERSON_ID'],
                player_info_dict['FIRST_NAME'],
                player_info_dict['LAST_NAME'],
                player_info_dict['TEAM_ID'],
                player_info_dict['TEAM_NAME'],
                player_info_dict['TEAM_ABBREVIATION'],
                player_info_dict['JERSEY'],
                player_info_dict['POSITION'],
                player_info_dict['HEIGHT'],
                player_info_dict['WEIGHT'],
                player_info_dict['PTS'],
                player_info_dict['AST'],
                player_info_dict['REB'],
                player_info_dict['GP'],
                player_info_dict['MIN'],
                player_info_dict['FG_PCT'],
                player_info_dict['FT_PCT'],
                player_info_dict['FG3_PCT']
            ))
            conn.commit()
        print(f"Inserted player info for {player_info_dict['FIRST_NAME']} {player_info_dict['LAST_NAME']}")
    except Exception as e:
        print(f"Error fetching or inserting data for player_id {player_id}: {e}")

# Fetch all player IDs for the 2023-2024 season
player_ids = fetch_all_player_ids()

# Process players in batches with a delay between batches
batch_size = 5  # Number of players to process in each batch

for i in range(0, len(player_ids), batch_size):
    batch = player_ids[i:i+batch_size]
    for player_id in batch:
        fetch_and_insert_player_info(player_id, conn)
    print(f"Batch {i//batch_size + 1} processed, sleeping for 60 seconds.")
    time.sleep(60)  # Wait for 60 seconds before processing the next batch

# Close the database connection
conn.close()
