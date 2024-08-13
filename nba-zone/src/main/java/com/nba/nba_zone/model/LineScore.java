package com.nba.nba_zone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.lang.String;

@Entity
@Table(name = "line_score")
public class LineScore extends GameBase {

    private String gameDateEst;
    private Integer gameSequence;
    private Integer teamId;
    private String teamAbbreviation;
    private String teamCityName;
    private String teamName;
    private String teamWinsLosses;
    private Integer ptsQtr1;
    private Integer ptsQtr2;
    private Integer ptsQtr3;
    private Integer ptsQtr4;
    private Integer ptsOt1;
    private Integer ptsOt2;
    private Integer ptsOt3;
    private Integer ptsOt4;
    private Integer ptsOt5;
    private Integer ptsOt6;
    private Integer ptsOt7;
    private Integer ptsOt8;
    private Integer ptsOt9;
    private Integer ptsOt10;
    private Integer pts;
    private Double fgPct;
    private Double ftPct;

    @Column(name = "fg3_pct")
    private Double fg3Pct;

    private Integer ast;
    private Integer reb;
    private Integer tov;

    public LineScore() {}

    public LineScore(Long id, String gameId, String gameDateEst, Integer gameSequence, Integer teamId, String teamAbbreviation,
                     String teamCityName, String teamName, String teamWinsLosses, Integer ptsQtr1, Integer ptsQtr2, Integer ptsQtr3, Integer ptsQtr4,
                     Integer ptsOt1, Integer ptsOt2, Integer ptsOt3, Integer ptsOt4, Integer ptsOt5, Integer ptsOt6, Integer ptsOt7, Integer ptsOt8, Integer ptsOt9,
                     Integer ptsOt10, Integer pts, Double fgPct, Double ftPct, Double fg3Pct, Integer ast, Integer reb, Integer tov) {
        super(id, gameId);
        this.gameDateEst = gameDateEst;
        this.gameSequence = gameSequence;
        this.teamId = teamId;
        this.teamAbbreviation = teamAbbreviation;
        this.teamCityName = teamCityName;
        this.teamName = teamName;
        this.teamWinsLosses = teamWinsLosses;
        this.ptsQtr1 = ptsQtr1;
        this.ptsQtr2 = ptsQtr2;
        this.ptsQtr3 = ptsQtr3;
        this.ptsQtr4 = ptsQtr4;
        this.ptsOt1 = ptsOt1;
        this.ptsOt2 = ptsOt2;
        this.ptsOt3 = ptsOt3;
        this.ptsOt4 = ptsOt4;
        this.ptsOt5 = ptsOt5;
        this.ptsOt6 = ptsOt6;
        this.ptsOt7 = ptsOt7;
        this.ptsOt8 = ptsOt8;
        this.ptsOt9 = ptsOt9;
        this.ptsOt10 = ptsOt10;
        this.pts = pts;
        this.fgPct = fgPct;
        this.ftPct = ftPct;
        this.fg3Pct = fg3Pct;
        this.ast = ast;
        this.reb = reb;
        this.tov = tov;
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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamAbbreviation() {
        return teamAbbreviation;
    }

    public void setTeamAbbreviation(String teamAbbreviation) {
        this.teamAbbreviation = teamAbbreviation;
    }

    public String getTeamCityName() {
        return teamCityName;
    }

    public void setTeamCityName(String teamCityName) {
        this.teamCityName = teamCityName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamWinsLosses() {
        return teamWinsLosses;
    }

    public void setTeamWinsLosses(String teamWinsLosses) {
        this.teamWinsLosses = teamWinsLosses;
    }

    public Integer getPtsQtr1() {
        return ptsQtr1;
    }

    public void setPtsQtr1(Integer ptsQtr1) {
        this.ptsQtr1 = ptsQtr1;
    }

    public Integer getPtsQtr2() {
        return ptsQtr2;
    }

    public void setPtsQtr2(Integer ptsQtr2) {
        this.ptsQtr2 = ptsQtr2;
    }

    public Integer getPtsQtr3() {
        return ptsQtr3;
    }

    public void setPtsQtr3(Integer ptsQtr3) {
        this.ptsQtr3 = ptsQtr3;
    }

    public Integer getPtsQtr4() {
        return ptsQtr4;
    }

    public void setPtsQtr4(Integer ptsQtr4) {
        this.ptsQtr4 = ptsQtr4;
    }

    public Integer getPtsOt1() {
        return ptsOt1;
    }

    public void setPtsOt1(Integer ptsOt1) {
        this.ptsOt1 = ptsOt1;
    }

    public Integer getPtsOt2() {
        return ptsOt2;
    }

    public void setPtsOt2(Integer ptsOt2) {
        this.ptsOt2 = ptsOt2;
    }

    public Integer getPtsOt3() {
        return ptsOt3;
    }

    public void setPtsOt3(Integer ptsOt3) {
        this.ptsOt3 = ptsOt3;
    }

    public Integer getPtsOt4() {
        return ptsOt4;
    }

    public void setPtsOt4(Integer ptsOt4) {
        this.ptsOt4 = ptsOt4;
    }

    public Integer getPtsOt5() {
        return ptsOt5;
    }

    public void setPtsOt5(Integer ptsOt5) {
        this.ptsOt5 = ptsOt5;
    }

    public Integer getPtsOt6() {
        return ptsOt6;
    }

    public void setPtsOt6(Integer ptsOt6) {
        this.ptsOt6 = ptsOt6;
    }

    public Integer getPtsOt7() {
        return ptsOt7;
    }

    public void setPtsOt7(Integer ptsOt7) {
        this.ptsOt7 = ptsOt7;
    }

    public Integer getPtsOt8() {
        return ptsOt8;
    }

    public void setPtsOt8(Integer ptsOt8) {
        this.ptsOt8 = ptsOt8;
    }

    public Integer getPtsOt9() {
        return ptsOt9;
    }

    public void setPtsOt9(Integer ptsOt9) {
        this.ptsOt9 = ptsOt9;
    }

    public Integer getPtsOt10() {
        return ptsOt10;
    }

    public void setPtsOt10(Integer ptsOt10) {
        this.ptsOt10 = ptsOt10;
    }

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
    }

    public Double getFgPct() {
        return fgPct;
    }

    public void setFgPct(Double fgPct) {
        this.fgPct = fgPct;
    }

    public Double getFtPct() {
        return ftPct;
    }

    public void setFtPct(Double ftPct) {
        this.ftPct = ftPct;
    }

    public Double getFg3Pct() {
        return fg3Pct;
    }

    public void setFg3Pct(Double fg3Pct) {
        this.fg3Pct = fg3Pct;
    }

    public Integer getAst() {
        return ast;
    }

    public void setAst(Integer ast) {
        this.ast = ast;
    }

    public Integer getReb() {
        return reb;
    }

    public void setReb(Integer reb) {
        this.reb = reb;
    }

    public Integer getTov() {
        return tov;
    }

    public void setTov(Integer tov) {
        this.tov = tov;
    }}
