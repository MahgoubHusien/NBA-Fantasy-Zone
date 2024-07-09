import time
from nba_api.stats.endpoints import commonplayerinfo, leaguedashplayerstats
import pandas as pd
from sqlalchemy import create_engine

# Database connection string
db_connection_str = 'postgresql://username:password@localhost:5432/nba_db'
db_connection = create_engine(db_connection_str)

# Function to fetch common player info
def fetch_common_player_info(player_id):
    player_info = commonplayerinfo.CommonPlayerInfo(player_id=player_id).get_data_frames()[0]
    return player_info

# Fetching player IDs for the current season
league_dash_stats = leaguedashplayerstats.LeagueDashPlayerStats(season='2023-24').get_data_frames()[0]
player_ids = league_dash_stats['PLAYER_ID'].tolist()

# Process data in batches
batch_size = 50
for i in range(0, len(player_ids), batch_size):
    batch_ids = player_ids[i:i + batch_size]
    all_players_info = pd.DataFrame()
    for pid in batch_ids:
        player_info = fetch_common_player_info(pid)
        all_players_info = pd.concat([all_players_info, player_info], ignore_index=True)
        time.sleep(1)  # Adding a delay to avoid hitting the API rate limit
    
    # Store batch to PostgreSQL
    all_players_info.to_sql('common_player_info', db_connection, if_exists='append', index=False)
    print(f'Processed and stored batch {i//batch_size + 1}')

print("Completed fetching and storing common player info.")
