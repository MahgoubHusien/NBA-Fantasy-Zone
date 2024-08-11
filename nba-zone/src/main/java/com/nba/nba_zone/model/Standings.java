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
    private Integer teamId;

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
    private Integer playoffRank;

    @Column(name = "clinch_indicator")
    private String clinchIndicator;

    @Column(name = "division")
    private String division;

    @Column(name = "division_record")
    private String divisionRecord;

    @Column(name = "division_rank")
    private Integer divisionRank;

    @Column(name = "wins")
    private Integer wins;

    @Column(name = "losses")
    private Integer losses;

    @Column(name = "win_pct")
    private Double winPct;

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
    private Integer longWinStreak;

    @Column(name = "long_loss_streak")
    private Integer longLossStreak;

    @Column(name = "current_streak")
    private String currentStreak;

    @Column(name = "conference_games_back")
    private Double conferenceGamesBack;

    @Column(name = "clinched_conference_title")
    private Integer clinchedConferenceTitle;

    @Column(name = "clinched_playoff_birth")
    private Integer clinchedPlayoffBirth;

    @Column(name = "clinched_playin")
    private Integer clinchedPlayin;

    @Column(name = "eliminated_conference")
    private Integer eliminatedConference;

    @Column(name = "points_pg")
    private Double pointsPerGame;

    @Column(name = "opp_points_pg")
    private Double opponentPointsPerGame;

    @Column(name = "diff_points_pg")
    private Double differencePointsPerGame;

    @Column(name = "logo_url")
    private String logoUrl;

    // No-argument constructor
    public Standings() {
    }

    // Constructor with all fields
    public Standings(Integer teamId, String leagueId, String seasonId, String teamCity, String teamName, String teamSlug,
                     String conference, String conferenceRecord, Integer playoffRank, String clinchIndicator, String division,
                     String divisionRecord, Integer divisionRank, Integer wins, Integer losses, Double winPct, Integer leagueRank,
                     String record, String home, String road, String lastTen, Integer longWinStreak, Integer longLossStreak,
                     String currentStreak, Double conferenceGamesBack, Integer clinchedConferenceTitle, Integer clinchedPlayoffBirth,
                     Integer clinchedPlayin, Integer eliminatedConference, Double pointsPerGame, Double opponentPointsPerGame,
                     Double differencePointsPerGame, String logoUrl) {
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
        this.logoUrl = logoUrl;
    }


    // Getters and Setters

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
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

    public Integer getPlayoffRank() {
        return playoffRank;
    }

    public void setPlayoffRank(Integer playoffRank) {
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

    public Integer getDivisionRank() {
        return divisionRank;
    }

    public void setDivisionRank(Integer divisionRank) {
        this.divisionRank = divisionRank;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Double getWinPct() {
        return winPct;
    }

    public void setWinPct(Double winPct) {
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

    public Integer getLongWinStreak() {
        return longWinStreak;
    }

    public void setLongWinStreak(Integer longWinStreak) {
        this.longWinStreak = longWinStreak;
    }

    public Integer getLongLossStreak() {
        return longLossStreak;
    }

    public void setLongLossStreak(Integer longLossStreak) {
        this.longLossStreak = longLossStreak;
    }

    public String getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(String currentStreak) {
        this.currentStreak = currentStreak;
    }

    public Double getConferenceGamesBack() {
        return conferenceGamesBack;
    }

    public void setConferenceGamesBack(Double conferenceGamesBack) {
        this.conferenceGamesBack = conferenceGamesBack;
    }

    public Integer getClinchedConferenceTitle() {
        return clinchedConferenceTitle;
    }

    public void setClinchedConferenceTitle(Integer clinchedConferenceTitle) {
        this.clinchedConferenceTitle = clinchedConferenceTitle;
    }

    public Integer getClinchedPlayoffBirth() {
        return clinchedPlayoffBirth;
    }

    public void setClinchedPlayoffBirth(Integer clinchedPlayoffBirth) {
        this.clinchedPlayoffBirth = clinchedPlayoffBirth;
    }

    public Integer getClinchedPlayin() {
        return clinchedPlayin;
    }

    public void setClinchedPlayin(Integer clinchedPlayin) {
        this.clinchedPlayin = clinchedPlayin;
    }

    public Integer getEliminatedConference() {
        return eliminatedConference;
    }

    public void setEliminatedConference(Integer eliminatedConference) {
        this.eliminatedConference = eliminatedConference;
    }

    public Double getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(Double pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public Double getOpponentPointsPerGame() {
        return opponentPointsPerGame;
    }

    public void setOpponentPointsPerGame(Double opponentPointsPerGame) {
        this.opponentPointsPerGame = opponentPointsPerGame;
    }

    public Double getDifferencePointsPerGame() {
        return differencePointsPerGame;
    }

    public void setDifferencePointsPerGame(Double differencePointsPerGame) {
        this.differencePointsPerGame = differencePointsPerGame;
    }

    public String getLogoUrl(){ return logoUrl; }
    public void setLogoUrl(String logoUrl){ this.logoUrl = logoUrl; }
}
