import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import CommonTeamRoster
from nba_api.stats.static import teams

load_dotenv()

conn_params = {
    'dbname': os.getenv('DB_NAME'),
    'user': os.getenv('DB_USER'),
    'password': os.getenv('DB_PASSWORD'),
    'host': os.getenv('DB_HOST'),
    'port': int(os.getenv('DB_PORT'))
}

conn = psycopg2.connect(**conn_params)
cursor = conn.cursor()

drop_coaches_table_query = "DROP TABLE IF EXISTS coaches;"
cursor.execute(drop_coaches_table_query)
conn.commit()

create_coaches_table_query = """
CREATE TABLE IF NOT EXISTS coaches (
    team_id INTEGER,
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

def fetch_and_insert_head_coaches(season='2023-24', season_type='Regular Season'):
    nba_teams = teams.get_teams()
    
    for team in nba_teams:
        team_id = team['id']
        team_name = team['full_name']
        team_abbreviation = team['abbreviation']
        
        team_roster = CommonTeamRoster(team_id=team_id, season=season)
        coaches_df = team_roster.get_data_frames()[1] 
        
        for _, coach in coaches_df.iterrows():
            if coach['COACH_TYPE'] == 'Head Coach':
                cursor.execute("""
                    INSERT INTO coaches (team_id, team_name, team_abbreviation, coach_id, first_name, last_name, coach_type, season, season_type)
                    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
                    ON CONFLICT (coach_id) DO NOTHING
                """, (
                    team_id, team_name, team_abbreviation, coach['COACH_ID'], coach['FIRST_NAME'], coach['LAST_NAME'], 
                    coach['COACH_TYPE'], season, season_type
                ))
        
        conn.commit()

def update_team_stats_with_coaches():
    update_query = """
    UPDATE team_stats ts
    SET coach_name = c.first_name || ' ' || c.last_name
    FROM coaches c
    WHERE ts.team_id = c.team_id;
    """
    cursor.execute(update_query)
    conn.commit()


fetch_and_insert_head_coaches()

update_team_stats_with_coaches()

cursor.close()
conn.close()

print("Coach names added to team_stats table successfully.")
