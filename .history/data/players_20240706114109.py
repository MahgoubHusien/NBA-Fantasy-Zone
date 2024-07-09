from nba_api.stats.endpoints import commonplayerinfo, leaguedashplayerstats
import pandas as pd
import time

# Function to fetch common player info
def fetch_common_player_info(player_id):
    try:
        player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
        print(f"Received data for player ID: {player_id}")
        return player_info
    except Exception as e:
        print(f"Error fetching data for player ID: {player_id}: {e}")
        return pd.DataFrame()

# Fetching player IDs for the current season
league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
player_ids = league_dash_stats['PLAYER_ID'].tolist()

# Fetch common player info for each player in small increments
all_players_info = pd.DataFrame()
batch_size = 10

for i in range(0, len(player_ids), batch_size):
    batch_ids = player_ids[i:i+batch_size]
    for pid in batch_ids:
        player_info = fetch_common_player_info(pid)
        all_players_info = pd.concat([all_players_info, player_info], ignore_index=True)
    # Sleep for a short period to avoid hitting rate limits
    time.sleep(1)

# Display common player info
print(all_players_info.head())
