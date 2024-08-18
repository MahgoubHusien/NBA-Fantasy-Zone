import os
import psycopg2
from dotenv import load_dotenv

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

# Define the base URL for logos
base_url = '/logos'

# Define a function to get the logo filename based on team name
def get_logo_filename(team_name):
    # Handle special cases
    special_cases = {
        '76ers': '76ers',
        'Trail Blazers': 'blazers'
    }
    
    # If the team name matches a special case, use that
    if team_name in special_cases:
        formatted_team_name = special_cases[team_name]
    else:
        # Otherwise, format the team name to match the file name convention
        formatted_team_name = team_name.lower().replace(' ', '_').replace('-', '_')
    
    # Construct the full URL to the logo
    logo_url = f"{base_url}/{formatted_team_name}.png"
    return logo_url

# Function to update the logo_url in the standings table
def update_logo_url_in_standings(conn):
    update_query = """
    UPDATE standings
    SET logo_url = %s
    WHERE team_name = %s;
    """
    
    select_query = "SELECT team_name FROM standings;"

    with conn.cursor() as cur:
        cur.execute(select_query)
        teams = cur.fetchall()

        for team in teams:
            team_name = team[0]
            logo_url = get_logo_filename(team_name)
            cur.execute(update_query, (logo_url, team_name))
        
        conn.commit()
        print("Updated logo_url in standings table.")

# Function to update the logo_url in the team_stats table
def update_logo_url_in_team_stats(conn):
    update_query = """
    UPDATE team_stats
    SET logo_url = %s
    WHERE team_name = %s;
    """
    
    select_query = "SELECT team_name FROM team_stats;"

    with conn.cursor() as cur:
        cur.execute(select_query)
        teams = cur.fetchall()

        for team in teams:
            team_name = team[0]
            logo_url = get_logo_filename(team_name)
            cur.execute(update_query, (logo_url, team_name))
        
        conn.commit()
        print("Updated logo_url in team_stats table.")

# Execute the update functions
update_logo_url_in_standings(conn)
update_logo_url_in_team_stats(conn)

# Close the connection
conn.close()
