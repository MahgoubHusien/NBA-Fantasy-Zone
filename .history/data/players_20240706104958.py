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

# Fetch league dash stats for all players
league_dash_stats = fetch_league_dash_stats()

# Fetching player IDs for the current season
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

# Displaying a few rows of each DataFrame to ensure correctness
print("Common Player Info:")
print(all_players_info.head())

print("League Dash Stats:")
print(league_dash_stats.head())

print("Player Career Stats:")
print(all_career_stats.head())

# Database connection string
db_connection_str = 'postgresql://username:password@localhost:5432/nba_db'
db_connection = create_engine(db_connection_str)

# Store common player info to PostgreSQL
all_players_info.to_sql('common_player_info', db_connection, if_exists='replace', index=False)

# Store league dash stats to PostgreSQL
league_dash_stats.to_sql('league_dash_stats', db_connection, if_exists='replace', index=False)

# Store career stats to PostgreSQL
all_career_stats.to_sql('player_career_stats', db_connection, if_exists='replace', index=False)

print("Data successfully imported to PostgreSQL")
