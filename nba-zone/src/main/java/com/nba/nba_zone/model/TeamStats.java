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
    private int teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "gp")
    private int gp;

    @Column(name = "w")
    private int wins;

    @Column(name = "l")
    private int losses;

    @Column(name = "w_pct")
    private float winPct;

    @Column(name = "min")
    private float minutes;

    @Column(name = "fgm")
    private float fieldGoalsMade;

    @Column(name = "fga")
    private float fieldGoalsAttempted;

    @Column(name = "fg_pct")
    private float fieldGoalPct;

    @Column(name = "fg3m")
    private float threePointersMade;

    @Column(name = "fg3a")
    private float threePointersAttempted;

    @Column(name = "fg3_pct")
    private float threePointPct;

    @Column(name = "ftm")
    private float freeThrowsMade;

    @Column(name = "fta")
    private float freeThrowsAttempted;

    @Column(name = "ft_pct")
    private float freeThrowPct;

    @Column(name = "oreb")
    private float offensiveRebounds;

    @Column(name = "dreb")
    private float defensiveRebounds;

    @Column(name = "reb")
    private float totalRebounds;

    @Column(name = "ast")
    private float assists;

    @Column(name = "tov")
    private float turnovers;

    @Column(name = "stl")
    private float steals;

    @Column(name = "blk")
    private float blocks;

    @Column(name = "blka")
    private float blocksAgainst;

    @Column(name = "pf")
    private float personalFouls;

    @Column(name = "pfd")
    private float personalFoulsDrawn;

    @Column(name = "pts")
    private float points;

    @Column(name = "plus_minus")
    private float plusMinus;

    @Column(name = "gp_rank")
    private int gpRank;

    @Column(name = "w_rank")
    private int winRank;

    @Column(name = "l_rank")
    private int lossRank;

    @Column(name = "w_pct_rank")
    private float winPctRank;

    @Column(name = "min_rank")
    private int minRank;

    @Column(name = "fgm_rank")
    private int fieldGoalsMadeRank;

    @Column(name = "fga_rank")
    private int fieldGoalsAttemptedRank;

    @Column(name = "fg_pct_rank")
    private float fieldGoalPctRank;

    @Column(name = "fg3m_rank")
    private int threePointersMadeRank;

    @Column(name = "fg3a_rank")
    private int threePointersAttemptedRank;

    @Column(name = "fg3_pct_rank")
    private float threePointPctRank;

    @Column(name = "ftm_rank")
    private int freeThrowsMadeRank;

    @Column(name = "fta_rank")
    private int freeThrowsAttemptedRank;

    @Column(name = "ft_pct_rank")
    private float freeThrowPctRank;

    @Column(name = "oreb_rank")
    private int offensiveReboundsRank;

    @Column(name = "dreb_rank")
    private int defensiveReboundsRank;

    @Column(name = "reb_rank")
    private int totalReboundsRank;

    @Column(name = "ast_rank")
    private int assistsRank;

    @Column(name = "tov_rank")
    private int turnoversRank;

    @Column(name = "stl_rank")
    private int stealsRank;

    @Column(name = "blk_rank")
    private int blocksRank;

    @Column(name = "blka_rank")
    private int blocksAgainstRank;

    @Column(name = "pf_rank")
    private int personalFoulsRank;

    @Column(name = "pfd_rank")
    private int personalFoulsDrawnRank;

    @Column(name = "pts_rank")
    private int pointsRank;

    @Column(name = "plus_minus_rank")
    private int plusMinusRank;

    @Column(name = "cfid")
    private Integer cfid;

    @Column(name = "cfparams")
    private String cfparams;

    // No-argument constructor
    public TeamStats() {
    }

    // Constructor with all fields
    public TeamStats(int teamId, String teamName, int gp, int wins, int losses, float winPct, float minutes,
                     float fieldGoalsMade, float fieldGoalsAttempted, float fieldGoalPct, float threePointersMade,
                     float threePointersAttempted, float threePointPct, float freeThrowsMade, float freeThrowsAttempted,
                     float freeThrowPct, float offensiveRebounds, float defensiveRebounds, float totalRebounds,
                     float assists, float turnovers, float steals, float blocks, float blocksAgainst, float personalFouls,
                     float personalFoulsDrawn, float points, float plusMinus, int gpRank, int winRank, int lossRank,
                     float winPctRank, int minRank, int fieldGoalsMadeRank, int fieldGoalsAttemptedRank,
                     float fieldGoalPctRank, int threePointersMadeRank, int threePointersAttemptedRank,
                     float threePointPctRank, int freeThrowsMadeRank, int freeThrowsAttemptedRank, float freeThrowPctRank,
                     int offensiveReboundsRank, int defensiveReboundsRank, int totalReboundsRank, int assistsRank,
                     int turnoversRank, int stealsRank, int blocksRank, int blocksAgainstRank, int personalFoulsRank,
                     int personalFoulsDrawnRank, int pointsRank, int plusMinusRank, Integer cfid, String cfparams) {
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
    }

    // Getters and Setters

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGp() {
        return gp;
    }

    public void setGp(int gp) {
        this.gp = gp;
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

    public float getMinutes() {
        return minutes;
    }

    public void setMinutes(float minutes) {
        this.minutes = minutes;
    }

    public float getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(float fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public float getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public void setFieldGoalsAttempted(float fieldGoalsAttempted) {
        this.fieldGoalsAttempted = fieldGoalsAttempted;
    }

    public float getFieldGoalPct() {
        return fieldGoalPct;
    }

    public void setFieldGoalPct(float fieldGoalPct) {
        this.fieldGoalPct = fieldGoalPct;
    }

    public float getThreePointersMade() {
        return threePointersMade;
    }

    public void setThreePointersMade(float threePointersMade) {
        this.threePointersMade = threePointersMade;
    }

    public float getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public void setThreePointersAttempted(float threePointersAttempted) {
        this.threePointersAttempted = threePointersAttempted;
    }

    public float getThreePointPct() {
        return threePointPct;
    }

    public void setThreePointPct(float threePointPct) {
        this.threePointPct = threePointPct;
    }

    public float getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(float freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public float getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public void setFreeThrowsAttempted(float freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public float getFreeThrowPct() {
        return freeThrowPct;
    }

    public void setFreeThrowPct(float freeThrowPct) {
        this.freeThrowPct = freeThrowPct;
    }

    public float getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(float offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public float getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(float defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public float getTotalRebounds() {
        return totalRebounds;
    }

    public void setTotalRebounds(float totalRebounds) {
        this.totalRebounds = totalRebounds;
    }

    public float getAssists() {
        return assists;
    }

    public void setAssists(float assists) {
        this.assists = assists;
    }

    public float getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(float turnovers) {
        this.turnovers = turnovers;
    }

    public float getSteals() {
        return steals;
    }

    public void setSteals(float steals) {
        this.steals = steals;
    }

    public float getBlocks() {
        return blocks;
    }

    public void setBlocks(float blocks) {
        this.blocks = blocks;
    }

    public float getBlocksAgainst() {
        return blocksAgainst;
    }

    public void setBlocksAgainst(float blocksAgainst) {
        this.blocksAgainst = blocksAgainst;
    }

    public float getPersonalFouls() {
        return personalFouls;
    }

    public void setPersonalFouls(float personalFouls) {
        this.personalFouls = personalFouls;
    }

    public float getPersonalFoulsDrawn() {
        return personalFoulsDrawn;
    }

    public void setPersonalFoulsDrawn(float personalFoulsDrawn) {
        this.personalFoulsDrawn = personalFoulsDrawn;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public float getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(float plusMinus) {
        this.plusMinus = plusMinus;
    }

    public int getGpRank() {
        return gpRank;
    }

    public void setGpRank(int gpRank) {
        this.gpRank = gpRank;
    }

    public int getWinRank() {
        return winRank;
    }

    public void setWinRank(int winRank) {
        this.winRank = winRank;
    }

    public int getLossRank() {
        return lossRank;
    }

    public void setLossRank(int lossRank) {
        this.lossRank = lossRank;
    }

    public float getWinPctRank() {
        return winPctRank;
    }

    public void setWinPctRank(float winPctRank) {
        this.winPctRank = winPctRank;
    }

    public int getMinRank() {
        return minRank;
    }

    public void setMinRank(int minRank) {
        this.minRank = minRank;
    }

    public int getFieldGoalsMadeRank() {
        return fieldGoalsMadeRank;
    }

    public void setFieldGoalsMadeRank(int fieldGoalsMadeRank) {
        this.fieldGoalsMadeRank = fieldGoalsMadeRank;
    }

    public int getFieldGoalsAttemptedRank() {
        return fieldGoalsAttemptedRank;
    }

    public void setFieldGoalsAttemptedRank(int fieldGoalsAttemptedRank) {
        this.fieldGoalsAttemptedRank = fieldGoalsAttemptedRank;
    }

    public float getFieldGoalPctRank() {
        return fieldGoalPctRank;
    }

    public void setFieldGoalPctRank(float fieldGoalPctRank) {
        this.fieldGoalPctRank = fieldGoalPctRank;
    }

    public int getThreePointersMadeRank() {
        return threePointersMadeRank;
    }

    public void setThreePointersMadeRank(int threePointersMadeRank) {
        this.threePointersMadeRank = threePointersMadeRank;
    }

    public int getThreePointersAttemptedRank() {
        return threePointersAttemptedRank;
    }

    public void setThreePointersAttemptedRank(int threePointersAttemptedRank) {
        this.threePointersAttemptedRank = threePointersAttemptedRank;
    }

    public float getThreePointPctRank() {
        return threePointPctRank;
    }

    public void setThreePointPctRank(float threePointPctRank) {
        this.threePointPctRank = threePointPctRank;
    }

    public int getFreeThrowsMadeRank() {
        return freeThrowsMadeRank;
    }

    public void setFreeThrowsMadeRank(int freeThrowsMadeRank) {
        this.freeThrowsMadeRank = freeThrowsMadeRank;
    }

    public int getFreeThrowsAttemptedRank() {
        return freeThrowsAttemptedRank;
    }

    public void setFreeThrowsAttemptedRank(int freeThrowsAttemptedRank) {
        this.freeThrowsAttemptedRank = freeThrowsAttemptedRank;
    }

    public float getFreeThrowPctRank() {
        return freeThrowPctRank;
    }

    public void setFreeThrowPctRank(float freeThrowPctRank) {
        this.freeThrowPctRank = freeThrowPctRank;
    }

    public int getOffensiveReboundsRank() {
        return offensiveReboundsRank;
    }

    public void setOffensiveReboundsRank(int offensiveReboundsRank) {
        this.offensiveReboundsRank = offensiveReboundsRank;
    }

    public int getDefensiveReboundsRank() {
        return defensiveReboundsRank;
    }

    public void setDefensiveReboundsRank(int defensiveReboundsRank) {
        this.defensiveReboundsRank = defensiveReboundsRank;
    }

    public int getTotalReboundsRank() {
        return totalReboundsRank;
    }

    public void setTotalReboundsRank(int totalReboundsRank) {
        this.totalReboundsRank = totalReboundsRank;
    }

    public int getAssistsRank() {
        return assistsRank;
    }

    public void setAssistsRank(int assistsRank) {
        this.assistsRank = assistsRank;
    }

    public int getTurnoversRank() {
        return turnoversRank;
    }

    public void setTurnoversRank(int turnoversRank) {
        this.turnoversRank = turnoversRank;
    }

    public int getStealsRank() {
        return stealsRank;
    }

    public void setStealsRank(int stealsRank) {
        this.stealsRank = stealsRank;
    }

    public int getBlocksRank() {
        return blocksRank;
    }

    public void setBlocksRank(int blocksRank) {
        this.blocksRank = blocksRank;
    }

    public int getBlocksAgainstRank() {
        return blocksAgainstRank;
    }

    public void setBlocksAgainstRank(int blocksAgainstRank) {
        this.blocksAgainstRank = blocksAgainstRank;
    }

    public int getPersonalFoulsRank() {
        return personalFoulsRank;
    }

    public void setPersonalFoulsRank(int personalFoulsRank) {
        this.personalFoulsRank = personalFoulsRank;
    }

    public int getPersonalFoulsDrawnRank() {
        return personalFoulsDrawnRank;
    }

    public void setPersonalFoulsDrawnRank(int personalFoulsDrawnRank) {
        this.personalFoulsDrawnRank = personalFoulsDrawnRank;
    }

    public int getPointsRank() {
        return pointsRank;
    }

    public void setPointsRank(int pointsRank) {
        this.pointsRank = pointsRank;
    }

    public int getPlusMinusRank() {
        return plusMinusRank;
    }

    public void setPlusMinusRank(int plusMinusRank) {
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
}
