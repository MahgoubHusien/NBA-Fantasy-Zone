import os
import pandas as pd
import psycopg2
from dotenv import load_dotenv
from nba_api.stats.endpoints import LeagueStandingsV3

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

def check_and_create_tables(conn):
    create_standings_table_query = """
    CREATE TABLE IF NOT EXISTS standings (
        league_id VARCHAR(10),
        season_id VARCHAR(10),
        team_id INT PRIMARY KEY,
        team_city VARCHAR(50),
        team_name VARCHAR(100),
        team_slug VARCHAR(50),
        conference VARCHAR(50),
        conference_record VARCHAR(10),
        playoff_rank INT,
        clinch_indicator VARCHAR(10),
        division VARCHAR(50),
        division_record VARCHAR(10),
        division_rank INT,
        wins INT,
        losses INT,
        win_pct FLOAT,
        league_rank INT,
        record VARCHAR(10),
        home VARCHAR(10),
        road VARCHAR(10),
        l10 VARCHAR(10),
        last_10_home VARCHAR(10),
        last_10_road VARCHAR(10),
        ot INT,
        three_pts_or_less INT,
        ten_pts_or_more INT,
        long_home_streak INT,
        str_long_home_streak VARCHAR(10),
        long_road_streak INT,
        str_long_road_streak VARCHAR(10),
        long_win_streak INT,
        long_loss_streak INT,
        current_home_streak INT,
        str_current_home_streak VARCHAR(10),
        current_road_streak INT,
        str_current_road_streak VARCHAR(10),
        current_streak VARCHAR(10),
        str_current_streak VARCHAR(10),
        conference_games_back FLOAT,
        division_games_back FLOAT,
        clinched_conference_title BOOLEAN,
        clinched_division_title BOOLEAN,
        clinched_playoff_birth BOOLEAN,
        eliminated_conference BOOLEAN,
        eliminated_division BOOLEAN,
        ahead_at_half INT,
        behind_at_half INT,
        tied_at_half INT,
        ahead_at_third INT,
        behind_at_third INT,
        tied_at_third INT,
        score_100_pts BOOLEAN,
        opp_score_100_pts BOOLEAN,
        opp_over_500 BOOLEAN,
        lead_in_fg_pct BOOLEAN,
        lead_in_reb BOOLEAN,
        fewer_turnovers BOOLEAN,
        points_pg FLOAT,
        opp_points_pg FLOAT,
        diff_points_pg FLOAT,
        vs_east VARCHAR(10),
        vs_atlantic VARCHAR(10),
        vs_central VARCHAR(10),
        vs_southeast VARCHAR(10),
        vs_west VARCHAR(10),
        vs_northwest VARCHAR(10),
        vs_pacific VARCHAR(10),
        vs_southwest VARCHAR(10),
        jan VARCHAR(10),
        feb VARCHAR(10),
        mar VARCHAR(10),
        apr VARCHAR(10),
        may VARCHAR(10),
        jun VARCHAR(10),
        jul VARCHAR(10),
        aug VARCHAR(10),
        sep VARCHAR(10),
        oct VARCHAR(10),
        nov VARCHAR(10),
        dec VARCHAR(10),
        return_to_play_east_pi_flag BOOLEAN,
        return_to_play_west_pi_flag BOOLEAN,
        return_to_play_already_eliminated BOOLEAN,
        seeding_game_1_outcome VARCHAR(10),
        seeding_game_2_outcome VARCHAR(10),
        seeding_game_3_outcome VARCHAR(10),
        seeding_game_4_outcome VARCHAR(10),
        seeding_game_5_outcome VARCHAR(10),
        seeding_game_6_outcome VARCHAR(10),
        seeding_game_7_outcome VARCHAR(10),
        seeding_game_8_outcome VARCHAR(10),
        seeding_game_1_id VARCHAR(10),
        seeding_game_2_id VARCHAR(10),
        seeding_game_3_id VARCHAR(10),
        seeding_game_4_id VARCHAR(10),
        seeding_game_5_id VARCHAR(10),
        seeding_game_6_id VARCHAR(10),
        seeding_game_7_id VARCHAR(10),
        seeding_game_8_id VARCHAR(10),
        seeding_game_1_opponent VARCHAR(100),
        seeding_game_2_opponent VARCHAR(100),
        seeding_game_3_opponent VARCHAR(100),
        seeding_game_4_opponent VARCHAR(100),
        seeding_game_5_opponent VARCHAR(100),
        seeding_game_6_opponent VARCHAR(100),
        seeding_game_7_opponent VARCHAR(100),
        seeding_game_8_opponent VARCHAR(100),
        seeding_game_1_label VARCHAR(100),
        seeding_game_2_label VARCHAR(100),
        seeding_game_3_label VARCHAR(100),
        seeding_game_4_label VARCHAR(100),
        seeding_game_5_label VARCHAR(100),
        seeding_game_6_label VARCHAR(100),
        seeding_game_7_label VARCHAR(100),
        seeding_game_8_label VARCHAR(100)
    );
    """
    
    with conn.cursor() as cur:
        cur.execute(create_standings_table_query)
        conn.commit()
    print("Table `standings` checked and created if not exists.")

check_and_create_tables(conn)

def fetch_and_insert_standings(conn):
    try:
        # Fetch standings
        standings = LeagueStandingsV3(season='2023-24').standings.get_data_frame()

        query_standings = """
        INSERT INTO standings (
            league_id, season_id, team_id, team_city, team_name, team_slug, conference, conference_record, 
            playoff_rank, clinch_indicator, division, division_record, division_rank, wins, losses, win_pct, 
            league_rank, record, home, road, l10, last_10_home, last_10_road, ot, three_pts_or_less, ten_pts_or_more, 
            long_home_streak, str_long_home_streak, long_road_streak, str_long_road_streak, long_win_streak, 
            long_loss_streak, current_home_streak, str_current_home_streak, current_road_streak, str_current_road_streak, 
            current_streak, str_current_streak, conference_games_back, division_games_back, clinched_conference_title, 
            clinched_division_title, clinched_playoff_birth, eliminated_conference, eliminated_division, ahead_at_half, 
            behind_at_half, tied_at_half, ahead_at_third, behind_at_third, tied_at_third, score_100_pts, opp_score_100_pts, 
            opp_over_500, lead_in_fg_pct, lead_in_reb, fewer_turnovers, points_pg, opp_points_pg, diff_points_pg, vs_east, 
            vs_atlantic, vs_central, vs_southeast, vs_west, vs_northwest, vs_pacific, vs_southwest, jan, feb, mar, apr, may, 
            jun, jul, aug, sep, oct, nov, dec, return_to_play_east_pi_flag, return_to_play_west_pi_flag, 
            return_to_play_already_eliminated, seeding_game_1_outcome, seeding_game_2_outcome, seeding_game_3_outcome, 
            seeding_game_4_outcome, seeding_game_5_outcome, seeding_game_6_outcome, seeding_game_7_outcome, 
            seeding_game_8_outcome, seeding_game_1_id, seeding_game_2_id, seeding_game_3_id, seeding_game_4_id, 
            seeding_game_5_id, seeding_game_6_id, seeding_game_7_id, seeding_game_8_id, seeding_game_1_opponent, 
            seeding_game_2_opponent, seeding_game_3_opponent, seeding_game_4_opponent, seeding_game_5_opponent, 
            seeding_game_6_opponent, seeding_game_7_opponent, seeding_game_8_opponent, seeding_game_1_label, 
            seeding_game_2_label, seeding_game_3_label, seeding_game_4_label, seeding_game_5_label, seeding_game_6_label, 
            seeding_game_7_label, seeding_game_8_label
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 
                  %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 
                  %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 
                  %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON CONFLICT (team_id) DO UPDATE SET
            league_id = EXCLUDED.league_id,
            season_id = EXCLUDED.season_id,
            team_city = EXCLUDED.team_city,
            team_name = EXCLUDED.team_name,
            team_slug = EXCLUDED.team_slug,
            conference = EXCLUDED.conference,
            conference_record = EXCLUDED.conference_record,
            playoff_rank = EXCLUDED.playoff_rank,
            clinch_indicator = EXCLUDED.clinch_indicator,
            division = EXCLUDED.division,
            division_record = EXCLUDED.division_record,
            division_rank = EXCLUDED.division_rank,
            wins = EXCLUDED.wins,
            losses = EXCLUDED.losses,
            win_pct = EXCLUDED.win_pct,
            league_rank = EXCLUDED.league_rank,
            record = EXCLUDED.record,
            home = EXCLUDED.home,
            road = EXCLUDED.road,
            l10 = EXCLUDED.l10,
            last_10_home = EXCLUDED.last_10_home,
            last_10_road = EXCLUDED.last_10_road,
            ot = EXCLUDED.ot,
            three_pts_or_less = EXCLUDED.three_pts_or_less,
            ten_pts_or_more = EXCLUDED.ten_pts_or_more,
            long_home_streak = EXCLUDED.long_home_streak,
            str_long_home_streak = EXCLUDED.str_long_home_streak,
            long_road_streak = EXCLUDED.long_road_streak,
            str_long_road_streak = EXCLUDED.str_long_road_streak,
            long_win_streak = EXCLUDED.long_win_streak,
            long_loss_streak = EXCLUDED.long_loss_streak,
            current_home_streak = EXCLUDED.current_home_streak,
            str_current_home_streak = EXCLUDED.str_current_home_streak,
            current_road_streak = EXCLUDED.current_road_streak,
            str_current_road_streak = EXCLUDED.str_current_road_streak,
            current_streak = EXCLUDED.current_streak,
            str_current_streak = EXCLUDED.str_current_streak,
            conference_games_back = EXCLUDED.conference_games_back,
            division_games_back = EXCLUDED.division_games_back,
            clinched_conference_title = EXCLUDED.clinched_conference_title,
            clinched_division_title = EXCLUDED.clinched_division_title,
            clinched_playoff_birth = EXCLUDED.clinched_playoff_birth,
            eliminated_conference = EXCLUDED.eliminated_conference,
            eliminated_division = EXCLUDED.eliminated_division,
            ahead_at_half = EXCLUDED.ahead_at_half,
            behind_at_half = EXCLUDED.behind_at_half,
            tied_at_half = EXCLUDED.tied_at_half,
            ahead_at_third = EXCLUDED.ahead_at_third,
            behind_at_third = EXCLUDED.behind_at_third,
            tied_at_third = EXCLUDED.tied_at_third,
            score_100_pts = EXCLUDED.score_100_pts,
            opp_score_100_pts = EXCLUDED.opp_score_100_pts,
            opp_over_500 = EXCLUDED.opp_over_500,
            lead_in_fg_pct = EXCLUDED.lead_in_fg_pct,
            lead_in_reb = EXCLUDED.lead_in_reb,
            fewer_turnovers = EXCLUDED.fewer_turnovers,
            points_pg = EXCLUDED.points_pg,
            opp_points_pg = EXCLUDED.opp_points_pg,
            diff_points_pg = EXCLUDED.diff_points_pg,
            vs_east = EXCLUDED.vs_east,
            vs_atlantic = EXCLUDED.vs_atlantic,
            vs_central = EXCLUDED.vs_central,
            vs_southeast = EXCLUDED.vs_southeast,
            vs_west = EXCLUDED.vs_west,
            vs_northwest = EXCLUDED.vs_northwest,
            vs_pacific = EXCLUDED.vs_pacific,
            vs_southwest = EXCLUDED.vs_southwest,
            jan = EXCLUDED.jan,
            feb = EXCLUDED.feb,
            mar = EXCLUDED.mar,
            apr = EXCLUDED.apr,
            may = EXCLUDED.may,
            jun = EXCLUDED.jun,
            jul = EXCLUDED.jul,
            aug = EXCLUDED.aug,
            sep = EXCLUDED.sep,
            oct = EXCLUDED.oct,
            nov = EXCLUDED.nov,
            dec = EXCLUDED.dec,
            return_to_play_east_pi_flag = EXCLUDED.return_to_play_east_pi_flag,
            return_to_play_west_pi_flag = EXCLUDED.return_to_play_west_pi_flag,
            return_to_play_already_eliminated = EXCLUDED.return_to_play_already_eliminated,
            seeding_game_1_outcome = EXCLUDED.seeding_game_1_outcome,
            seeding_game_2_outcome = EXCLUDED.seeding_game_2_outcome,
            seeding_game_3_outcome = EXCLUDED.seeding_game_3_outcome,
            seeding_game_4_outcome = EXCLUDED.seeding_game_4_outcome,
            seeding_game_5_outcome = EXCLUDED.seeding_game_5_outcome,
            seeding_game_6_outcome = EXCLUDED.seeding_game_6_outcome,
            seeding_game_7_outcome = EXCLUDED.seeding_game_7_outcome,
            seeding_game_8_outcome = EXCLUDED.seeding_game_8_outcome,
            seeding_game_1_id = EXCLUDED.seeding_game_1_id,
            seeding_game_2_id = EXCLUDED.seeding_game_2_id,
            seeding_game_3_id = EXCLUDED.seeding_game_3_id,
            seeding_game_4_id = EXCLUDED.seeding_game_4_id,
            seeding_game_5_id = EXCLUDED.seeding_game_5_id,
            seeding_game_6_id = EXCLUDED.seeding_game_6_id,
            seeding_game_7_id = EXCLUDED.seeding_game_7_id,
            seeding_game_8_id = EXCLUDED.seeding_game_8_id,
            seeding_game_1_opponent = EXCLUDED.seeding_game_1_opponent,
            seeding_game_2_opponent = EXCLUDED.seeding_game_2_opponent,
            seeding_game_3_opponent = EXCLUDED.seeding_game_3_opponent,
            seeding_game_4_opponent = EXCLUDED.seeding_game_4_opponent,
            seeding_game_5_opponent = EXCLUDED.seeding_game_5_opponent,
            seeding_game_6_opponent = EXCLUDED.seeding_game_6_opponent,
            seeding_game_7_opponent = EXCLUDED.seeding_game_7_opponent,
            seeding_game_8_opponent = EXCLUDED.seeding_game_8_opponent,
            seeding_game_1_label = EXCLUDED.seeding_game_1_label,
            seeding_game_2_label = EXCLUDED.seeding_game_2_label,
            seeding_game_3_label = EXCLUDED.seeding_game_3_label,
            seeding_game_4_label = EXCLUDED.seeding_game_4_label,
            seeding_game_5_label = EXCLUDED.seeding_game_5_label,
            seeding_game_6_label = EXCLUDED.seeding_game_6_label,
            seeding_game_7_label = EXCLUDED.seeding_game_7_label,
            seeding_game_8_label = EXCLUDED.seeding_game_8_label;
        """
        
        with conn.cursor() as cur:
            for index, row in standings.iterrows():
                cur.execute(query_standings, (
                    row['LeagueID'], row['SeasonID'], row['TeamID'], row['TeamCity'], row['TeamName'], 
                    row['TeamSlug'], row['Conference'], row['ConferenceRecord'], row['PlayoffRank'], 
                    row['ClinchIndicator'], row['Division'], row['DivisionRecord'], row['DivisionRank'], 
                    row['WINS'], row['LOSSES'], row['WinPCT'], row['LeagueRank'], row['Record'], 
                    row['HOME'], row['ROAD'], row['L10'], row['Last10Home'], row['Last10Road'], 
                    row['OT'], row['ThreePTSOrLess'], row['TenPTSOrMore'], row['LongHomeStreak'], 
                    row['strLongHomeStreak'], row['LongRoadStreak'], row['strLongRoadStreak'], 
                    row['LongWinStreak'], row['LongLossStreak'], row['CurrentHomeStreak'], 
                    row['strCurrentHomeStreak'], row['CurrentRoadStreak'], row['strCurrentRoadStreak'], 
                    row['CurrentStreak'], row['strCurrentStreak'], row['ConferenceGamesBack'], 
                    row['DivisionGamesBack'], row['ClinchedConferenceTitle'], row['ClinchedDivisionTitle'], 
                    row['ClinchedPlayoffBirth'], row['EliminatedConference'], row['EliminatedDivision'], 
                    row['AheadAtHalf'], row['BehindAtHalf'], row['TiedAtHalf'], row['AheadAtThird'], 
                    row['BehindAtThird'], row['TiedAtThird'], row['Score100PTS'], row['OppScore100PTS'], 
                    row['OppOver500'], row['LeadInFGPCT'], row['LeadInReb'], row['FewerTurnovers'], 
                    row['PointsPG'], row['OppPointsPG'], row['DiffPointsPG'], row['vsEast'], 
                    row['vsAtlantic'], row['vsCentral'], row['vsSoutheast'], row['vsWest'], 
                    row['vsNorthwest'], row['vsPacific'], row['vsSouthwest'], row['Jan'], row['Feb'], 
                    row['Mar'], row['Apr'], row['May'], row['Jun'], row['Jul'], row['Aug'], row['Sep'], 
                    row['Oct'], row['Nov'], row['Dec'], row['ReturnToPlay_East_PI_Flag'], 
                    row['ReturnToPlay_West_PI_Flag'], row['ReturnToPlay_Already_Eliminated'], 
                    row['Seeding_Game_1_Outcome'], row['Seeding_Game_2_Outcome'], row['Seeding_Game_3_Outcome'], 
                    row['Seeding_Game_4_Outcome'], row['Seeding_Game_5_Outcome'], row['Seeding_Game_6_Outcome'], 
                    row['Seeding_Game_7_Outcome'], row['Seeding_Game_8_Outcome'], row['Seeding_Game_1_ID'], 
                    row['Seeding_Game_2_ID'], row['Seeding_Game_3_ID'], row['Seeding_Game_4_ID'], 
                    row['Seeding_Game_5_ID'], row['Seeding_Game_6_ID'], row['Seeding_Game_7_ID'], 
                    row['Seeding_Game_8_ID'], row['Seeding_Game_1_Opponent'], row['Seeding_Game_2_Opponent'], 
                    row['Seeding_Game_3_Opponent'], row['Seeding_Game_4_Opponent'], row['Seeding_Game_5_Opponent'], 
                    row['Seeding_Game_6_Opponent'], row['Seeding_Game_7_Opponent'], row['Seeding_Game_8_Opponent'], 
                    row['Seeding_Game_1_Label'], row['Seeding_Game_2_Label'], row['Seeding_Game_3_Label'], 
                    row['Seeding_Game_4_Label'], row['Seeding_Game_5_Label'], row['Seeding_Game_6_Label'], 
                    row['Seeding_Game_7_Label'], row['Seeding_Game_8_Label']
                ))
            conn.commit()
        print("Inserted/Updated standings.")
    except Exception as e:
        print(f"Error fetching or inserting standings: {e}")
        conn.rollback()  # Rollback the transaction on error

fetch_and_insert_standings(conn)

def filter_and_delete_unnecessary_data(conn):
    try:
        with conn.cursor() as cur:
            delete_query = """
            DELETE FROM standings
            WHERE league_id IS NULL OR season_id IS NULL;
            """x
            cur.execute(delete_query)
            conn.commit()
        print("Filtered and deleted unnecessary data.")
    except Exception as e:
        print(f"Error filtering or deleting data: {e}")
        conn.rollback()  # Rollback the transaction on error

filter_and_delete_unnecessary_data(conn)

conn.close()
