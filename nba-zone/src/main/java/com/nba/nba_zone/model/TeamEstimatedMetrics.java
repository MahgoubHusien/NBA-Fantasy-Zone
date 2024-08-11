package com.nba.nba_zone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_estimated_metrics")
public class TeamEstimatedMetrics {

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

    @Column(name = "e_off_rating")
    private float estimatedOffensiveRating;

    @Column(name = "e_def_rating")
    private float estimatedDefensiveRating;

    @Column(name = "e_net_rating")
    private float estimatedNetRating;

    @Column(name = "e_pace")
    private float estimatedPace;

    @Column(name = "e_ast_ratio")
    private float estimatedAssistRatio;

    @Column(name = "e_oreb_pct")
    private float estimatedOffensiveReboundPct;

    @Column(name = "e_dreb_pct")
    private float estimatedDefensiveReboundPct;

    @Column(name = "e_reb_pct")
    private float estimatedReboundPct;

    @Column(name = "e_tm_tov_pct")
    private float estimatedTurnoverPct;

    @Column(name = "gp_rank")
    private int gpRank;

    @Column(name = "w_rank")
    private int winRank;

    @Column(name = "l_rank")
    private int lossRank;

    @Column(name = "w_pct_rank")
    private int winPctRank;

    @Column(name = "min_rank")
    private int minRank;

    @Column(name = "e_off_rating_rank")
    private int estimatedOffensiveRatingRank;

    @Column(name = "e_def_rating_rank")
    private int estimatedDefensiveRatingRank;

    @Column(name = "e_net_rating_rank")
    private int estimatedNetRatingRank;

    @Column(name = "e_ast_ratio_rank")
    private int estimatedAssistRatioRank;

    @Column(name = "e_oreb_pct_rank")
    private int estimatedOffensiveReboundPctRank;

    @Column(name = "e_dreb_pct_rank")
    private int estimatedDefensiveReboundPctRank;

    @Column(name = "e_reb_pct_rank")
    private int estimatedReboundPctRank;

    @Column(name = "e_tm_tov_pct_rank")
    private int estimatedTurnoverPctRank;

    @Column(name = "e_pace_rank")
    private int estimatedPaceRank;

    // No-argument constructor
    public TeamEstimatedMetrics() {
    }

    // Constructor with all fields
    public TeamEstimatedMetrics(int teamId, String teamName, int gp, int wins, int losses, float winPct, float minutes,
                                float estimatedOffensiveRating, float estimatedDefensiveRating, float estimatedNetRating,
                                float estimatedPace, float estimatedAssistRatio, float estimatedOffensiveReboundPct,
                                float estimatedDefensiveReboundPct, float estimatedReboundPct, float estimatedTurnoverPct,
                                int gpRank, int winRank, int lossRank, int winPctRank, int minRank, int estimatedOffensiveRatingRank,
                                int estimatedDefensiveRatingRank, int estimatedNetRatingRank, int estimatedAssistRatioRank,
                                int estimatedOffensiveReboundPctRank, int estimatedDefensiveReboundPctRank, int estimatedReboundPctRank,
                                int estimatedTurnoverPctRank, int estimatedPaceRank) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.gp = gp;
        this.wins = wins;
        this.losses = losses;
        this.winPct = winPct;
        this.minutes = minutes;
        this.estimatedOffensiveRating = estimatedOffensiveRating;
        this.estimatedDefensiveRating = estimatedDefensiveRating;
        this.estimatedNetRating = estimatedNetRating;
        this.estimatedPace = estimatedPace;
        this.estimatedAssistRatio = estimatedAssistRatio;
        this.estimatedOffensiveReboundPct = estimatedOffensiveReboundPct;
        this.estimatedDefensiveReboundPct = estimatedDefensiveReboundPct;
        this.estimatedReboundPct = estimatedReboundPct;
        this.estimatedTurnoverPct = estimatedTurnoverPct;
        this.gpRank = gpRank;
        this.winRank = winRank;
        this.lossRank = lossRank;
        this.winPctRank = winPctRank;
        this.minRank = minRank;
        this.estimatedOffensiveRatingRank = estimatedOffensiveRatingRank;
        this.estimatedDefensiveRatingRank = estimatedDefensiveRatingRank;
        this.estimatedNetRatingRank = estimatedNetRatingRank;
        this.estimatedAssistRatioRank = estimatedAssistRatioRank;
        this.estimatedOffensiveReboundPctRank = estimatedOffensiveReboundPctRank;
        this.estimatedDefensiveReboundPctRank = estimatedDefensiveReboundPctRank;
        this.estimatedReboundPctRank = estimatedReboundPctRank;
        this.estimatedTurnoverPctRank = estimatedTurnoverPctRank;
        this.estimatedPaceRank = estimatedPaceRank;
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

    public float getEstimatedOffensiveRating() {
        return estimatedOffensiveRating;
    }

    public void setEstimatedOffensiveRating(float estimatedOffensiveRating) {
        this.estimatedOffensiveRating = estimatedOffensiveRating;
    }

    public float getEstimatedDefensiveRating() {
        return estimatedDefensiveRating;
    }

    public void setEstimatedDefensiveRating(float estimatedDefensiveRating) {
        this.estimatedDefensiveRating = estimatedDefensiveRating;
    }

    public float getEstimatedNetRating() {
        return estimatedNetRating;
    }

    public void setEstimatedNetRating(float estimatedNetRating) {
        this.estimatedNetRating = estimatedNetRating;
    }

    public float getEstimatedPace() {
        return estimatedPace;
    }

    public void setEstimatedPace(float estimatedPace) {
        this.estimatedPace = estimatedPace;
    }

    public float getEstimatedAssistRatio() {
        return estimatedAssistRatio;
    }

    public void setEstimatedAssistRatio(float estimatedAssistRatio) {
        this.estimatedAssistRatio = estimatedAssistRatio;
    }

    public float getEstimatedOffensiveReboundPct() {
        return estimatedOffensiveReboundPct;
    }

    public void setEstimatedOffensiveReboundPct(float estimatedOffensiveReboundPct) {
        this.estimatedOffensiveReboundPct = estimatedOffensiveReboundPct;
    }

    public float getEstimatedDefensiveReboundPct() {
        return estimatedDefensiveReboundPct;
    }

    public void setEstimatedDefensiveReboundPct(float estimatedDefensiveReboundPct) {
        this.estimatedDefensiveReboundPct = estimatedDefensiveReboundPct;
    }

    public float getEstimatedReboundPct() {
        return estimatedReboundPct;
    }

    public void setEstimatedReboundPct(float estimatedReboundPct) {
        this.estimatedReboundPct = estimatedReboundPct;
    }

    public float getEstimatedTurnoverPct() {
        return estimatedTurnoverPct;
    }

    public void setEstimatedTurnoverPct(float estimatedTurnoverPct) {
        this.estimatedTurnoverPct = estimatedTurnoverPct;
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

    public int getWinPctRank() {
        return winPctRank;
    }

    public void setWinPctRank(int winPctRank) {
        this.winPctRank = winPctRank;
    }

    public int getMinRank() {
        return minRank;
    }

    public void setMinRank(int minRank) {
        this.minRank = minRank;
    }

    public int getEstimatedOffensiveRatingRank() {
        return estimatedOffensiveRatingRank;
    }

    public void setEstimatedOffensiveRatingRank(int estimatedOffensiveRatingRank) {
        this.estimatedOffensiveRatingRank = estimatedOffensiveRatingRank;
    }

    public int getEstimatedDefensiveRatingRank() {
        return estimatedDefensiveRatingRank;
    }

    public void setEstimatedDefensiveRatingRank(int estimatedDefensiveRatingRank) {
        this.estimatedDefensiveRatingRank = estimatedDefensiveRatingRank;
    }

    public int getEstimatedNetRatingRank() {
        return estimatedNetRatingRank;
    }

    public void setEstimatedNetRatingRank(int estimatedNetRatingRank) {
        this.estimatedNetRatingRank = estimatedNetRatingRank;
    }

    public int getEstimatedAssistRatioRank() {
        return estimatedAssistRatioRank;
    }

    public void setEstimatedAssistRatioRank(int estimatedAssistRatioRank) {
        this.estimatedAssistRatioRank = estimatedAssistRatioRank;
    }

    public int getEstimatedOffensiveReboundPctRank() {
        return estimatedOffensiveReboundPctRank;
    }

    public void setEstimatedOffensiveReboundPctRank(int estimatedOffensiveReboundPctRank) {
        this.estimatedOffensiveReboundPctRank = estimatedOffensiveReboundPctRank;
    }

    public int getEstimatedDefensiveReboundPctRank() {
        return estimatedDefensiveReboundPctRank;
    }

    public void setEstimatedDefensiveReboundPctRank(int estimatedDefensiveReboundPctRank) {
        this.estimatedDefensiveReboundPctRank = estimatedDefensiveReboundPctRank;
    }

    public int getEstimatedReboundPctRank() {
        return estimatedReboundPctRank;
    }

    public void setEstimatedReboundPctRank(int estimatedReboundPctRank) {
        this.estimatedReboundPctRank = estimatedReboundPctRank;
    }

    public int getEstimatedTurnoverPctRank() {
        return estimatedTurnoverPctRank;
    }

    public void setEstimatedTurnoverPctRank(int estimatedTurnoverPctRank) {
        this.estimatedTurnoverPctRank = estimatedTurnoverPctRank;
    }

    public int getEstimatedPaceRank() {
        return estimatedPaceRank;
    }

    public void setEstimatedPaceRank(int estimatedPaceRank) {
        this.estimatedPaceRank = estimatedPaceRank;
    }
}
