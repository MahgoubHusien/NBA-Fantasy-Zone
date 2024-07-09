from nba_api.stats.endpoints import commonplayerinfo, leaguedashplayerstats, playercareerstats
import pandas as pd

# Function to fetch common player info
def fetch_common_player_info(player_id):
    player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
    return player_info

# Fetching player IDs for the current season
league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
player_ids = league_dash_stats['PLAYER_ID'].tolist()

# Fetch common player info for each player
all_players_info = pd.DataFrame()
for pid in player_ids:
    player_info = fetch_common_player_info(pid)
    all_players_info = pd.concat([all_players_info, player_info], ignore_index=True)

# Display common player info
print(all_players_info.head())
