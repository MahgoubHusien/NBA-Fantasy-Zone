import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueLeaders

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

def check_and_create_league_leaders_table(conn):
    create_league_leaders_table_query = """
    CREATE TABLE IF NOT EXISTS league_leaders (
        player_id INT PRIMARY KEY,
        id INT,
        rank INT,
        player_name VARCHAR(100),
        team VARCHAR(100),
        gp INT,
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
        stl FLOAT,
        blk FLOAT,
        tov FLOAT,
        pf FLOAT,
        pts FLOAT,
        eff FLOAT,
        ast_tov FLOAT,
        stl_tov FLOAT
    );
    """

    with conn.cursor() as cur:
        cur.execute(create_league_leaders_table_query)
        conn.commit()
    print("Table `league_leaders` checked and created if not exists.")

check_and_create_league_leaders_table(conn)

def fetch_and_insert_league_leaders(conn):
    try:
        # Fetch league leaders data using the LeagueLeaders class
        league_leaders = LeagueLeaders(season='2023-24').get_data_frames()[0]
        
        query_league_leaders = """
        INSERT INTO league_leaders (
            player_id, id, rank, player_name, team, gp, min, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, oreb, dreb, reb, ast, stl, blk, tov, pf, pts, eff, ast_tov, stl_tov
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (player_id) DO UPDATE SET
            id = EXCLUDED.id,
            rank = EXCLUDED.rank,
            player_name = EXCLUDED.player_name,
            team = EXCLUDED.team,
            gp = EXCLUDED.gp,
            min = EXCLUDED.min,
            fgm = EXCLUDED.fgm,
            fga = EXCLUDED.fga,
            fg_pct = EXCLUDED.fg_pct,
            fg3m = EXCLUDED.fg3m,
            fg3a = EXCLUDED.fg3a,
            fg3_pct = EXCLUDED.fg3_pct,
            ftm = EXCLUDED.ftm,
            fta = EXCLUDED.fta,
            ft_pct = EXCLUDED.ft_pct,
            oreb = EXCLUDED.oreb,
            dreb = EXCLUDED.dreb,
            reb = EXCLUDED.reb,
            ast = EXCLUDED.ast,
            stl = EXCLUDED.stl,
            blk = EXCLUDED.blk,
            tov = EXCLUDED.tov,
            pf = EXCLUDED.pf,
            pts = EXCLUDED.pts,
            eff = EXCLUDED.eff,
            ast_tov = EXCLUDED.ast_tov,
            stl_tov = EXCLUDED.stl_tov;
        """
        
        with conn.cursor() as cur:
            for index, row in league_leaders.iterrows():
                # Adjust the id to be player_id minus 1
                adjusted_id = row['PLAYER_ID'] - 1
                cur.execute(query_league_leaders, (
                    row['PLAYER_ID'], adjusted_id, row['RANK'], row['PLAYER'], row['TEAM'], row['GP'], row['MIN'], row['FGM'], row['FGA'], row['FG_PCT'], row['FG3M'],
                    row['FG3A'], row['FG3_PCT'], row['FTM'], row['FTA'], row['FT_PCT'], row['OREB'], row['DREB'], row['REB'], row['AST'], row['STL'], row['BLK'], row['TOV'], row['PF'], row['PTS'], row['EFF'], row['AST_TOV'], row['STL_TOV']
                ))
            conn.commit()
        print("Inserted/Updated league leaders data with adjusted IDs.")
    except Exception as e:
        print(f"Error fetching or inserting league leaders data: {e}")
        conn.rollback()  # Rollback the transaction on error

fetch_and_insert_league_leaders(conn)

conn.close()
