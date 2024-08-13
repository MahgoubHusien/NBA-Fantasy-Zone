package com.nba.nba_zone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Games extends GameBase {

    private Integer seasonId;
    private String gameDate;
    private Integer homeTeamId;
    private String homeTeamName;
    private String homeTeamAbbreviation;
    private Integer visitorTeamId;
    private String visitorTeamName;
    private String visitorTeamAbbreviation;
    private String matchup;
    private String wl;
    private Integer min;
    private Integer pts;
    private Integer fgm;
    private Integer fga;
    private Double fgPct;
    private Integer fg3m;
    private Integer fg3a;
    private Double fg3Pct;
    private Integer ftm;
    private Integer fta;
    private Double ftPct;
    private Integer oreb;
    private Integer dreb;
    private Integer reb;
    private Integer ast;
    private Integer stl;
    private Integer blk;
    private Integer tov;
    private Integer pf;
    private Integer plusMinus;

    public Games() {}

    public Games(Long id, String gameId, Integer seasonId, String gameDate, Integer homeTeamId, String homeTeamName,
                 String homeTeamAbbreviation, Integer visitorTeamId, String visitorTeamName, String visitorTeamAbbreviation,
                 String matchup, String wl, Integer min, Integer pts, Integer fgm, Integer fga, Double fgPct, Integer fg3m, Integer fg3a, Double fg3Pct,
                 Integer ftm, Integer fta, Double ftPct, Integer oreb, Integer dreb, Integer reb, Integer ast, Integer stl, Integer blk, Integer tov, Integer pf,
                 Integer plusMinus) {
        super(id, gameId);
        this.seasonId = seasonId;
        this.gameDate = gameDate;
        this.homeTeamId = homeTeamId;
        this.homeTeamName = homeTeamName;
        this.homeTeamAbbreviation = homeTeamAbbreviation;
        this.visitorTeamId = visitorTeamId;
        this.visitorTeamName = visitorTeamName;
        this.visitorTeamAbbreviation = visitorTeamAbbreviation;
        this.matchup = matchup;
        this.wl = wl;
        this.min = min;
        this.pts = pts;
        this.fgm = fgm;
        this.fga = fga;
        this.fgPct = fgPct;
        this.fg3m = fg3m;
        this.fg3a = fg3a;
        this.fg3Pct = fg3Pct;
        this.ftm = ftm;
        this.fta = fta;
        this.ftPct = ftPct;
        this.oreb = oreb;
        this.dreb = dreb;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.tov = tov;
        this.pf = pf;
        this.plusMinus = plusMinus;
    }

    // Getters and Setters
    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getHomeTeamAbbreviation() {
        return homeTeamAbbreviation;
    }

    public void setHomeTeamAbbreviation(String homeTeamAbbreviation) {
        this.homeTeamAbbreviation = homeTeamAbbreviation;
    }

    public Integer getVisitorTeamId() {
        return visitorTeamId;
    }

    public void setVisitorTeamId(Integer visitorTeamId) {
        this.visitorTeamId = visitorTeamId;
    }

    public String getVisitorTeamName() {
        return visitorTeamName;
    }

    public void setVisitorTeamName(String visitorTeamName) {
        this.visitorTeamName = visitorTeamName;
    }

    public String getVisitorTeamAbbreviation() {
        return visitorTeamAbbreviation;
    }

    public void setVisitorTeamAbbreviation(String visitorTeamAbbreviation) {
        this.visitorTeamAbbreviation = visitorTeamAbbreviation;
    }

    public String getMatchup() {
        return matchup;
    }

    public void setMatchup(String matchup) {
        this.matchup = matchup;
    }

    public String getWl() {
        return wl;
    }

    public void setWl(String wl) {
        this.wl = wl;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
    }

    public Integer getFgm() {
        return fgm;
    }

    public void setFgm(Integer fgm) {
        this.fgm = fgm;
    }

    public Integer getFga() {
        return fga;
    }

    public void setFga(Integer fga) {
        this.fga = fga;
    }

    public Double getFgPct() {
        return fgPct;
    }

    public void setFgPct(Double fgPct) {
        this.fgPct = fgPct;
    }

    public Integer getFg3m() {
        return fg3m;
    }

    public void setFg3m(Integer fg3m) {
        this.fg3m = fg3m;
    }

    public Integer getFg3a() {
        return fg3a;
    }

    public void setFg3a(Integer fg3a) {
        this.fg3a = fg3a;
    }

    public Double getFg3Pct() {
        return fg3Pct;
    }

    public void setFg3Pct(Double fg3Pct) {
        this.fg3Pct = fg3Pct;
    }

    public Integer getFtm() {
        return ftm;
    }

    public void setFtm(Integer ftm) {
        this.ftm = ftm;
    }

    public Integer getFta() {
        return fta;
    }

    public void setFta(Integer fta) {
        this.fta = fta;
    }

    public Double getFtPct() {
        return ftPct;
    }

    public void setFtPct(Double ftPct) {
        this.ftPct = ftPct;
    }

    public Integer getOreb() {
        return oreb;
    }

    public void setOreb(Integer oreb) {
        this.oreb = oreb;
    }

    public Integer getDreb() {
        return dreb;
    }

    public void setDreb(Integer dreb) {
        this.dreb = dreb;
    }

    public Integer getReb() {
        return reb;
    }

    public void setReb(Integer reb) {
        this.reb = reb;
    }

    public Integer getAst() {
        return ast;
    }

    public void setAst(Integer ast) {
        this.ast = ast;
    }

    public Integer getStl() {
        return stl;
    }

    public void setStl(Integer stl) {
        this.stl = stl;
    }

    public Integer getBlk() {
        return blk;
    }

    public void setBlk(Integer blk) {
        this.blk = blk;
    }

    public Integer getTov() {
        return tov;
    }

    public void setTov(Integer tov) {
        this.tov = tov;
    }

    public Integer getPf() {
        return pf;
    }

    public void setPf(Integer pf) {
        this.pf = pf;
    }

    public Integer getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Integer plusMinus) {
        this.plusMinus = plusMinus;
    }}
