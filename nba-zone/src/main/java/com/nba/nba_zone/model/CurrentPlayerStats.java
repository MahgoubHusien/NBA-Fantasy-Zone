package com.nba.nba_zone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "current_player_stats")
public class CurrentPlayerStats extends PlayerBase {

    private Integer orebRank;
    private Integer dd2Rank;
    private Integer td3Rank;
    private Integer cfid;
    private Integer age;
    private Integer gp;
    private Integer w;
    private Integer l;
    private Double wPct;
    private Double min;
    private Double fgm;
    private Double fga;
    private Double fgPct;
    private Double fg3m;
    private Double fg3a;
    private Double fg3Pct;
    private Double ftm;
    private Double fta;
    private Double ftPct;
    private Double oreb;
    private Double dreb;
    private Double reb;
    private Double ast;
    private Double tov;
    private Double stl;
    private Double blk;
    private Double blka;
    private Double pf;
    private Double pfd;
    private Double pts;
    private Double plusMinus;
    private Double nbaFantasyPts;

    public CurrentPlayerStats() {}

    public CurrentPlayerStats(Long id, Integer playerId, String teamAbbreviation, Integer orebRank, Integer dd2Rank,
                              Integer td3Rank, Integer cfid, Integer age, Integer gp, Integer w, Integer l,
                              Double wPct, Double min, Double fgm, Double fga, Double fgPct, Double fg3m,
                              Double fg3a, Double fg3Pct, Double ftm, Double fta, Double ftPct, Double oreb,
                              Double dreb, Double reb, Double ast, Double tov, Double stl, Double blk,
                              Double blka, Double pf, Double pfd, Double pts, Double plusMinus, Double nbaFantasyPts) {
        super(id, playerId, teamAbbreviation);
        this.orebRank = orebRank;
        this.dd2Rank = dd2Rank;
        this.td3Rank = td3Rank;
        this.cfid = cfid;
        this.age = age;
        this.gp = gp;
        this.w = w;
        this.l = l;
        this.wPct = wPct;
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
        this.tov = tov;
        this.stl = stl;
        this.blk = blk;
        this.blka = blka;
        this.pf = pf;
        this.pfd = pfd;
        this.pts = pts;
        this.plusMinus = plusMinus;
        this.nbaFantasyPts = nbaFantasyPts;
    }

    // Getters and Setters

    public Integer getOrebRank() {
        return orebRank;
    }

    public void setOrebRank(Integer orebRank) {
        this.orebRank = orebRank;
    }

    public Integer getDd2Rank() {
        return dd2Rank;
    }

    public void setDd2Rank(Integer dd2Rank) {
        this.dd2Rank = dd2Rank;
    }

    public Integer getTd3Rank() {
        return td3Rank;
    }

    public void setTd3Rank(Integer td3Rank) {
        this.td3Rank = td3Rank;
    }

    public Integer getCfid() {
        return cfid;
    }

    public void setCfid(Integer cfid) {
        this.cfid = cfid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGp() {
        return gp;
    }

    public void setGp(Integer gp) {
        this.gp = gp;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Double getWPct() {
        return wPct;
    }

    public void setWPct(Double wPct) {
        this.wPct = wPct;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getFgm() {
        return fgm;
    }

    public void setFgm(Double fgm) {
        this.fgm = fgm;
    }

    public Double getFga() {
        return fga;
    }

    public void setFga(Double fga) {
        this.fga = fga;
    }

    public Double getFgPct() {
        return fgPct;
    }

    public void setFgPct(Double fgPct) {
        this.fgPct = fgPct;
    }

    public Double getFg3m() {
        return fg3m;
    }

    public void setFg3m(Double fg3m) {
        this.fg3m = fg3m;
    }

    public Double getFg3a() {
        return fg3a;
    }

    public void setFg3a(Double fg3a) {
        this.fg3a = fg3a;
    }

    public Double getFg3Pct() {
        return fg3Pct;
    }

    public void setFg3Pct(Double fg3Pct) {
        this.fg3Pct = fg3Pct;
    }

    public Double getFtm() {
        return ftm;
    }

    public void setFtm(Double ftm) {
        this.ftm = ftm;
    }

    public Double getFta() {
        return fta;
    }

    public void setFta(Double fta) {
        this.fta = fta;
    }

    public Double getFtPct() {
        return ftPct;
    }

    public void setFtPct(Double ftPct) {
        this.ftPct = ftPct;
    }

    public Double getOreb() {
        return oreb;
    }

    public void setOreb(Double oreb) {
        this.oreb = oreb;
    }

    public Double getDreb() {
        return dreb;
    }

    public void setDreb(Double dreb) {
        this.dreb = dreb;
    }

    public Double getReb() {
        return reb;
    }

    public void setReb(Double reb) {
        this.reb = reb;
    }

    public Double getAst() {
        return ast;
    }

    public void setAst(Double ast) {
        this.ast = ast;
    }

    public Double getTov() {
        return tov;
    }

    public void setTov(Double tov) {
        this.tov = tov;
    }

    public Double getStl() {
        return stl;
    }

    public void setStl(Double stl) {
        this.stl = stl;
    }

    public Double getBlk() {
        return blk;
    }

    public void setBlk(Double blk) {
        this.blk = blk;
    }

    public Double getBlka() {
        return blka;
    }

    public void setBlka(Double blka) {
        this.blka = blka;
    }

    public Double getPf() {
        return pf;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

    public Double getPfd() {
        return pfd;
    }

    public void setPfd(Double pfd) {
        this.pfd = pfd;
    }

    public Double getPts() {
        return pts;
    }

    public void setPts(Double pts) {
        this.pts = pts;
    }

    public Double getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Double plusMinus) {
        this.plusMinus = plusMinus;
    }

    public Double getNbaFantasyPts() {
        return nbaFantasyPts;
    }

    public void setNbaFantasyPts(Double nbaFantasyPts) {
        this.nbaFantasyPts = nbaFantasyPts;
    }
}
