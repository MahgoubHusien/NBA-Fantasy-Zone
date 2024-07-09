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

def fetch_and_save_standings():
    try:
        # Fetch standings
        standings = LeagueStandingsV3(season='2023-24').standings.get_data_frame()

        # Define columns to be inserted into the database
        required_columns = [
            "LeagueID", "SeasonID", "TeamID", "TeamCity", "TeamName", "TeamSlug", "Conference",
            "ConferenceRecord", "PlayoffRank", "ClinchIndicator", "Division", "DivisionRecord",
            "DivisionRank", "WINS", "LOSSES", "WinPCT", "LeagueRank", "Record", "HOME", "ROAD",
            "L10", "LongWinStreak", "LongLossStreak", "CurrentStreak", "ConferenceGamesBack",
            "ClinchedConferenceTitle", "ClinchedPlayoffBirth", "EliminatedConference",
            "PointsPG", "OppPointsPG", "DiffPointsPG"
        ]

        # Ensure all required columns are in the DataFrame, adding missing columns with None
        for col in required_columns:
            if col not in standings.columns:
                standings[col] = None

        # Filter DataFrame to include only the required columns
        filtered_standings = standings[required_columns]

        # Save the filtered DataFrame to a CSV file
        csv_file_path = 'filtered_standings.csv'
        filtered_standings.to_csv(csv_file_path, index=False)
        print(f"Filtered standings saved to {csv_file_path}")
        
    except Exception as e:
        print(f"Error fetching or saving standings: {e}")

fetch_and_save_standings()

conn.close()
