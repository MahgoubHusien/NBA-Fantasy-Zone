import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import CommonTeamRoster
from nba_api.stats.static import teams

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

# Create the coaches table if it doesn't exist
create_coaches_table_query = """
CREATE TABLE IF NOT EXISTS coaches (
    team_id VARCHAR,
    team_name VARCHAR,
    team_abbreviation VARCHAR,
    coach_id VARCHAR PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    coach_type VARCHAR,
    season VARCHAR,
    season_type VARCHAR
);
"""
cursor.execute(create_coaches_table_query)
conn.commit()

# Function to fetch and insert coaches data
def fetch_and_insert_coaches(season='2023-24', season_type='Regular Season'):
    nba_teams = teams.get_teams()
    
    for team in nba_teams:
        team_id = team['id']
        team_name = team['full_name']
        team_abbreviation = team['abbreviation']
        
        # Fetch coaches data
        team_coaches = CommonTeamRoster(team_id=team_id, season=season)
        coaches_df = team_coaches.get_data_frames()[1]  # Coaches data is the second DataFrame
        
        for _, coach in coaches_df.iterrows():
            cursor.execute("""
                INSERT INTO coaches (team_id, team_name, team_abbreviation, coach_id, first_name, last_name, coach_type, season, season_type)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (coach_id) DO NOTHING
            """, (
                team_id, team_name, team_abbreviation, coach['COACH_ID'], coach['FIRST_NAME'], coach['LAST_NAME'], 
                coach['COACH_TYPE'], season, season_type
            ))
        
        # Commit after each team to avoid data loss
        conn.commit()

# Fetch and insert coaches data
fetch_and_insert_coaches()

# Close the connection
cursor.close()
conn.close()

print("Coaches data inserted into PostgreSQL database successfully.")
