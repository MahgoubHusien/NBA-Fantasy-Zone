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

    @Column(name = "e_off_rating")
    private Double estimatedOffensiveRating;

    @Column(name = "e_def_rating")
    private Double estimatedDefensiveRating;

    @Column(name = "e_net_rating")
    private Double estimatedNetRating;

    @Column(name = "e_pace")
    private Double estimatedPace;

    @Column(name = "e_ast_ratio")
    private Double estimatedAssistRatio;

    @Column(name = "e_oreb_pct")
    private Double estimatedOffensiveReboundPct;

    @Column(name = "e_dreb_pct")
    private Double estimatedDefensiveReboundPct;

    @Column(name = "e_reb_pct")
    private Double estimatedReboundPct;

    @Column(name = "e_tm_tov_pct")
    private Double estimatedTurnoverPct;

    @Column(name = "gp_rank")
    private Integer gpRank;

    @Column(name = "w_rank")
    private Integer winRank;

    @Column(name = "l_rank")
    private Integer lossRank;

    @Column(name = "w_pct_rank")
    private Integer winPctRank;

    @Column(name = "min_rank")
    private Integer minRank;

    @Column(name = "e_off_rating_rank")
    private Integer estimatedOffensiveRatingRank;

    @Column(name = "e_def_rating_rank")
    private Integer estimatedDefensiveRatingRank;

    @Column(name = "e_net_rating_rank")
    private Integer estimatedNetRatingRank;

    @Column(name = "e_ast_ratio_rank")
    private Integer estimatedAssistRatioRank;

    @Column(name = "e_oreb_pct_rank")
    private Integer estimatedOffensiveReboundPctRank;

    @Column(name = "e_dreb_pct_rank")
    private Integer estimatedDefensiveReboundPctRank;

    @Column(name = "e_reb_pct_rank")
    private Integer estimatedReboundPctRank;

    @Column(name = "e_tm_tov_pct_rank")
    private Integer estimatedTurnoverPctRank;

    @Column(name = "e_pace_rank")
    private Integer estimatedPaceRank;

    // No-argument constructor
    public TeamEstimatedMetrics() {
    }

    // Constructor with all fields
    public TeamEstimatedMetrics(Integer teamId, String teamName, Integer gp, Integer wins, Integer losses, Double winPct, Double minutes,
                                Double estimatedOffensiveRating, Double estimatedDefensiveRating, Double estimatedNetRating,
                                Double estimatedPace, Double estimatedAssistRatio, Double estimatedOffensiveReboundPct,
                                Double estimatedDefensiveReboundPct, Double estimatedReboundPct, Double estimatedTurnoverPct,
                                Integer gpRank, Integer winRank, Integer lossRank, Integer winPctRank, Integer minRank, Integer estimatedOffensiveRatingRank,
                                Integer estimatedDefensiveRatingRank, Integer estimatedNetRatingRank, Integer estimatedAssistRatioRank,
                                Integer estimatedOffensiveReboundPctRank, Integer estimatedDefensiveReboundPctRank, Integer estimatedReboundPctRank,
                                Integer estimatedTurnoverPctRank, Integer estimatedPaceRank) {
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

    public Double getEstimatedOffensiveRating() {
        return estimatedOffensiveRating;
    }

    public void setEstimatedOffensiveRating(Double estimatedOffensiveRating) {
        this.estimatedOffensiveRating = estimatedOffensiveRating;
    }

    public Double getEstimatedDefensiveRating() {
        return estimatedDefensiveRating;
    }

    public void setEstimatedDefensiveRating(Double estimatedDefensiveRating) {
        this.estimatedDefensiveRating = estimatedDefensiveRating;
    }

    public Double getEstimatedNetRating() {
        return estimatedNetRating;
    }

    public void setEstimatedNetRating(Double estimatedNetRating) {
        this.estimatedNetRating = estimatedNetRating;
    }

    public Double getEstimatedPace() {
        return estimatedPace;
    }

    public void setEstimatedPace(Double estimatedPace) {
        this.estimatedPace = estimatedPace;
    }

    public Double getEstimatedAssistRatio() {
        return estimatedAssistRatio;
    }

    public void setEstimatedAssistRatio(Double estimatedAssistRatio) {
        this.estimatedAssistRatio = estimatedAssistRatio;
    }

    public Double getEstimatedOffensiveReboundPct() {
        return estimatedOffensiveReboundPct;
    }

    public void setEstimatedOffensiveReboundPct(Double estimatedOffensiveReboundPct) {
        this.estimatedOffensiveReboundPct = estimatedOffensiveReboundPct;
    }

    public Double getEstimatedDefensiveReboundPct() {
        return estimatedDefensiveReboundPct;
    }

    public void setEstimatedDefensiveReboundPct(Double estimatedDefensiveReboundPct) {
        this.estimatedDefensiveReboundPct = estimatedDefensiveReboundPct;
    }

    public Double getEstimatedReboundPct() {
        return estimatedReboundPct;
    }

    public void setEstimatedReboundPct(Double estimatedReboundPct) {
        this.estimatedReboundPct = estimatedReboundPct;
    }

    public Double getEstimatedTurnoverPct() {
        return estimatedTurnoverPct;
    }

    public void setEstimatedTurnoverPct(Double estimatedTurnoverPct) {
        this.estimatedTurnoverPct = estimatedTurnoverPct;
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

    public Integer getWinPctRank() {
        return winPctRank;
    }

    public void setWinPctRank(Integer winPctRank) {
        this.winPctRank = winPctRank;
    }

    public Integer getMinRank() {
        return minRank;
    }

    public void setMinRank(Integer minRank) {
        this.minRank = minRank;
    }

    public Integer getEstimatedOffensiveRatingRank() {
        return estimatedOffensiveRatingRank;
    }

    public void setEstimatedOffensiveRatingRank(Integer estimatedOffensiveRatingRank) {
        this.estimatedOffensiveRatingRank = estimatedOffensiveRatingRank;
    }

    public Integer getEstimatedDefensiveRatingRank() {
        return estimatedDefensiveRatingRank;
    }

    public void setEstimatedDefensiveRatingRank(Integer estimatedDefensiveRatingRank) {
        this.estimatedDefensiveRatingRank = estimatedDefensiveRatingRank;
    }

    public Integer getEstimatedNetRatingRank() {
        return estimatedNetRatingRank;
    }

    public void setEstimatedNetRatingRank(Integer estimatedNetRatingRank) {
        this.estimatedNetRatingRank = estimatedNetRatingRank;
    }

    public Integer getEstimatedAssistRatioRank() {
        return estimatedAssistRatioRank;
    }

    public void setEstimatedAssistRatioRank(Integer estimatedAssistRatioRank) {
        this.estimatedAssistRatioRank = estimatedAssistRatioRank;
    }

    public Integer getEstimatedOffensiveReboundPctRank() {
        return estimatedOffensiveReboundPctRank;
    }

    public void setEstimatedOffensiveReboundPctRank(Integer estimatedOffensiveReboundPctRank) {
        this.estimatedOffensiveReboundPctRank = estimatedOffensiveReboundPctRank;
    }

    public Integer getEstimatedDefensiveReboundPctRank() {
        return estimatedDefensiveReboundPctRank;
    }

    public void setEstimatedDefensiveReboundPctRank(Integer estimatedDefensiveReboundPctRank) {
        this.estimatedDefensiveReboundPctRank = estimatedDefensiveReboundPctRank;
    }

    public Integer getEstimatedReboundPctRank() {
        return estimatedReboundPctRank;
    }

    public void setEstimatedReboundPctRank(Integer estimatedReboundPctRank) {
        this.estimatedReboundPctRank = estimatedReboundPctRank;
    }

    public Integer getEstimatedTurnoverPctRank() {
        return estimatedTurnoverPctRank;
    }

    public void setEstimatedTurnoverPctRank(Integer estimatedTurnoverPctRank) {
        this.estimatedTurnoverPctRank = estimatedTurnoverPctRank;
    }

    public Integer getEstimatedPaceRank() {
        return estimatedPaceRank;
    }

    public void setEstimatedPaceRank(Integer estimatedPaceRank) {
        this.estimatedPaceRank = estimatedPaceRank;
    }
}
