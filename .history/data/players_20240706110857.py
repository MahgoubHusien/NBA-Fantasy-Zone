import concurrent.futures
from nba_api.stats.endpoints import commonplayerinfo, leaguedashplayerstats, playercareerstats
import pandas as pd
from sqlalchemy import create_engine
from tenacity import retry, stop_after_attempt, wait_exponential, RetryError

# Define retry decorator
@retry(stop=stop_after_attempt(5), wait=wait_exponential(multiplier=1, min=4, max=60))
def fetch_common_player_info(player_id):
    try:
        player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
        return player_info
    except Exception as e:
        print(f"Error fetching common player info for player_id {player_id}: {e}")
        raise

@retry(stop=stop_after_attempt(5), wait=wait_exponential(multiplier=1, min=4, max=60))
def fetch_player_career_stats(player_id):
    try:
        career_stats = playercareerstats.PlayerCareerStats(player_id=player_id).get_data_frames()[0]
        return career_stats
    except Exception as e:
        print(f"Error fetching career stats for player_id {player_id}: {e}")
        raise

@retry(stop=stop_after_attempt(5), wait=wait_exponential(multiplier=1, min=4, max=60))
def fetch_league_dash_stats():
    try:
        league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
        return league_dash_stats
    except Exception as e:
        print(f"Error fetching league dash stats: {e}")
        raise

def fetch_data(player_ids):
    all_players_info = pd.DataFrame()
    all_career_stats = pd.DataFrame()

    with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
        common_info_futures = {executor.submit(fetch_common_player_info, pid): pid for pid in player_ids}
        career_stats_futures = {executor.submit(fetch_player_career_stats, pid): pid for pid in player_ids}

        for future in concurrent.futures.as_completed(common_info_futures):
            try:
                player_info = future.result()
                all_players_info = pd.concat([all_players_info, player_info], ignore_index=True)
            except RetryError:
                print(f"Failed to fetch common player info for player_id {common_info_futures[future]} after multiple attempts")

        for future in concurrent.futures.as_completed(career_stats_futures):
            try:
                career_stats = future.result()
                all_career_stats = pd.concat([all_career_stats, career_stats], ignore_index=True)
            except RetryError:
                print(f"Failed to fetch career stats for player_id {career_stats_futures[future]} after multiple attempts")

    return all_players_info, all_career_stats

# Fetching player IDs for the current season
try:
    league_dash_stats = fetch_league_dash_stats()
    player_ids = league_dash_stats['PLAYER_ID'].tolist()
except RetryError:
    print("Failed to fetch league dash stats after multiple attempts")
    player_ids = []

# Fetch data concurrently
all_players_info, all_career_stats = fetch_data(player_ids)

# Display common player info
print(all_players_info.head())

# Display career stats
print(all_career_stats.head())

# # Database connection string
# db_connection_str = 'postgresql://username:password@localhost:5432/nba_db'
# db_connection = create_engine(db_connection_str)

# # Store data to PostgreSQL
# all_players_info.to_sql('common_player_info', db_connection, if_exists='replace', index=False)
# league_dash_stats.to_sql('league_dash_stats', db_connection, if_exists='replace', index=False)
# all_career_stats.to_sql('player_career_stats', db_connection, if_exists='replace', index=False)


all_players_info.to_csv('data/common_player_info.csv', index=False)
all_career_stats.to_csv('data/player_career_stats.csv', index=False)
league_dash_stats.to_csv('data/league_dash_stats.csv', index=False)
