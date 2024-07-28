package com.nba.nba_zone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "player_fantasy_stats")
public class PlayerFantasyStats extends PlayerBase {

    private Integer teamId;
    private Double fanDuelPtsLast5;
    private Double nbaFantasyPtsLast5;
    private Double ptsLast5;
    private Double rebLast5;
    private Double astLast5;
    private Double fg3mLast5;
    private Double ftPctLast5;
    private Double stlLast5;
    private Double blkLast5;
    private Double tovLast5;
    private Double fgPctLast5;
    private Double fanDuelPtsSeason;
    private Double nbaFantasyPtsSeason;
    private Double ptsSeason;
    private Double rebSeason;
    private Double astSeason;
    private Double fg3mSeason;
    private Double fgPctSeason;
    private Double tovSeason;

    public PlayerFantasyStats() {}

    public PlayerFantasyStats(Long id, Integer playerId, String teamAbbreviation, Integer teamId, Double fanDuelPtsLast5,
                              Double nbaFantasyPtsLast5, Double ptsLast5, Double rebLast5, Double astLast5, Double fg3mLast5,
                              Double ftPctLast5, Double stlLast5, Double blkLast5, Double tovLast5, Double fgPctLast5,
                              Double fanDuelPtsSeason, Double nbaFantasyPtsSeason, Double ptsSeason, Double rebSeason,
                              Double astSeason, Double fg3mSeason, Double fgPctSeason, Double tovSeason) {
        super(id, playerId, teamAbbreviation);
        this.teamId = teamId;
        this.fanDuelPtsLast5 = fanDuelPtsLast5;
        this.nbaFantasyPtsLast5 = nbaFantasyPtsLast5;
        this.ptsLast5 = ptsLast5;
        this.rebLast5 = rebLast5;
        this.astLast5 = astLast5;
        this.fg3mLast5 = fg3mLast5;
        this.ftPctLast5 = ftPctLast5;
        this.stlLast5 = stlLast5;
        this.blkLast5 = blkLast5;
        this.tovLast5 = tovLast5;
        this.fgPctLast5 = fgPctLast5;
        this.fanDuelPtsSeason = fanDuelPtsSeason;
        this.nbaFantasyPtsSeason = nbaFantasyPtsSeason;
        this.ptsSeason = ptsSeason;
        this.rebSeason = rebSeason;
        this.astSeason = astSeason;
        this.fg3mSeason = fg3mSeason;
        this.fgPctSeason = fgPctSeason;
        this.tovSeason = tovSeason;
    }

    // Getters and Setters

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Double getFanDuelPtsLast5() {
        return fanDuelPtsLast5;
    }

    public void setFanDuelPtsLast5(Double fanDuelPtsLast5) {
        this.fanDuelPtsLast5 = fanDuelPtsLast5;
    }

    public Double getNbaFantasyPtsLast5() {
        return nbaFantasyPtsLast5;
    }

    public void setNbaFantasyPtsLast5(Double nbaFantasyPtsLast5) {
        this.nbaFantasyPtsLast5 = nbaFantasyPtsLast5;
    }

    public Double getPtsLast5() {
        return ptsLast5;
    }

    public void setPtsLast5(Double ptsLast5) {
        this.ptsLast5 = ptsLast5;
    }

    public Double getRebLast5() {
        return rebLast5;
    }

    public void setRebLast5(Double rebLast5) {
        this.rebLast5 = rebLast5;
    }

    public Double getAstLast5() {
        return astLast5;
    }

    public void setAstLast5(Double astLast5) {
        this.astLast5 = astLast5;
    }

    public Double getFg3mLast5() {
        return fg3mLast5;
    }

    public void setFg3mLast5(Double fg3mLast5) {
        this.fg3mLast5 = fg3mLast5;
    }

    public Double getFtPctLast5() {
        return ftPctLast5;
    }

    public void setFtPctLast5(Double ftPctLast5) {
        this.ftPctLast5 = ftPctLast5;
    }

    public Double getStlLast5() {
        return stlLast5;
    }

    public void setStlLast5(Double stlLast5) {
        this.stlLast5 = stlLast5;
    }

    public Double getBlkLast5() {
        return blkLast5;
    }

    public void setBlkLast5(Double blkLast5) {
        this.blkLast5 = blkLast5;
    }

    public Double getTovLast5() {
        return tovLast5;
    }

    public void setTovLast5(Double tovLast5) {
        this.tovLast5 = tovLast5;
    }

    public Double getFgPctLast5() {
        return fgPctLast5;
    }

    public void setFgPctLast5(Double fgPctLast5) {
        this.fgPctLast5 = fgPctLast5;
    }

    public Double getFanDuelPtsSeason() {
        return fanDuelPtsSeason;
    }

    public void setFanDuelPtsSeason(Double fanDuelPtsSeason) {
        this.fanDuelPtsSeason = fanDuelPtsSeason;
    }

    public Double getNbaFantasyPtsSeason() {
        return nbaFantasyPtsSeason;
    }

    public void setNbaFantasyPtsSeason(Double nbaFantasyPtsSeason) {
        this.nbaFantasyPtsSeason = nbaFantasyPtsSeason;
    }

    public Double getPtsSeason() {
        return ptsSeason;
    }

    public void setPtsSeason(Double ptsSeason) {
        this.ptsSeason = ptsSeason;
    }

    public Double getRebSeason() {
        return rebSeason;
    }

    public void setRebSeason(Double rebSeason) {
        this.rebSeason = rebSeason;
    }

    public Double getAstSeason() {
        return astSeason;
    }

    public void setAstSeason(Double astSeason) {
        this.astSeason = astSeason;
    }

    public Double getFg3mSeason() {
        return fg3mSeason;
    }

    public void setFg3mSeason(Double fg3mSeason) {
        this.fg3mSeason = fg3mSeason;
    }

    public Double getFgPctSeason() {
        return fgPctSeason;
    }

    public void setFgPctSeason(Double fgPctSeason) {
        this.fgPctSeason = fgPctSeason;
    }

    public Double getTovSeason() {
        return tovSeason;
    }

    public void setTovSeason(Double tovSeason) {
        this.tovSeason = tovSeason;
    }
}
