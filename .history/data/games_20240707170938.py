from nba_api.stats.endpoints import CommonTeamRoster

# Specify the team ID and season
team_id = '1610612747'  # Example: Los Angeles Lakers
season = '2023-24'

# Fetch the team roster and coaches
team_roster = CommonTeamRoster(team_id=team_id, season=season)
coaches_df = team_roster.get_data_frames()[1]  # The second DataFrame contains the coaches

# Display the coaches DataFrame
print(coaches_df)
