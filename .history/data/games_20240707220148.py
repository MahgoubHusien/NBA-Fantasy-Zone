import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueGameLog, BoxScoreTraditionalV3, ScoreboardV2
from datetime import datetime

# Load environment variables from .env file
load_dotenv()

# Read connection parameters from environment variables
conn_params = {
    'dbname': os.getenv('DB_NAME'),
    'user': os.getenv('DB_USER'),
    'password': os.getenv('DB_PASSWORD'),
    'host': os.getenv('DB_HOST'),
    'port': int(os.getenv('DB_PORT'))
}

# Establish a connection to the database
conn = psycopg2.connect(**conn_params)
cursor = conn.cursor()

def fetch_games(season='2023-24', season_type='Regular Season'):
    game_log = LeagueGameLog(season=season, season_type_all_star=season_type)
    games_df = game_log.get_data_frames()[0]
    return games_df

def fetch_player_stats(game_id):
    boxscore_traditional = BoxScoreTraditionalV3(game_id=game_id)
    player_stats = boxscore_traditional.get_data_frames()[0]
    return player_stats

def calculate_fantasy_points(player_stats):
    player_stats['FantasyPoints'] = (
        player_stats['PTS'] + 
        player_stats['REB'] * 1.2 + 
        player_stats['AST'] * 1.5 + 
        player_stats['STL'] * 3 + 
        player_stats['BLK'] * 3 - 
        player_stats['TO'] * 1
    )
    return player_stats

def fetch_future_matchups(date):
    scoreboard = ScoreboardV2(game_date=date)
    games = scoreboard.get_data_frames()[1]
    return games

# Fetch games
games_df = fetch_games()

# Print columns to verify
print(games_df.columns)

# Get today's date
today = datetime.today().strftime('%Y-%m-%d')

# Iterate through each game
for index, game in games_df.iterrows():
    game_id = game['GAME_ID']
    game_date = game['GAME_DATE']

    # Insert game details
    cursor.execute("""
        INSERT INTO games (game_id, season_id, game_date, home_team_id, home_team_name, home_team_abbreviation, visitor_team_id, visitor_team_name, visitor_team_abbreviation, matchup, wl, min, pts, fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, oreb, dreb, reb, ast, stl, blk, "to", pf, plus_minus)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (game_id) DO NOTHING
    """, (
        game_id, game['SEASON_ID'], game_date, game['TEAM_ID'], game['TEAM_NAME'], game['TEAM_ABBREVIATION'], None, None, None,
        game['MATCHUP'], game['WL'], game['MIN'], game['PTS'], game['FGM'], game['FGA'], game['FG_PCT'], game['FG3M'],
        game['FG3A'], game['FG3_PCT'], game['FTM'], game['FTA'], game['FT_PCT'], game['OREB'], game['DREB'], game['REB'],
        game['AST'], game['STL'], game['BLK'], game.get('TO', None), game['PF'], game['PLUS_MINUS']
    ))
    
    if game_date < today:
        # Fetch and process player stats for past games
        player_stats = fetch_player_stats(game_id)
        player_stats = calculate_fantasy_points(player_stats)
        
        # Print columns to verify
        print(player_stats.columns)
        
        # Insert player stats
        for _, player in player_stats.iterrows():
            cursor.execute("""
                INSERT INTO player_stats (game_id, player_id, player_name, team_id, team_abbreviation, min, pts, reb, ast, stl, blk, "to", fgm, fga, fg_pct, fg3m, fg3a, fg3_pct, ftm, fta, ft_pct, fantasy_points)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (game_id, player_id) DO NOTHING
            """, (
                game_id, player['PLAYER_ID'], player['PLAYER_NAME'], player['TEAM_ID'], player['TEAM_ABBREVIATION'],
                player['MIN'], player['PTS'], player['REB'], player['AST'], player['STL'], player['BLK'], player.get('TO', None),
                player['FGM'], player['FGA'], player['FG_PCT'], player['FG3M'], player['FG3A'], player['FG3_PCT'],
                player['FTM'], player['FTA'], player['FT_PCT'], player['FantasyPoints']
            ))
    else:
        # Fetch matchups for future games
        future_games = fetch_future_matchups(game_date)
        
        # Insert future matchups
        for _, future_game in future_games.iterrows():
            cursor.execute("""
                INSERT INTO future_matchups (game_id, game_date, home_team_id, visitor_team_id, home_team_abbreviation, visitor_team_abbreviation, home_team_name, visitor_team_name)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
                ON CONFLICT (game_id) DO NOTHING
            """, (
                future_game['GAME_ID'], future_game['GAME_DATE'], future_game['HOME_TEAM_ID'], future_game['VISITOR_TEAM_ID'],
                future_game['HOME_TEAM_ABBREVIATION'], future_game['VISITOR_TEAM_ABBREVIATION'], future_game['HOME_TEAM_NAME'],
                future_game['VISITOR_TEAM_NAME']
            ))

# Commit the transactions
conn.commit()

# Close the connection
cursor.close()
conn.close()

print("Data inserted into PostgreSQL database successfully.")
