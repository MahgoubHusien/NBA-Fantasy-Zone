from nba_api.stats.endpoints import commonplayerinfo, leaguedashplayerstats, playercareerstats
import pandas as pd
from sqlalchemy import create_engine

# Function to fetch common player info
def fetch_common_player_info(player_id):
    player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
    return player_info

# Function to fetch league dash player stats
def fetch_league_dash_stats():
    league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
    return league_dash_stats

# Function to fetch player career stats
def fetch_player_career_stats(player_id):
    career_stats = playercareerstats.PlayerCareerStats(player_id=player_id).get_data_frames()[0]
    return career_stats

# Fetching player IDs for the current season
league_dash_stats = fetch_league_dash_stats()
player_ids = league_dash_stats['PLAYER_ID'].tolist()

# Fetch common player info for each player
all_players_info = pd.DataFrame()
for pid in player_ids:
    player_info = fetch_common_player_info(pid)
    all_players_info = pd.concat([all_players_info, player_info], ignore_index=True)

# Fetch career stats for each player
all_career_stats = pd.DataFrame()
for pid in player_ids:
    career_stats = fetch_player_career_stats(pid)
    all_career_stats = pd.concat([all_career_stats, career_stats], ignore_index=True)

# Display the first few rows of each DataFrame
print("Common Player Info:")
print(all_players_info.head())

print("\nLeague Dash Stats:")
print(league_dash_stats.head())

print("\nPlayer Career Stats:")
print(all_career_stats.head())

# Save to CSV
all_players_info.to_csv('common_player_info.csv', index=False)
league_dash_stats.to_csv('league_dash_stats.csv', index=False)
all_career_stats.to_csv('player_career_stats.csv', index=False)

# # Database connection string
# db_connection_str = 'postgresql://username:password@localhost:5432/nba_db'
# db_connection = create_engine(db_connection_str)

# # Store data to PostgreSQL
# all_players_info.to_sql('common_player_info', db_connection, if_exists='replace', index=False)
# league_dash_stats.to_sql('league_dash_stats', db_connection, if_exists='replace', index=False)
# all_career_stats.to_sql('player_career_stats', db_connection, if_exists='replace', index=False)

# print("Data has been saved to PostgreSQL and CSV files.")
