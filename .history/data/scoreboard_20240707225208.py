from nba_api.stats.endpoints import ScoreboardV2
import pandas as pd
from datetime import datetime

# Define the date for which you want to fetch the scoreboard data
game_date = '2023-04-15'  # Example date, you can set it to any date you need

# Fetch the scoreboard data
scoreboard = ScoreboardV2(game_date=game_date)

# Get the different data frames
game_header = scoreboard.game_header.get_data_frame()
line_score = scoreboard.line_score.get_data_frame()
series_standings = scoreboard.series_standings.get_data_frame()
last_meeting = scoreboard.last_meeting.get_data_frame()
east_conf_standings_by_day = scoreboard.east_conf_standings_by_day.get_data_frame()
west_conf_standings_by_day = scoreboard.west_conf_standings_by_day.get_data_frame()
available = scoreboard.available.get_data_frame()

# Print the data frames
print("Game Header:")
print(game_header.head())

print("\nLine Score:")
print(line_score.head())

print("\nSeries Standings:")
print(series_standings.head())

print("\nLast Meeting:")
print(last_meeting.head())

print("\nEast Conference Standings By Day:")
print(east_conf_standings_by_day.head())

print("\nWest Conference Standings By Day:")
print(west_conf_standings_by_day.head())

print("\nAvailable:")
print(available.head())
