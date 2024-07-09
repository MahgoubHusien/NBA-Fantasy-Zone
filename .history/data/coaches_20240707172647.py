import os
import pandas as pd
import numpy as np
from dotenv import load_dotenv
from nba_api.stats.endpoints import CommonTeamRoster
from nba_api.stats.static import teams
from requests.exceptions import ReadTimeout
import time

# Load environment variables from .env file
load_dotenv()

def fetch_head_coach(team_id, season='2023-24', timeout=60, retries=3, delay=5):
    attempt = 0
    while attempt < retries:
        try:
            team_roster = CommonTeamRoster(team_id=team_id, season=season, timeout=timeout)
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
        except ReadTimeout:
            attempt += 1
            print(f"Timeout fetching head coach for team_id {team_id}, attempt {attempt} of {retries}. Retrying in {delay} seconds...")
            time.sleep(delay)
    print(f"Failed to fetch head coach for team_id {team_id} after {retries} attempts.")
    return None

# Fetch all teams
nba_teams = teams.get_teams()

# Prepare a list to hold all head coaches data
head_coaches_data = []

# Fetch head coach for each team
for team in nba_teams:
    team_id = team['id']
    try:
        head_coach = fetch_head_coach(team_id)
        if head_coach:
            head_coaches_data.append(head_coach)
        else:
            print(f"No head coach found for team {team['full_name']}")
    except Exception as e:
        print(f"Error fetching head coach for team {team['full_name']}: {e}")

# Convert the list of dictionaries to a DataFrame
head_coaches_df = pd.DataFrame(head_coaches_data)

# Save the DataFrame to a CSV file
head_coaches_df.to_csv('head_coaches.csv', index=False)

print("Head coach data saved to head_coaches.csv successfully.")
