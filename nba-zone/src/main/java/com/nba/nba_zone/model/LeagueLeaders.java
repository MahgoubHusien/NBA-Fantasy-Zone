package com.nba.nba_zone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class LeagueLeaders extends PlayerBase {

    private Integer rank;
    private String playerName;
    private String team;
    private Integer gp;
    private Integer min;
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
    private Integer pts;
    private Integer eff;
    private Double astTov;
    private Double stlTov;
    @Column(name = "photo")
    private String photoUrl;

    // No-argument constructor
    public LeagueLeaders() {}

    // All-argument constructor
    public LeagueLeaders(Long id, Integer playerId, String teamAbbreviation, Integer rank, String playerName, String team, Integer gp, Integer min, Integer fgm, Integer fga, Double fgPct, Integer fg3m, Integer fg3a, Double fg3Pct, Integer ftm, Integer fta, Double ftPct, Integer oreb, Integer dreb, Integer reb, Integer ast, Integer stl, Integer blk, Integer tov, Integer pf, Integer pts, Integer eff, Double astTov, Double stlTov, String photoUrl) {
        super(id, playerId, teamAbbreviation);
        this.rank = rank;
        this.playerName = playerName;
        this.team = team;
        this.gp = gp;
        this.min = min;
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
        this.pts = pts;
        this.eff = eff;
        this.astTov = astTov;
        this.stlTov = stlTov;
        this.photoUrl = photoUrl;
    }
    // Getters and Setters

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getGp() {
        return gp;
    }

    public void setGp(Integer gp) {
        this.gp = gp;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
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

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
    }

    public Integer getEff() {
        return eff;
    }

    public void setEff(Integer eff) {
        this.eff = eff;
    }

    public Double getAstTov() {
        return astTov;
    }

    public void setAstTov(Double astTov) {
        this.astTov = astTov;
    }

    public Double getStlTov() {
        return stlTov;
    }

    public void setStlTov(Double stlTov) {
        this.stlTov = stlTov;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
