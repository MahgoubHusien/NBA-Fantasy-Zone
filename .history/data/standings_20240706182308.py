import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueDashTeamStats

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
    create_team_stats_table_query = """
    CREATE TABLE IF NOT EXISTS team_stats (
        team_id INT PRIMARY KEY,
        team_name VARCHAR(100),
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
        gp_rank INT,
        w_rank INT,
        l_rank INT,
        w_pct_rank FLOAT,
        min_rank INT,
        fgm_rank INT,
        fga_rank INT,
        fg_pct_rank FLOAT,
        fg3m_rank INT,
        fg3a_rank INT,
        fg3_pct_rank FLOAT,
        ftm_rank INT,
        fta_rank INT,
        ft_pct_rank FLOAT,
        oreb_rank INT,
        dreb_rank INT,
        reb_rank INT,
        ast_rank INT,
        tov_rank INT,
        stl_rank INT,
        blk_rank INT,
        blka_rank INT,
        pf_rank INT,
        pfd_rank INT,
        pts_rank INT,
        plus_minus_rank INT,
        cfid INT,
        cfparams TEXT
    );
    """
    
    with conn.cursor() as cur:
        cur.execute(create_team_stats_table_query)
        conn.commit()
    print("Table `team_stats` checked and created if not exists.")

check_and_create_tables(conn)

def fetch_and_insert_team_stats(conn):
    try:
        # Fetch team stats
        team_stats = LeagueDashTeamStats(season='2023-24').get_data_frames()[0]
        
        query_team_stats = """
        INSERT INTO team_stats (
            team_id, team_name, gp, w, l, w_pct, min, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, oreb, dreb, reb, ast, tov, stl, blk, blka, pf, pfd, pts, plus_minus, gp_rank, w_rank, l_rank, w_pct_rank, min_rank, fgm_rank, fga_rank, fg_pct_rank, fg3m_rank, fg3a_rank, fg3_pct_rank, ftm_rank, fta_rank, ft_pct_rank, oreb_rank, dreb_rank, reb_rank, ast_rank, tov_rank, stl_rank, blk_rank, blka_rank, pf_rank, pfd_rank, pts_rank, plus_minus_rank, cfid, cfparams
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (team_id) DO UPDATE SET
            team_name = EXCLUDED.team_name,
            gp = EXCLUDED.gp,
            w = EXCLUDED.w,
            l = EXCLUDED.l,
            w_pct = EXCLUDED.w_pct,
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
            tov = EXCLUDED.tov,
            stl = EXCLUDED.stl,
            blk = EXCLUDED.blk,
            blka = EXCLUDED.blka,
            pf = EXCLUDED.pf,
            pfd = EXCLUDED.pfd,
            pts = EXCLUDED.pts,
            plus_minus = EXCLUDED.plus_minus,
            gp_rank = EXCLUDED.gp_rank,
            w_rank = EXCLUDED.w_rank,
            l_rank = EXCLUDED.l_rank,
            w_pct_rank = EXCLUDED.w_pct_rank,
            min_rank = EXCLUDED.min_rank,
            fgm_rank = EXCLUDED.fgm_rank,
            fga_rank = EXCLUDED.fga_rank,
            fg_pct_rank = EXCLUDED.fg_pct_rank,
            fg3m_rank = EXCLUDED.fg3m_rank,
            fg3a_rank = EXCLUDED.fg3a_rank,
            fg3_pct_rank = EXCLUDED.fg3_pct_rank,
            ftm_rank = EXCLUDED.ftm_rank,
            fta_rank = EXCLUDED.fta_rank,
            ft_pct_rank = EXCLUDED.ft_pct_rank,
            oreb_rank = EXCLUDED.oreb_rank,
            dreb_rank = EXCLUDED.dreb_rank,
            reb_rank = EXCLUDED.reb_rank,
            ast_rank = EXCLUDED.ast_rank,
            tov_rank = EXCLUDED.tov_rank,
            stl_rank = EXCLUDED.stl_rank,
            blk_rank = EXCLUDED.blk_rank,
            blka_rank = EXCLUDED.blka_rank,
            pf_rank = EXCLUDED.pf_rank,
            pfd_rank = EXCLUDED.pfd_rank,
            pts_rank = EXCLUDED.pts_rank,
            plus_minus_rank = EXCLUDED.plus_minus_rank,
            cfid = EXCLUDED.cfid,
            cfparams = EXCLUDED.cfparams;
        """
        
        with conn.cursor() as cur:
            for index, row in team_stats.iterrows():
                cur.execute(query_team_stats, (
                    row['TEAM_ID'], row['TEAM_NAME'], row['GP'], row['W'], row['L'], row['W_PCT'], row['MIN'], row['FGM'], row['FGA'], row['FG_PCT'], row['FG3M'],
                    row['FG3A'], row['FG3_PCT'], row['FTM'], row['FTA'], row['FT_PCT'], row['OREB'], row['DREB'], row['REB'], row['AST'], row['TOV'], row['STL'], row['BLK'], row['BLKA'],
                    row['PF'], row['PFD'], row['PTS'], row['PLUS_MINUS'], row['GP_RANK'], row['W_RANK'], row['L_RANK'], row['W_PCT_RANK'], row['MIN_RANK'], row['FGM_RANK'], row['FGA_RANK'], row['FG_PCT_RANK'],
                    row['FG3M_RANK'], row['FG3A_RANK'], row['FG3_PCT_RANK'], row['FTM_RANK'], row['FTA_RANK'], row['FT_PCT_RANK'], row['OREB_RANK'], row['DREB_RANK'], row['REB_RANK'], row['AST_RANK'],
                    row['TOV_RANK'], row['STL_RANK'], row['BLK_RANK'], row['BLKA_RANK'], row['PF_RANK'], row['PFD_RANK'], row['PTS_RANK'], row['PLUS_MINUS_RANK'], row['CFID'], row['CFPARAMS']
                ))
            conn.commit()
        print("Inserted/Updated team stats.")
    except Exception as e:
        print(f"Error fetching or inserting team stats: {e}")
        conn.rollback()  # Rollback the transaction on error

fetch_and_insert_team_stats(conn)

conn.close()
