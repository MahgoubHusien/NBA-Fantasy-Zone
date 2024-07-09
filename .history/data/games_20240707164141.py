from nba_api.stats.endpoints import LeagueGameLog
import pandas as pd

# Specify the parameters
season = '2023-24'
season_type = 'Regular Season'

# Fetch the game logs
game_log = LeagueGameLog(season=season, season_type_all_star=season_type)
games_df = game_log.get_data_frames()[0]  # Get the first DataFrame

# Display the DataFrame
print(games_df)

# Optionally, save the DataFrame to a CSV file
games_df.to_csv('games_log.csv', index=False)
