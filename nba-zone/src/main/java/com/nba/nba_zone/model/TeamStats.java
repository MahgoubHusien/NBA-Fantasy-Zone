package com.nba.nba_zone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_stats")
public class TeamStats {

    @Id
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "gp")
    private Integer gp;

    @Column(name = "w")
    private Integer wins;

    @Column(name = "l")
    private Integer losses;

    @Column(name = "w_pct")
    private Double winPct;

    @Column(name = "min")
    private Double minutes;

    @Column(name = "fgm")
    private Double fieldGoalsMade;

    @Column(name = "fga")
    private Double fieldGoalsAttempted;

    @Column(name = "fg_pct")
    private Double fieldGoalPct;

    @Column(name = "fg3m")
    private Double threePointersMade;

    @Column(name = "fg3a")
    private Double threePointersAttempted;

    @Column(name = "fg3_pct")
    private Double threePointPct;

    @Column(name = "ftm")
    private Double freeThrowsMade;

    @Column(name = "fta")
    private Double freeThrowsAttempted;

    @Column(name = "ft_pct")
    private Double freeThrowPct;

    @Column(name = "oreb")
    private Double offensiveRebounds;

    @Column(name = "dreb")
    private Double defensiveRebounds;

    @Column(name = "reb")
    private Double totalRebounds;

    @Column(name = "ast")
    private Double assists;

    @Column(name = "tov")
    private Double turnovers;

    @Column(name = "stl")
    private Double steals;

    @Column(name = "blk")
    private Double blocks;

    @Column(name = "blka")
    private Double blocksAgainst;

    @Column(name = "pf")
    private Double personalFouls;

    @Column(name = "pfd")
    private Double personalFoulsDrawn;

    @Column(name = "pts")
    private Double points;

    @Column(name = "plus_minus")
    private Double plusMinus;

    @Column(name = "gp_rank")
    private Integer gpRank;

    @Column(name = "w_rank")
    private Integer winRank;

    @Column(name = "l_rank")
    private Integer lossRank;

    @Column(name = "w_pct_rank")
    private Double winPctRank;

    @Column(name = "min_rank")
    private Integer minRank;

    @Column(name = "fgm_rank")
    private Integer fieldGoalsMadeRank;

    @Column(name = "fga_rank")
    private Integer fieldGoalsAttemptedRank;

    @Column(name = "fg_pct_rank")
    private Double fieldGoalPctRank;

    @Column(name = "fg3m_rank")
    private Integer threePointersMadeRank;

    @Column(name = "fg3a_rank")
    private Integer threePointersAttemptedRank;

    @Column(name = "fg3_pct_rank")
    private Double threePointPctRank;

    @Column(name = "ftm_rank")
    private Integer freeThrowsMadeRank;

    @Column(name = "fta_rank")
    private Integer freeThrowsAttemptedRank;

    @Column(name = "ft_pct_rank")
    private Double freeThrowPctRank;

    @Column(name = "oreb_rank")
    private Integer offensiveReboundsRank;

    @Column(name = "dreb_rank")
    private Integer defensiveReboundsRank;

    @Column(name = "reb_rank")
    private Integer totalReboundsRank;

    @Column(name = "ast_rank")
    private Integer assistsRank;

    @Column(name = "tov_rank")
    private Integer turnoversRank;

    @Column(name = "stl_rank")
    private Integer stealsRank;

    @Column(name = "blk_rank")
    private Integer blocksRank;

    @Column(name = "blka_rank")
    private Integer blocksAgainstRank;

    @Column(name = "pf_rank")
    private Integer personalFoulsRank;

    @Column(name = "pfd_rank")
    private Integer personalFoulsDrawnRank;

    @Column(name = "pts_rank")
    private Integer pointsRank;

    @Column(name = "plus_minus_rank")
    private Integer plusMinusRank;

    @Column(name = "cfid")
    private Integer cfid;

    @Column(name = "cfparams")
    private String cfparams;

    @Column(name = "logo_url")
    private String logoUrl;

    // No-argument constructor
    public TeamStats() {
    }

    // Constructor with all fields
    public TeamStats(Integer teamId, String teamName, Integer gp, Integer wins, Integer losses, Double winPct, Double minutes,
                     Double fieldGoalsMade, Double fieldGoalsAttempted, Double fieldGoalPct, Double threePointersMade,
                     Double threePointersAttempted, Double threePointPct, Double freeThrowsMade, Double freeThrowsAttempted,
                     Double freeThrowPct, Double offensiveRebounds, Double defensiveRebounds, Double totalRebounds,
                     Double assists, Double turnovers, Double steals, Double blocks, Double blocksAgainst, Double personalFouls,
                     Double personalFoulsDrawn, Double points, Double plusMinus, Integer gpRank, Integer winRank, Integer lossRank,
                     Double winPctRank, Integer minRank, Integer fieldGoalsMadeRank, Integer fieldGoalsAttemptedRank,
                     Double fieldGoalPctRank, Integer threePointersMadeRank, Integer threePointersAttemptedRank,
                     Double threePointPctRank, Integer freeThrowsMadeRank, Integer freeThrowsAttemptedRank, Double freeThrowPctRank,
                     Integer offensiveReboundsRank, Integer defensiveReboundsRank, Integer totalReboundsRank, Integer assistsRank,
                     Integer turnoversRank, Integer stealsRank, Integer blocksRank, Integer blocksAgainstRank, Integer personalFoulsRank,
                     Integer personalFoulsDrawnRank, Integer pointsRank, Integer plusMinusRank, Integer cfid, String cfparams, String logoUrl) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.gp = gp;
        this.wins = wins;
        this.losses = losses;
        this.winPct = winPct;
        this.minutes = minutes;
        this.fieldGoalsMade = fieldGoalsMade;
        this.fieldGoalsAttempted = fieldGoalsAttempted;
        this.fieldGoalPct = fieldGoalPct;
        this.threePointersMade = threePointersMade;
        this.threePointersAttempted = threePointersAttempted;
        this.threePointPct = threePointPct;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.freeThrowPct = freeThrowPct;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        this.totalRebounds = totalRebounds;
        this.assists = assists;
        this.turnovers = turnovers;
        this.steals = steals;
        this.blocks = blocks;
        this.blocksAgainst = blocksAgainst;
        this.personalFouls = personalFouls;
        this.personalFoulsDrawn = personalFoulsDrawn;
        this.points = points;
        this.plusMinus = plusMinus;
        this.gpRank = gpRank;
        this.winRank = winRank;
        this.lossRank = lossRank;
        this.winPctRank = winPctRank;
        this.minRank = minRank;
        this.fieldGoalsMadeRank = fieldGoalsMadeRank;
        this.fieldGoalsAttemptedRank = fieldGoalsAttemptedRank;
        this.fieldGoalPctRank = fieldGoalPctRank;
        this.threePointersMadeRank = threePointersMadeRank;
        this.threePointersAttemptedRank = threePointersAttemptedRank;
        this.threePointPctRank = threePointPctRank;
        this.freeThrowsMadeRank = freeThrowsMadeRank;
        this.freeThrowsAttemptedRank = freeThrowsAttemptedRank;
        this.freeThrowPctRank = freeThrowPctRank;
        this.offensiveReboundsRank = offensiveReboundsRank;
        this.defensiveReboundsRank = defensiveReboundsRank;
        this.totalReboundsRank = totalReboundsRank;
        this.assistsRank = assistsRank;
        this.turnoversRank = turnoversRank;
        this.stealsRank = stealsRank;
        this.blocksRank = blocksRank;
        this.blocksAgainstRank = blocksAgainstRank;
        this.personalFoulsRank = personalFoulsRank;
        this.personalFoulsDrawnRank = personalFoulsDrawnRank;
        this.pointsRank = pointsRank;
        this.plusMinusRank = plusMinusRank;
        this.cfid = cfid;
        this.cfparams = cfparams;
        this.logoUrl = logoUrl;
    }

    // Getters and Setters

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getGp() {
        return gp;
    }

    public void setGp(Integer gp) {
        this.gp = gp;
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

    public Double getMinutes() {
        return minutes;
    }

    public void setMinutes(Double minutes) {
        this.minutes = minutes;
    }

    public Double getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(Double fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public Double getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public void setFieldGoalsAttempted(Double fieldGoalsAttempted) {
        this.fieldGoalsAttempted = fieldGoalsAttempted;
    }

    public Double getFieldGoalPct() {
        return fieldGoalPct;
    }

    public void setFieldGoalPct(Double fieldGoalPct) {
        this.fieldGoalPct = fieldGoalPct;
    }

    public Double getThreePointersMade() {
        return threePointersMade;
    }

    public void setThreePointersMade(Double threePointersMade) {
        this.threePointersMade = threePointersMade;
    }

    public Double getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public void setThreePointersAttempted(Double threePointersAttempted) {
        this.threePointersAttempted = threePointersAttempted;
    }

    public Double getThreePointPct() {
        return threePointPct;
    }

    public void setThreePointPct(Double threePointPct) {
        this.threePointPct = threePointPct;
    }

    public Double getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(Double freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public Double getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public void setFreeThrowsAttempted(Double freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public Double getFreeThrowPct() {
        return freeThrowPct;
    }

    public void setFreeThrowPct(Double freeThrowPct) {
        this.freeThrowPct = freeThrowPct;
    }

    public Double getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(Double offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public Double getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(Double defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public Double getTotalRebounds() {
        return totalRebounds;
    }

    public void setTotalRebounds(Double totalRebounds) {
        this.totalRebounds = totalRebounds;
    }

    public Double getAssists() {
        return assists;
    }

    public void setAssists(Double assists) {
        this.assists = assists;
    }

    public Double getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Double turnovers) {
        this.turnovers = turnovers;
    }

    public Double getSteals() {
        return steals;
    }

    public void setSteals(Double steals) {
        this.steals = steals;
    }

    public Double getBlocks() {
        return blocks;
    }

    public void setBlocks(Double blocks) {
        this.blocks = blocks;
    }

    public Double getBlocksAgainst() {
        return blocksAgainst;
    }

    public void setBlocksAgainst(Double blocksAgainst) {
        this.blocksAgainst = blocksAgainst;
    }

    public Double getPersonalFouls() {
        return personalFouls;
    }

    public void setPersonalFouls(Double personalFouls) {
        this.personalFouls = personalFouls;
    }

    public Double getPersonalFoulsDrawn() {
        return personalFoulsDrawn;
    }

    public void setPersonalFoulsDrawn(Double personalFoulsDrawn) {
        this.personalFoulsDrawn = personalFoulsDrawn;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Double plusMinus) {
        this.plusMinus = plusMinus;
    }

    public Integer getGpRank() {
        return gpRank;
    }

    public void setGpRank(Integer gpRank) {
        this.gpRank = gpRank;
    }

    public Integer getWinRank() {
        return winRank;
    }

    public void setWinRank(Integer winRank) {
        this.winRank = winRank;
    }

    public Integer getLossRank() {
        return lossRank;
    }

    public void setLossRank(Integer lossRank) {
        this.lossRank = lossRank;
    }

    public Double getWinPctRank() {
        return winPctRank;
    }

    public void setWinPctRank(Double winPctRank) {
        this.winPctRank = winPctRank;
    }

    public Integer getMinRank() {
        return minRank;
    }

    public void setMinRank(Integer minRank) {
        this.minRank = minRank;
    }

    public Integer getFieldGoalsMadeRank() {
        return fieldGoalsMadeRank;
    }

    public void setFieldGoalsMadeRank(Integer fieldGoalsMadeRank) {
        this.fieldGoalsMadeRank = fieldGoalsMadeRank;
    }

    public Integer getFieldGoalsAttemptedRank() {
        return fieldGoalsAttemptedRank;
    }

    public void setFieldGoalsAttemptedRank(Integer fieldGoalsAttemptedRank) {
        this.fieldGoalsAttemptedRank = fieldGoalsAttemptedRank;
    }

    public Double getFieldGoalPctRank() {
        return fieldGoalPctRank;
    }

    public void setFieldGoalPctRank(Double fieldGoalPctRank) {
        this.fieldGoalPctRank = fieldGoalPctRank;
    }

    public Integer getThreePointersMadeRank() {
        return threePointersMadeRank;
    }

    public void setThreePointersMadeRank(Integer threePointersMadeRank) {
        this.threePointersMadeRank = threePointersMadeRank;
    }

    public Integer getThreePointersAttemptedRank() {
        return threePointersAttemptedRank;
    }

    public void setThreePointersAttemptedRank(Integer threePointersAttemptedRank) {
        this.threePointersAttemptedRank = threePointersAttemptedRank;
    }

    public Double getThreePointPctRank() {
        return threePointPctRank;
    }

    public void setThreePointPctRank(Double threePointPctRank) {
        this.threePointPctRank = threePointPctRank;
    }

    public Integer getFreeThrowsMadeRank() {
        return freeThrowsMadeRank;
    }

    public void setFreeThrowsMadeRank(Integer freeThrowsMadeRank) {
        this.freeThrowsMadeRank = freeThrowsMadeRank;
    }

    public Integer getFreeThrowsAttemptedRank() {
        return freeThrowsAttemptedRank;
    }

    public void setFreeThrowsAttemptedRank(Integer freeThrowsAttemptedRank) {
        this.freeThrowsAttemptedRank = freeThrowsAttemptedRank;
    }

    public Double getFreeThrowPctRank() {
        return freeThrowPctRank;
    }

    public void setFreeThrowPctRank(Double freeThrowPctRank) {
        this.freeThrowPctRank = freeThrowPctRank;
    }

    public Integer getOffensiveReboundsRank() {
        return offensiveReboundsRank;
    }

    public void setOffensiveReboundsRank(Integer offensiveReboundsRank) {
        this.offensiveReboundsRank = offensiveReboundsRank;
    }

    public Integer getDefensiveReboundsRank() {
        return defensiveReboundsRank;
    }

    public void setDefensiveReboundsRank(Integer defensiveReboundsRank) {
        this.defensiveReboundsRank = defensiveReboundsRank;
    }

    public Integer getTotalReboundsRank() {
        return totalReboundsRank;
    }

    public void setTotalReboundsRank(Integer totalReboundsRank) {
        this.totalReboundsRank = totalReboundsRank;
    }

    public Integer getAssistsRank() {
        return assistsRank;
    }

    public void setAssistsRank(Integer assistsRank) {
        this.assistsRank = assistsRank;
    }

    public Integer getTurnoversRank() {
        return turnoversRank;
    }

    public void setTurnoversRank(Integer turnoversRank) {
        this.turnoversRank = turnoversRank;
    }

    public Integer getStealsRank() {
        return stealsRank;
    }

    public void setStealsRank(Integer stealsRank) {
        this.stealsRank = stealsRank;
    }

    public Integer getBlocksRank() {
        return blocksRank;
    }

    public void setBlocksRank(Integer blocksRank) {
        this.blocksRank = blocksRank;
    }

    public Integer getBlocksAgainstRank() {
        return blocksAgainstRank;
    }

    public void setBlocksAgainstRank(Integer blocksAgainstRank) {
        this.blocksAgainstRank = blocksAgainstRank;
    }

    public Integer getPersonalFoulsRank() {
        return personalFoulsRank;
    }

    public void setPersonalFoulsRank(Integer personalFoulsRank) {
        this.personalFoulsRank = personalFoulsRank;
    }

    public Integer getPersonalFoulsDrawnRank() {
        return personalFoulsDrawnRank;
    }

    public void setPersonalFoulsDrawnRank(Integer personalFoulsDrawnRank) {
        this.personalFoulsDrawnRank = personalFoulsDrawnRank;
    }

    public Integer getPointsRank() {
        return pointsRank;
    }

    public void setPointsRank(Integer pointsRank) {
        this.pointsRank = pointsRank;
    }

    public Integer getPlusMinusRank() {
        return plusMinusRank;
    }

    public void setPlusMinusRank(Integer plusMinusRank) {
        this.plusMinusRank = plusMinusRank;
    }

    public Integer getCfid() {
        return cfid;
    }

    public void setCfid(Integer cfid) {
        this.cfid = cfid;
    }

    public String getCfparams() {
        return cfparams;
    }

    public void setCfparams(String cfparams) {
        this.cfparams = cfparams;
    }

    public String getLogoUrl() { return logoUrl;}

    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl;}
}

