import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueStandingsV3

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
    create_standings_table_query = """
    CREATE TABLE IF NOT EXISTS standings (
        league_id VARCHAR(10),
        season_id VARCHAR(10),
        team_id INT PRIMARY KEY,
        team_city VARCHAR(50),
        team_name VARCHAR(100),
        team_slug VARCHAR(50),
        conference VARCHAR(50),
        conference_record VARCHAR(10),
        playoff_rank INT,
        clinch_indicator VARCHAR(10),
        division VARCHAR(50),
        division_record VARCHAR(10),
        division_rank INT,
        wins INT,
        losses INT,
        win_pct FLOAT,
        league_rank INT,
        record VARCHAR(10),
        home VARCHAR(10),
        road VARCHAR(10),
        l10 VARCHAR(10),
        long_win_streak INT,
        long_loss_streak INT,
        current_streak VARCHAR(10),
        conference_games_back FLOAT,
        clinched_conference_title BOOLEAN,
        clinched_playoff_birth BOOLEAN,
        eliminated_conference BOOLEAN,
        points_pg FLOAT,
        opp_points_pg FLOAT,
        diff_points_pg FLOAT
    );
    """
    
    with conn.cursor() as cur:
        cur.execute(create_standings_table_query)
        conn.commit()
    print("Table `standings` checked and created if not exists.")

check_and_create_tables(conn)

def fetch_and_insert_standings(conn):
    try:
        # Fetch standings
        standings = LeagueStandingsV3(season='2023-24').standings.get_data_frame()
        print(standings)

        # Filter the necessary fields
        filtered_standings = standings[[
            'LeagueID', 'SeasonID', 'TeamID', 'TeamCity', 'TeamName', 'TeamSlug', 'Conference', 
            'ConferenceRecord', 'PlayoffRank', 'ClinchIndicator', 'Division', 'DivisionRecord', 
            'DivisionRank', 'WINS', 'LOSSES', 'WinPCT', 'LeagueRank', 'Record', 'HOME', 'ROAD', 
            'L10', 'LongWinStreak', 'LongLossStreak', 'CurrentStreak', 'ConferenceGamesBack', 
            'ClinchedConferenceTitle', 'ClinchedPlayoffBirth', 'EliminatedConference', 
            'PointsPG', 'OppPointsPG', 'DiffPointsPG'
        ]]

        query_standings = """
        INSERT INTO standings (
            league_id, season_id, team_id, team_city, team_name, team_slug, conference, conference_record, 
            playoff_rank, clinch_indicator, division, division_record, division_rank, wins, losses, win_pct, 
            league_rank, record, home, road, l10, long_win_streak, long_loss_streak, current_streak, 
            conference_games_back, clinched_conference_title, clinched_playoff_birth, eliminated_conference, 
            points_pg, opp_points_pg, diff_points_pg
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (team_id) DO UPDATE SET
            league_id = EXCLUDED.league_id,
            season_id = EXCLUDED.season_id,
            team_city = EXCLUDED.team_city,
            team_name = EXCLUDED.team_name,
            team_slug = EXCLUDED.team_slug,
            conference = EXCLUDED.conference,
            conference_record = EXCLUDED.conference_record,
            playoff_rank = EXCLUDED.playoff_rank,
            clinch_indicator = EXCLUDED.clinch_indicator,
            division = EXCLUDED.division,
            division_record = EXCLUDED.division_record,
            division_rank = EXCLUDED.division_rank,
            wins = EXCLUDED.wins,
            losses = EXCLUDED.losses,
            win_pct = EXCLUDED.win_pct,
            league_rank = EXCLUDED.league_rank,
            record = EXCLUDED.record,
            home = EXCLUDED.home,
            road = EXCLUDED.road,
            l10 = EXCLUDED.l10,
            long_win_streak = EXCLUDED.long_win_streak,
            long_loss_streak = EXCLUDED.long_loss_streak,
            current_streak = EXCLUDED.current_streak,
            conference_games_back = EXCLUDED.conference_games_back,
            clinched_conference_title = EXCLUDED.clinched_conference_title,
            clinched_playoff_birth = EXCLUDED.clinched_playoff_birth,
            eliminated_conference = EXCLUDED.eliminated_conference,
            points_pg = EXCLUDED.points_pg,
            opp_points_pg = EXCLUDED.opp_points_pg,
            diff_points_pg = EXCLUDED.diff_points_pg;
        """
        
        with conn.cursor() as cur:
            for index, row in filtered_standings.iterrows():
                cur.execute(query_standings, (
                    row['LeagueID'], row['SeasonID'], row['TeamID'], row['TeamCity'], row['TeamName'], 
                    row['TeamSlug'], row['Conference'], row['ConferenceRecord'], row['PlayoffRank'], 
                    row['ClinchIndicator'], row['Division'], row['DivisionRecord'], row['DivisionRank'], 
                    row['WINS'], row['LOSSES'], row['WinPCT'], row['LeagueRank'], row['Record'], 
                    row['HOME'], row['ROAD'], row['L10'], row['LongWinStreak'], row['LongLossStreak'], 
                    row['CurrentStreak'], row['ConferenceGamesBack'], row['ClinchedConferenceTitle'], 
                    row['ClinchedPlayoffBirth'], row['EliminatedConference'], row['PointsPG'], 
                    row['OppPointsPG'], row['DiffPointsPG']
                ))
            conn.commit()
        print("Inserted/Updated standings.")
    except Exception as e:
        print(f"Error fetching or inserting standings: {e}")
        conn.rollback()  # Rollback the transaction on error

fetch_and_insert_standings(conn)

conn.close()
