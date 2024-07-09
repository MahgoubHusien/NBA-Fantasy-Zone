from nba_api.stats.endpoints import commonplayerinfo
import pandas as pd

# Function to fetch common player info for LeBron James (PLAYER_ID: 2544)
def fetch_common_player_info(player_id):
    try:
        player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
        return player_info
    except Exception as e:
        print(f"Error fetching data for player {player_id}: {e}")
        return pd.DataFrame()

# Fetch common player info for LeBron James
lebron_info = fetch_common_player_info(2544)

# Print common player info
print("LeBron James' Common Player Info:")
print(lebron_info)
