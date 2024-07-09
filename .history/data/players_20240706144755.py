import os
import time
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import leaguedashplayerstats, commonplayerinfo, PlayerFantasyProfileBarGraph

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
        ppg FLOAT,
        apg FLOAT,
        rpg FLOAT,
        spg FLOAT,
        topg FLOAT,
        bpg FLOAT,
        pfpg FLOAT
    );
    """
    
    create_current_player_stats_table_query = """
    CREATE TABLE IF NOT EXISTS current_player_stats (
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
    
    create_fantasy_stats_table_query = """
    CREATE TABLE IF NOT EXISTS player_fantasy_stats (
        player_id INT PRIMARY KEY,
        player_name VARCHAR(100),
        team_id INT,
        team_abbreviation VARCHAR(20),
        fan_duel_pts_last5 FLOAT,
        nba_fantasy_pts_last5 FLOAT,
        pts_last5 FLOAT,
        reb_last5 FLOAT,
        ast_last5 FLOAT,
        fg3m_last5 FLOAT,
        ft_pct_last5 FLOAT,
        stl_last5 FLOAT,
        blk_last5 FLOAT,
        tov_last5 FLOAT,
        fg_pct_last5 FLOAT,
        fan_duel_pts_season FLOAT,
        nba_fantasy_pts_season FLOAT,
        pts_season FLOAT,
        reb_season FLOAT,
        ast_season FLOAT,
        fg3m_season FLOAT,
        ft_pct_season FLOAT,
        stl_season FLOAT,
        blk_season FLOAT,
        tov_season FLOAT,
        fg_pct_season FLOAT,
        last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    """
    
    with conn.cursor() as cur:
        cur.execute(create_common_player_info_table_query)
        cur.execute(create_current_player_stats_table_query)
        cur.execute(create_fantasy_stats_table_query)
        conn.commit()
    print("Tables checked and created if not exists.")

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
        
        games_played = player_stats_dict.get('GP', 1)
        player_data.update({
            'PPG': round(player_stats_dict.get('PTS', 0.0) / games_played * 100, 1),
            'APG': round(player_stats_dict.get('AST', 0.0) / games_played * 100, 1),
            'RPG': round(player_stats_dict.get('REB', 0.0) / games_played * 100, 1),
            'SPG': round(player_stats_dict.get('STL', 0.0) / games_played * 100, 1),
            'TOPG': round(player_stats_dict.get('TOV', 0.0) / games_played * 100, 1),
            'BPG': round(player_stats_dict.get('BLK', 0.0) / games_played * 100, 1),
            'PFPG': round(player_stats_dict.get('PF', 0.0) / games_played * 100, 1)
        })
        
        query_common_player_info = """
        INSERT INTO common_player_info (player_id, first_name, last_name, team_id, team_name, team_abbreviation, jersey, position, height, weight, ppg, apg, rpg, spg, topg, bpg, pfpg)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
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
                player_data['PPG'],
                player_data['APG'],
                player_data['RPG'],
                player_data['SPG'],
                player_data['TOPG'],
                player_data['BPG'],
                player_data['PFPG']
            ))
            conn.commit()
        print(f"Inserted player info for {player_data['FIRST_NAME']} {player_data['LAST_NAME']}")
    except Exception as e:
        print(f"Error fetching or inserting player info for player_id {player_id}: {e}")
        conn.rollback()  # Rollback the transaction on error

def fetch_and_insert_fantasy_stats(player_id, conn):
    try:
        # Fetch fantasy stats
        fantasy_profile = PlayerFantasyProfileBarGraph(player_id=player_id)
        
        last_five_games_avg = fantasy_profile.last_five_games_avg.get_data_frame()
        season_avg = fantasy_profile.season_avg.get_data_frame()
        
        if not last_five_games_avg.empty and not season_avg.empty:
            last_five_games_stats = last_five_games_avg.to_dict(orient='records')[0]
            season_stats = season_avg.to_dict(orient='records')[0]
            
            query_fantasy_stats = """
            INSERT INTO player_fantasy_stats (
                player_id, player_name, team_id, team_abbreviation,
                fan_duel_pts_last5, nba_fantasy_pts_last5, pts_last5, reb_last5, ast_last5, fg3m_last5, ft_pct_last5, stl_last5, blk_last5, tov_last5, fg_pct_last5,
                fan_duel_pts_season, nba_fantasy_pts_season, pts_season, reb_season, ast_season, fg3m_season, ft_pct_season, stl_season, blk_season, tov_season, fg_pct_season
            ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            ON CONFLICT (player_id) DO UPDATE SET
                player_name = EXCLUDED.player_name,
                team_id = EXCLUDED.team_id,
                team_abbreviation = EXCLUDED.team_abbreviation,
                fan_duel_pts_last5 = EXCLUDED.fan_duel_pts_last5,
                nba_fantasy_pts_last5 = EXCLUDED.nba_fantasy_pts_last5,
                pts_last5 = EXCLUDED.pts_last5,
                reb_last5 = EXCLUDED.reb_last5,
                ast_last5 = EXCLUDED.ast_last5,
                fg3m_last5 = EXCLUDED.fg3m_last5,
                ft_pct_last5 = EXCLUDED.ft_pct_last5,
                stl_last5 = EXCLUDED.stl_last5,
                blk_last5 = EXCLUDED.blk_last5,
                tov_last5 = EXCLUDED.tov_last5,
                fg_pct_last5 = EXCLUDED.fg_pct_last5,
                fan_duel_pts_season = EXCLUDED.fan_duel_pts_season,
                nba_fantasy_pts_season = EXCLUDED.nba_fantasy_pts_season,
                pts_season = EXCLUDED.pts_season,
                reb_season = EXCLUDED.reb_season,
                ast_season = EXCLUDED.ast_season,
                fg3m_season = EXCLUDED.fg3m_season,
                ft_pct_season = EXCLUDED.ft_pct_season,
                stl_season = EXCLUDED.stl_season,
                blk_season = EXCLUDED.blk_season,
                tov_season = EXCLUDED.tov_season,
                fg_pct_season = EXCLUDED.fg_pct_season,
                last_updated = CURRENT_TIMESTAMP;
            """
            
            with conn.cursor() as cur:
                cur.execute(query_fantasy_stats, (
                    season_stats['PLAYER_ID'], season_stats['PLAYER_NAME'], season_stats['TEAM_ID'], season_stats['TEAM_ABBREVIATION'],
                    last_five_games_stats['FAN_DUEL_PTS'], last_five_games_stats['NBA_FANTASY_PTS'], last_five_games_stats['PTS'], last_five_games_stats['REB'],
                    last_five_games_stats['AST'], last_five_games_stats['FG3M'], last_five_games_stats['FT_PCT'], last_five_games_stats['STL'], last_five_games_stats['BLK'],
                    last_five_games_stats['TOV'], last_five_games_stats['FG_PCT'], season_stats['FAN_DUEL_PTS'], season_stats['NBA_FANTASY_PTS'], season_stats['PTS'],
                    season_stats['REB'], season_stats['AST'], season_stats['FG3M'], season_stats['FT_PCT'], season_stats['STL'], season_stats['BLK'], season_stats['TOV'],
                    season_stats['FG_PCT']
                ))
                conn.commit()
            print(f"Inserted/Updated fantasy stats for player_id {player_id}")
    except Exception as e:
        print(f"Error fetching or inserting fantasy stats for player_id {player_id}: {e}")
        conn.rollback()  # Rollback the transaction on error

def fetch_and_insert_league_dash_stats(conn):
    try:
        league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
        
        query_league_dash_stats = """
        INSERT INTO current_player_stats (player_id, player_name, team_abbreviation, age, gp, w, l, w_pct, min, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, oreb, dreb, reb, ast, tov, stl, blk, blka, pf, pfd, pts, plus_minus, nba_fantasy_pts, dd2, td3)
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

batch_size = 7

for i in range(0, len(player_ids), batch_size):
    batch = player_ids[i:i+batch_size]
    for player_id in batch:
        fetch_and_insert_player_info(player_id, conn)
        fetch_and_insert_fantasy_stats(player_id, conn)
    print(f"Batch {i//batch_size + 1} processed, sleeping for 30 seconds.")
    time.sleep(30)

fetch_and_insert_league_dash_stats(conn)

conn.close()
