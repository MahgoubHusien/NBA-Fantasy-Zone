import concurrent.futures
import time
from nba_api.stats.endpoints import commonplayerinfo, leaguedashplayerstats, playercareerstats
import pandas as pd
from sqlalchemy import create_engine
from requests.exceptions import ReadTimeout

def fetch_common_player_info(player_id, retries=3, delay=5):
    for attempt in range(retries):
        try:
            player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id, timeout=60).get_data_frames()[0]
            print(f"Fetched common player info for player ID {player_id}")
            return player_info
        except ReadTimeout:
            print(f"Timeout error for player ID {player_id}, retrying... ({attempt + 1}/{retries})")
            time.sleep(delay)
    print(f"Failed to fetch common player info for player ID {player_id} after {retries} attempts")
    return None

def fetch_player_career_stats(player_id, retries=3, delay=5):
    for attempt in range(retries):
        try:
            career_stats = playercareerstats.PlayerCareerStats(player_id=player_id, timeout=60).get_data_frames()[0]
            print(f"Fetched career stats for player ID {player_id}")
            return career_stats
        except ReadTimeout:
            print(f"Timeout error for player ID {player_id}, retrying... ({attempt + 1}/{retries})")
            time.sleep(delay)
    print(f"Failed to fetch career stats for player ID {player_id} after {retries} attempts")
    return None

def fetch_data(player_ids):
    all_players_info = pd.DataFrame()
    all_career_stats = pd.DataFrame()

    with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
        common_info_futures = {executor.submit(fetch_common_player_info, pid): pid for pid in player_ids}
        career_stats_futures = {executor.submit(fetch_player_career_stats, pid): pid for pid in player_ids}

        for future in concurrent.futures.as_completed(common_info_futures):
            player_info = future.result()
            if player_info is not None:
                all_players_info = pd.concat([all_players_info, player_info], ignore_index=True)

        for future in concurrent.futures.as_completed(career_stats_futures):
            career_stats = future.result()
            if career_stats is not None:
                all_career_stats = pd.concat([all_career_stats, career_stats], ignore_index=True)

    return all_players_info, all_career_stats

# Fetching player IDs for the current season
print("Fetching league dash stats for the current season...")
league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24', timeout=60).get_data_frames()[0]
player_ids = league_dash_stats['PLAYER_ID'].tolist()
print(f"Fetched league dash stats. Number of players: {len(player_ids)}")

# Fetch data concurrently
print("Fetching data for all players...")
all_players_info, all_career_stats = fetch_data(player_ids)

# Display common player info
print("Sample common player info:")
print(all_players_info.head())

# Display career stats
print("Sample career stats:")
print(all_career_stats.head())

# Database connection string
db_connection_str = 'postgresql://username:password@localhost:5432/nba_db'
db_connection = create_engine(db_connection_str)

# Store data to PostgreSQL
print("Storing common player info to PostgreSQL...")
all_players_info.to_sql('common_player_info', db_connection, if_exists='replace', index=False)
print("Stored common player info to PostgreSQL")

print("Storing league dash stats to PostgreSQL...")
league_dash_stats.to_sql('league_dash_stats', db_connection, if_exists='replace', index=False)
print("Stored league dash stats to PostgreSQL")

print("Storing career stats to PostgreSQL...")
all_career_stats.to_sql('player_career_stats', db_connection, if_exists='replace', index=False)
print("Stored career stats to PostgreSQL")

# Export data to CSV
print("Exporting common player info to CSV...")
all_players_info.to_csv('common_player_info.csv', index=False)
print("Exported common player info to CSV")

print("Exporting league dash stats to CSV...")
league_dash_stats.to_csv('league_dash_stats.csv', index=False)
print("Exported league dash stats to CSV")

print("Exporting career stats to CSV...")
all_career_stats.to_csv('player_career_stats.csv', index=False)
print("Exported career stats to CSV")

print("Data fetching and storing completed successfully.")
