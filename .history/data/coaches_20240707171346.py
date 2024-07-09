import os
import psycopg2
import pandas as pd
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

def fetch_head_coach(team_id, season='2023-24'):
    team_roster = CommonTeamRoster(team_id=team_id, season=season)
    coaches_df = team_roster.get_data_frames()[1]  # The second DataFrame contains the coaches
    head_coach_df = coaches_df[coaches_df['COACH_TYPE'] == 'Head Coach']  # Filter and select the head coach
    if not head_coach_df.empty:
        head_coach = head_coach_df.iloc[0].to_dict()  # Convert to dictionary
        # Convert numpy.int64 to native Python int
        for key, value in head_coach.items():
            if isinstance(value, (np.int64, np.float64)):
                head_coach[key] = value.item()
        return head_coach
    else:
        return None

def insert_head_coach(cursor, head_coach):
    cursor.execute("""
        INSERT INTO team_coaches (team_id, season, coach_id, first_name, last_name, coach_type, sort_sequence, is_assistant)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (coach_id) DO NOTHING
    """, (
        head_coach['TEAM_ID'], head_coach['SEASON'], head_coach['COACH_ID'],
        head_coach['FIRST_NAME'], head_coach['LAST_NAME'], head_coach['COACH_TYPE'],
        head_coach['SORT_SEQUENCE'], head_coach['IS_ASSISTANT']
    ))

# Fetch all teams
nba_teams = teams.get_teams()

# Fetch and insert head coach for each team
for team in nba_teams:
    team_id = team['id']
    try:
        head_coach = fetch_head_coach(team_id)
        if head_coach:
            insert_head_coach(cursor, head_coach)
        else:
            print(f"No head coach found for team {team['full_name']}")
    except Exception as e:
        print(f"Error fetching head coach for team {team['full_name']}: {e}")

# Commit the transactions
conn.commit()

# Close the connection
cursor.close()
conn.close()

print("Head coach data inserted into PostgreSQL database successfully.")
