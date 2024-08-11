package com.nba.nba_zone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "standings")
public class Standings {

    @Id
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "league_id")
    private String leagueId;

    @Column(name = "season_id")
    private String seasonId;

    @Column(name = "team_city")
    private String teamCity;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_slug")
    private String teamSlug;

    @Column(name = "conference")
    private String conference;

    @Column(name = "conference_record")
    private String conferenceRecord;

    @Column(name = "playoff_rank")
    private int playoffRank;

    @Column(name = "clinch_indicator")
    private String clinchIndicator;

    @Column(name = "division")
    private String division;

    @Column(name = "division_record")
    private String divisionRecord;

    @Column(name = "division_rank")
    private int divisionRank;

    @Column(name = "wins")
    private int wins;

    @Column(name = "losses")
    private int losses;

    @Column(name = "win_pct")
    private float winPct;

    @Column(name = "league_rank")
    private Integer leagueRank;

    @Column(name = "record")
    private String record;

    @Column(name = "home")
    private String home;

    @Column(name = "road")
    private String road;

    @Column(name = "l10")
    private String lastTen;

    @Column(name = "long_win_streak")
    private int longWinStreak;

    @Column(name = "long_loss_streak")
    private int longLossStreak;

    @Column(name = "current_streak")
    private String currentStreak;

    @Column(name = "conference_games_back")
    private float conferenceGamesBack;

    @Column(name = "clinched_conference_title")
    private int clinchedConferenceTitle;

    @Column(name = "clinched_playoff_birth")
    private int clinchedPlayoffBirth;

    @Column(name = "clinched_playin")
    private int clinchedPlayin;

    @Column(name = "eliminated_conference")
    private int eliminatedConference;

    @Column(name = "points_pg")
    private float pointsPerGame;

    @Column(name = "opp_points_pg")
    private float opponentPointsPerGame;

    @Column(name = "diff_points_pg")
    private float differencePointsPerGame;

    // No-argument constructor
    public Standings() {
    }

    // Constructor with all fields
    public Standings(int teamId, String leagueId, String seasonId, String teamCity, String teamName, String teamSlug,
                     String conference, String conferenceRecord, int playoffRank, String clinchIndicator, String division,
                     String divisionRecord, int divisionRank, int wins, int losses, float winPct, Integer leagueRank,
                     String record, String home, String road, String lastTen, int longWinStreak, int longLossStreak,
                     String currentStreak, float conferenceGamesBack, int clinchedConferenceTitle, int clinchedPlayoffBirth,
                     int clinchedPlayin, int eliminatedConference, float pointsPerGame, float opponentPointsPerGame,
                     float differencePointsPerGame) {
        this.teamId = teamId;
        this.leagueId = leagueId;
        this.seasonId = seasonId;
        this.teamCity = teamCity;
        this.teamName = teamName;
        this.teamSlug = teamSlug;
        this.conference = conference;
        this.conferenceRecord = conferenceRecord;
        this.playoffRank = playoffRank;
        this.clinchIndicator = clinchIndicator;
        this.division = division;
        this.divisionRecord = divisionRecord;
        this.divisionRank = divisionRank;
        this.wins = wins;
        this.losses = losses;
        this.winPct = winPct;
        this.leagueRank = leagueRank;
        this.record = record;
        this.home = home;
        this.road = road;
        this.lastTen = lastTen;
        this.longWinStreak = longWinStreak;
        this.longLossStreak = longLossStreak;
        this.currentStreak = currentStreak;
        this.conferenceGamesBack = conferenceGamesBack;
        this.clinchedConferenceTitle = clinchedConferenceTitle;
        this.clinchedPlayoffBirth = clinchedPlayoffBirth;
        this.clinchedPlayin = clinchedPlayin;
        this.eliminatedConference = eliminatedConference;
        this.pointsPerGame = pointsPerGame;
        this.opponentPointsPerGame = opponentPointsPerGame;
        this.differencePointsPerGame = differencePointsPerGame;
    }


    // Getters and Setters

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamSlug() {
        return teamSlug;
    }

    public void setTeamSlug(String teamSlug) {
        this.teamSlug = teamSlug;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getConferenceRecord() {
        return conferenceRecord;
    }

    public void setConferenceRecord(String conferenceRecord) {
        this.conferenceRecord = conferenceRecord;
    }

    public int getPlayoffRank() {
        return playoffRank;
    }

    public void setPlayoffRank(int playoffRank) {
        this.playoffRank = playoffRank;
    }

    public String getClinchIndicator() {
        return clinchIndicator;
    }

    public void setClinchIndicator(String clinchIndicator) {
        this.clinchIndicator = clinchIndicator;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivisionRecord() {
        return divisionRecord;
    }

    public void setDivisionRecord(String divisionRecord) {
        this.divisionRecord = divisionRecord;
    }

    public int getDivisionRank() {
        return divisionRank;
    }

    public void setDivisionRank(int divisionRank) {
        this.divisionRank = divisionRank;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public float getWinPct() {
        return winPct;
    }

    public void setWinPct(float winPct) {
        this.winPct = winPct;
    }

    public Integer getLeagueRank() {
        return leagueRank;
    }

    public void setLeagueRank(Integer leagueRank) {
        this.leagueRank = leagueRank;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getLastTen() {
        return lastTen;
    }

    public void setLastTen(String lastTen) {
        this.lastTen = lastTen;
    }

    public int getLongWinStreak() {
        return longWinStreak;
    }

    public void setLongWinStreak(int longWinStreak) {
        this.longWinStreak = longWinStreak;
    }

    public int getLongLossStreak() {
        return longLossStreak;
    }

    public void setLongLossStreak(int longLossStreak) {
        this.longLossStreak = longLossStreak;
    }

    public String getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(String currentStreak) {
        this.currentStreak = currentStreak;
    }

    public float getConferenceGamesBack() {
        return conferenceGamesBack;
    }

    public void setConferenceGamesBack(float conferenceGamesBack) {
        this.conferenceGamesBack = conferenceGamesBack;
    }

    public int getClinchedConferenceTitle() {
        return clinchedConferenceTitle;
    }

    public void setClinchedConferenceTitle(int clinchedConferenceTitle) {
        this.clinchedConferenceTitle = clinchedConferenceTitle;
    }

    public int getClinchedPlayoffBirth() {
        return clinchedPlayoffBirth;
    }

    public void setClinchedPlayoffBirth(int clinchedPlayoffBirth) {
        this.clinchedPlayoffBirth = clinchedPlayoffBirth;
    }

    public int getClinchedPlayin() {
        return clinchedPlayin;
    }

    public void setClinchedPlayin(int clinchedPlayin) {
        this.clinchedPlayin = clinchedPlayin;
    }

    public int getEliminatedConference() {
        return eliminatedConference;
    }

    public void setEliminatedConference(int eliminatedConference) {
        this.eliminatedConference = eliminatedConference;
    }

    public float getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(float pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public float getOpponentPointsPerGame() {
        return opponentPointsPerGame;
    }

    public void setOpponentPointsPerGame(float opponentPointsPerGame) {
        this.opponentPointsPerGame = opponentPointsPerGame;
    }

    public float getDifferencePointsPerGame() {
        return differencePointsPerGame;
    }

    public void setDifferencePointsPerGame(float differencePointsPerGame) {
        this.differencePointsPerGame = differencePointsPerGame;
    }
}
