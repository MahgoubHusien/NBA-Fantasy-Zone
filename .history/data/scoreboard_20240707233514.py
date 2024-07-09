-- Table structure for game_header
CREATE TABLE IF NOT EXISTS game_header (
    game_id VARCHAR(20) PRIMARY KEY,
    game_date_est DATE,
    game_sequence INT,
    game_status_id INT,
    game_status_text VARCHAR(50),
    home_team_id INT,
    visitor_team_id INT,
    season VARCHAR(10),
    live_period INT,
    live_pc_time VARCHAR(20),
    natl_tv_broadcaster_abbreviation VARCHAR(50),
    home_tv_broadcaster_abbreviation VARCHAR(50),
    away_tv_broadcaster_abbreviation VARCHAR(50),
    live_period_time_bcast VARCHAR(20),
    arena_name VARCHAR(100),
    wh_status INT,
    wnba_commissioner_flag INT
);

-- Table structure for line_score
CREATE TABLE IF NOT EXISTS line_score (
    game_id VARCHAR(20),
    team_id INT,
    team_abbreviation VARCHAR(10),
    team_city_name VARCHAR(50),
    team_name VARCHAR(50),
    pts_qtr1 INT,
    pts_qtr2 INT,
    pts_qtr3 INT,
    pts_qtr4 INT,
    pts_ot1 INT,
    pts_ot2 INT,
    pts_ot3 INT,
    pts_ot4 INT,
    pts_ot5 INT,
    pts_ot6 INT,
    pts_ot7 INT,
    pts_ot8 INT,
    pts_ot9 INT,
    pts_ot10 INT,
    pts INT,
    fg_pct FLOAT,
    ft_pct FLOAT,
    fg3_pct FLOAT,
    ast INT,
    reb INT,
    tov INT,
    PRIMARY KEY (game_id, team_id)
);

-- Table structure for eastern_conf_standings
CREATE TABLE IF NOT EXISTS eastern_conf_standings (
    team_id INT PRIMARY KEY,
    league_id VARCHAR(10),
    standings_date DATE,
    conference VARCHAR(10),
    team VARCHAR(50),
    g INT,
    w INT,
    l INT,
    w_pct FLOAT,
    home_record VARCHAR(10),
    road_record VARCHAR(10)
);

-- Table structure for western_conf_standings
CREATE TABLE IF NOT EXISTS western_conf_standings (
    team_id INT PRIMARY KEY,
    league_id VARCHAR(10),
    standings_date DATE,
    conference VARCHAR(10),
    team VARCHAR(50),
    g INT,
    w INT,
    l INT,
    w_pct FLOAT,
    home_record VARCHAR(10),
    road_record VARCHAR(10)
);
