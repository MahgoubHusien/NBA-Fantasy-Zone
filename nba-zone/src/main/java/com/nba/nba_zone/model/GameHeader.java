package com.nba.nba_zone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.lang.String;

@Entity
@Table(name = "game_header")
public class GameHeader extends GameBase {

    private String gameDateEst;
    private Integer gameSequence;
    private Integer gameStatusId;
    private String gameStatusText;
    private Integer homeTeamId;
    private Integer visitorTeamId;
    private String season;
    private Integer livePeriod;
    private String livePcTime;
    private String natlTvBroadcasterAbbreviation;
    private String homeTvBroadcasterAbbreviation;
    private String awayTvBroadcasterAbbreviation;
    private String livePeriodTimeBcast;
    private String arenaName;
    private Integer whStatus;
    private String wnbaCommissionerFlag;

    public GameHeader() {}

    public GameHeader(Long id, String gameId, String gameDateEst, Integer gameSequence, Integer gameStatusId, String gameStatusText,
                      Integer homeTeamId, Integer visitorTeamId, String season, Integer livePeriod, String livePcTime, String natlTvBroadcasterAbbreviation,
                      String homeTvBroadcasterAbbreviation, String awayTvBroadcasterAbbreviation, String livePeriodTimeBcast, String arenaName,
                      Integer whStatus, String wnbaCommissionerFlag) {
        super(id, gameId);
        this.gameDateEst = gameDateEst;
        this.gameSequence = gameSequence;
        this.gameStatusId = gameStatusId;
        this.gameStatusText = gameStatusText;
        this.homeTeamId = homeTeamId;
        this.visitorTeamId = visitorTeamId;
        this.season = season;
        this.livePeriod = livePeriod;
        this.livePcTime = livePcTime;
        this.natlTvBroadcasterAbbreviation = natlTvBroadcasterAbbreviation;
        this.homeTvBroadcasterAbbreviation = homeTvBroadcasterAbbreviation;
        this.awayTvBroadcasterAbbreviation = awayTvBroadcasterAbbreviation;
        this.livePeriodTimeBcast = livePeriodTimeBcast;
        this.arenaName = arenaName;
        this.whStatus = whStatus;
        this.wnbaCommissionerFlag = wnbaCommissionerFlag;
    }

    // Getters and Setters
    public String getGameDateEst() {
        return gameDateEst;
    }

    public void setGameDateEst(String gameDateEst) {
        this.gameDateEst = gameDateEst;
    }

    public Integer getGameSequence() {
        return gameSequence;
    }

    public void setGameSequence(Integer gameSequence) {
        this.gameSequence = gameSequence;
    }

    public Integer getGameStatusId() {
        return gameStatusId;
    }

    public void setGameStatusId(Integer gameStatusId) {
        this.gameStatusId = gameStatusId;
    }

    public String getGameStatusText() {
        return gameStatusText;
    }

    public void setGameStatusText(String gameStatusText) {
        this.gameStatusText = gameStatusText;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getVisitorTeamId() {
        return visitorTeamId;
    }

    public void setVisitorTeamId(Integer visitorTeamId) {
        this.visitorTeamId = visitorTeamId;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getLivePeriod() {
        return livePeriod;
    }

    public void setLivePeriod(Integer livePeriod) {
        this.livePeriod = livePeriod;
    }

    public String getLivePcTime() {
        return livePcTime;
    }

    public void setLivePcTime(String livePcTime) {
        this.livePcTime = livePcTime;
    }

    public String getNatlTvBroadcasterAbbreviation() {
        return natlTvBroadcasterAbbreviation;
    }

    public void setNatlTvBroadcasterAbbreviation(String natlTvBroadcasterAbbreviation) {
        this.natlTvBroadcasterAbbreviation = natlTvBroadcasterAbbreviation;
    }

    public String getHomeTvBroadcasterAbbreviation() {
        return homeTvBroadcasterAbbreviation;
    }

    public void setHomeTvBroadcasterAbbreviation(String homeTvBroadcasterAbbreviation) {
        this.homeTvBroadcasterAbbreviation = homeTvBroadcasterAbbreviation;
    }

    public String getAwayTvBroadcasterAbbreviation() {
        return awayTvBroadcasterAbbreviation;
    }

    public void setAwayTvBroadcasterAbbreviation(String awayTvBroadcasterAbbreviation) {
        this.awayTvBroadcasterAbbreviation = awayTvBroadcasterAbbreviation;
    }

    public String getLivePeriodTimeBcast() {
        return livePeriodTimeBcast;
    }

    public void setLivePeriodTimeBcast(String livePeriodTimeBcast) {
        this.livePeriodTimeBcast = livePeriodTimeBcast;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public Integer getWhStatus() {
        return whStatus;
    }

    public void setWhStatus(Integer whStatus) {
        this.whStatus = whStatus;
    }

    public String isWnbaCommissionerFlag() {
        return wnbaCommissionerFlag;
    }

    public void setWnbaCommissionerFlag(String wnbaCommissionerFlag) {
        this.wnbaCommissionerFlag = wnbaCommissionerFlag;
    }
}
