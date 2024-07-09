from nba_api.stats.endpoints import commonplayerinfo, leaguedashplayerstats
import pandas as pd
import time

# Function to fetch common player info
def fetch_common_player_info(player_id):
    try:
        player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
        return player_info
    except Exception as e:
        print(f"Error fetching data for player {player_id}: {e}")
        return pd.DataFrame()

# Fetching player IDs for the current season
league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
player_ids = league_dash_stats['PLAYER_ID'].tolist()

# Process in increments
batch_size = 10
for start in range(0, len(player_ids), batch_size):
    end = start + batch_size
    batch_ids = player_ids[start:end]
    
    all_players_info = pd.DataFrame()
    for pid in batch_ids:
        player_info = fetch_common_player_info(pid)
        all_players_info = pd.concat([all_players_info, player_info], ignore_index=True)
        time.sleep(1)  # To avoid hitting rate limits or server overload
    
    # Write common player info to CSV
    all_players_info.to_csv('common_player_info.csv', mode='a', header=(start==0), index=False)
    print(f"Processed and wrote players {start} to {end}")
