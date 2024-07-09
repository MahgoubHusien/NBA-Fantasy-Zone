from nba_api.stats.endpoints import commonplayerinfo, leaguedashplayerstats, playercareerstats
import pandas as pd
from sqlalchemy import create_engine
import concurrent.futures

def fetch_common_player_info(player_id):
    player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
    return player_info

def fetch_player_career_stats(player_id):
    career_stats = playercareerstats.PlayerCareerStats(player_id=player_id).get_data_frames()[0]
    return career_stats

def fetch_data(player_ids):
    all_players_info = pd.DataFrame()
    all_career_stats = pd.DataFrame()

    with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
        common_info_futures = {executor.submit(fetch_common_player_info, pid): pid for pid in player_ids}
        career_stats_futures = {executor.submit(fetch_player_career_stats, pid): pid for pid in player_ids}

        for future in concurrent.futures.as_completed(common_info_futures):
            player_info = future.result()
            all_players_info = pd.concat([all_players_info, player_info], ignore_index=True)

        for future in concurrent.futures.as_completed(career_stats_futures):
            career_stats = future.result()
            all_career_stats = pd.concat([all_career_stats, career_stats], ignore_index=True)

    return all_players_info, all_career_stats

# Fetching player IDs for the current season
league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
player_ids = league_dash_stats['PLAYER_ID'].tolist()

# Fetch data concurrently
all_players_info, all_career_stats = fetch_data(player_ids)

# Display common player info
print(all_players_info.head())

# Display career stats
print(all_career_stats.head())

# Database connection string
db_connection_str = 'postgresql://username:password@localhost:5432/nba_db'
db_connection = create_engine(db_connection_str)

# Store data to PostgreSQL
all_players_info.to_sql('common_player_info', db_connection, if_exists='replace', index=False)
league_dash_stats.to_sql('league_dash_stats', db_connection, if_exists='replace', index=False)
all_career_stats.to_sql('player_career_stats', db_connection, if_exists='replace', index=False)

# Store data to CSV files
all_players_info.to_csv('common_player_info.csv', index=False)
league_dash_stats.to_csv('league_dash_stats.csv', index=False)
all_career_stats.to_csv('player_career_stats.csv', index=False)
