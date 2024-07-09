from nba_api.stats.endpoints import LeagueGameLog, BoxScoreTraditionalV3
import pandas as pd

def fetch_games(season='2023-24', season_type='Regular Season'):
    # Fetch the game logs
    game_log = LeagueGameLog(season=season, season_type_all_star=season_type)
    games_df = game_log.get_data_frames()[0]  # Get the first DataFrame
    return games_df

def fetch_player_stats(game_id):
    # Fetch traditional box score statistics
    boxscore_traditional = BoxScoreTraditionalV3(game_id=game_id)
    player_stats = boxscore_traditional.get_data_frames()[0]  # Get the first DataFrame
    return player_stats

def calculate_fantasy_points(player_stats):
    # Calculate fantasy points based on common fantasy basketball scoring systems
    player_stats['FantasyPoints'] = (
        player_stats['PTS'] + 
        player_stats['REB'] * 1.2 + 
        player_stats['AST'] * 1.5 + 
        player_stats['STL'] * 3 + 
        player_stats['BLK'] * 3 - 
        player_stats['TO'] * 1
    )
    return player_stats

# Fetch games
games_df = fetch_games()

# Prepare an empty DataFrame to hold all player stats
all_player_stats = pd.DataFrame()

# Iterate through each game and fetch player stats
for game_id in games_df['GAME_ID']:
    player_stats = fetch_player_stats(game_id)
    player_stats = calculate_fantasy_points(player_stats)
    all_player_stats = pd.concat([all_player_stats, player_stats], ignore_index=True)

# Display combined player stats with fantasy points
print(all_player_stats)

# Optionally, save the combined player stats to a CSV file
all_player_stats.to_csv('player_stats_with_fantasy_points.csv', index=False)
