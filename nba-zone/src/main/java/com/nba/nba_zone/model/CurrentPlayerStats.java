package com.nba.nba_zone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "current_player_stats")
public class CurrentPlayerStats extends PlayerBase {

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
    private Integer dd2;
    private Integer td3;
    private Integer gpRank;
    private Integer wRank;
    private Integer lRank;
    private Integer wPctRank;
    private Integer minRank;
    private Integer fgmRank;
    private Integer fgaRank;
    private Integer fgPctRank;
    private Integer fg3mRank;
    private Integer fg3aRank;
    private Integer fg3PctRank;
    private Integer ftmRank;
    private Integer ftaRank;
    private Integer ftPctRank;
    private Integer orebRank;
    private Integer drebRank;
    private Integer rebRank;
    private Integer astRank;
    private Integer tovRank;
    private Integer stlRank;
    private Integer blkRank;
    private Integer blkaRank;
    private Integer pfRank;
    private Integer pfdRank;
    private Integer ptsRank;
    private Integer plusMinusRank;
    private Integer nbaFantasyPtsRank;
    private Integer dd2Rank;
    private Integer td3Rank;
    private Integer cfid;
    private String cfparams;
    private String playerName;

    // No-argument constructor
    public CurrentPlayerStats() {}

    // All-argument constructor
    public CurrentPlayerStats(Long id, Integer playerId, String teamAbbreviation, Integer age, Integer gp, Integer w, Integer l, Double wPct, Double min, Double fgm, Double fga, Double fgPct, Double fg3m, Double fg3a, Double fg3Pct, Double ftm, Double fta, Double ftPct, Double oreb, Double dreb, Double reb, Double ast, Double tov, Double stl, Double blk, Double blka, Double pf, Double pfd, Double pts, Double plusMinus, Double nbaFantasyPts, Integer dd2, Integer td3, Integer gpRank, Integer wRank, Integer lRank, Integer wPctRank, Integer minRank, Integer fgmRank, Integer fgaRank, Integer fgPctRank, Integer fg3mRank, Integer fg3aRank, Integer fg3PctRank, Integer ftmRank, Integer ftaRank, Integer ftPctRank, Integer orebRank, Integer drebRank, Integer rebRank, Integer astRank, Integer tovRank, Integer stlRank, Integer blkRank, Integer blkaRank, Integer pfRank, Integer pfdRank, Integer ptsRank, Integer plusMinusRank, Integer nbaFantasyPtsRank, Integer dd2Rank, Integer td3Rank, Integer cfid, String cfparams, String playerName) {
        super(id, playerId, teamAbbreviation); // Calls the superclass constructor
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
        this.dd2 = dd2;
        this.td3 = td3;
        this.gpRank = gpRank;
        this.wRank = wRank;
        this.lRank = lRank;
        this.wPctRank = wPctRank;
        this.minRank = minRank;
        this.fgmRank = fgmRank;
        this.fgaRank = fgaRank;
        this.fgPctRank = fgPctRank;
        this.fg3mRank = fg3mRank;
        this.fg3aRank = fg3aRank;
        this.fg3PctRank = fg3PctRank;
        this.ftmRank = ftmRank;
        this.ftaRank = ftaRank;
        this.ftPctRank = ftPctRank;
        this.orebRank = orebRank;
        this.drebRank = drebRank;
        this.rebRank = rebRank;
        this.astRank = astRank;
        this.tovRank = tovRank;
        this.stlRank = stlRank;
        this.blkRank = blkRank;
        this.blkaRank = blkaRank;
        this.pfRank = pfRank;
        this.pfdRank = pfdRank;
        this.ptsRank = ptsRank;
        this.plusMinusRank = plusMinusRank;
        this.nbaFantasyPtsRank = nbaFantasyPtsRank;
        this.dd2Rank = dd2Rank;
        this.td3Rank = td3Rank;
        this.cfid = cfid;
        this.cfparams = cfparams;
        this.playerName = playerName;
    }

    // Getters and Setters
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Integer getGp() { return gp; }
    public void setGp(Integer gp) { this.gp = gp; }

    public Integer getW() { return w; }
    public void setW(Integer w) { this.w = w; }

    public Integer getL() { return l; }
    public void setL(Integer l) { this.l = l; }

    public Double getWPct() { return wPct; }
    public void setWPct(Double wPct) { this.wPct = wPct; }

    public Double getMin() { return min; }
    public void setMin(Double min) { this.min = min; }

    public Double getFgm() { return fgm; }
    public void setFgm(Double fgm) { this.fgm = fgm; }

    public Double getFga() { return fga; }
    public void setFga(Double fga) { this.fga = fga; }

    public Double getFgPct() { return fgPct; }
    public void setFgPct(Double fgPct) { this.fgPct = fgPct; }

    public Double getFg3m() { return fg3m; }
    public void setFg3m(Double fg3m) { this.fg3m = fg3m; }

    public Double getFg3a() { return fg3a; }
    public void setFg3a(Double fg3a) { this.fg3a = fg3a; }

    public Double getFg3Pct() { return fg3Pct; }
    public void setFg3Pct(Double fg3Pct) { this.fg3Pct = fg3Pct; }

    public Double getFtm() { return ftm; }
    public void setFtm(Double ftm) { this.ftm = ftm; }

    public Double getFta() { return fta; }
    public void setFta(Double fta) { this.fta = fta; }

    public Double getFtPct() { return ftPct; }
    public void setFtPct(Double ftPct) { this.ftPct = ftPct; }

    public Double getOreb() { return oreb; }
    public void setOreb(Double oreb) { this.oreb = oreb; }

    public Double getDreb() { return dreb; }
    public void setDreb(Double dreb) { this.dreb = dreb; }

    public Double getReb() { return reb; }
    public void setReb(Double reb) { this.reb = reb; }

    public Double getAst() { return ast; }
    public void setAst(Double ast) { this.ast = ast; }

    public Double getTov() { return tov; }
    public void setTov(Double tov) { this.tov = tov; }

    public Double getStl() { return stl; }
    public void setStl(Double stl) { this.stl = stl; }

    public Double getBlk() { return blk; }
    public void setBlk(Double blk) { this.blk = blk; }

    public Double getBlka() { return blka; }
    public void setBlka(Double blka) { this.blka = blka; }

    public Double getPf() { return pf; }
    public void setPf(Double pf) { this.pf = pf; }

    public Double getPfd() { return pfd; }
    public void setPfd(Double pfd) { this.pfd = pfd; }

    public Double getPts() { return pts; }
    public void setPts(Double pts) { this.pts = pts; }

    public Double getPlusMinus() { return plusMinus; }
    public void setPlusMinus(Double plusMinus) { this.plusMinus = plusMinus; }

    public Double getNbaFantasyPts() { return nbaFantasyPts; }
    public void setNbaFantasyPts(Double nbaFantasyPts) { this.nbaFantasyPts = nbaFantasyPts; }

    public Integer getDd2() { return dd2; }
    public void setDd2(Integer dd2) { this.dd2 = dd2; }

    public Integer getTd3() { return td3; }
    public void setTd3(Integer td3) { this.td3 = td3; }

    public Integer getGpRank() { return gpRank; }
    public void setGpRank(Integer gpRank) { this.gpRank = gpRank; }

    public Integer getWRank() { return wRank; }
    public void setWRank(Integer wRank) { this.wRank = wRank; }

    public Integer getLRank() { return lRank; }
    public void setLRank(Integer lRank) { this.lRank = lRank; }

    public Integer getWPctRank() { return wPctRank; }
    public void setWPctRank(Integer wPctRank) { this.wPctRank = wPctRank; }

    public Integer getMinRank() { return minRank; }
    public void setMinRank(Integer minRank) { this.minRank = minRank; }

    public Integer getFgmRank() { return fgmRank; }
    public void setFgmRank(Integer fgmRank) { this.fgmRank = fgmRank; }

    public Integer getFgaRank() { return fgaRank; }
    public void setFgaRank(Integer fgaRank) { this.fgaRank = fgaRank; }

    public Integer getFgPctRank() { return fgPctRank; }
    public void setFgPctRank(Integer fgPctRank) { this.fgPctRank = fgPctRank; }

    public Integer getFg3mRank() { return fg3mRank; }
    public void setFg3mRank(Integer fg3mRank) { this.fg3mRank = fg3mRank; }

    public Integer getFg3aRank() { return fg3aRank; }
    public void setFg3aRank(Integer fg3aRank) { this.fg3aRank = fg3aRank; }

    public Integer getFg3PctRank() { return fg3PctRank; }
    public void setFg3PctRank(Integer fg3PctRank) { this.fg3PctRank = fg3PctRank; }

    public Integer getFtmRank() { return ftmRank; }
    public void setFtmRank(Integer ftmRank) { this.ftmRank = ftmRank; }

    public Integer getFtaRank() { return ftaRank; }
    public void setFtaRank(Integer ftaRank) { this.ftaRank = ftaRank; }

    public Integer getFtPctRank() { return ftPctRank; }
    public void setFtPctRank(Integer ftPctRank) { this.ftPctRank = ftPctRank; }

    public Integer getOrebRank() { return orebRank; }
    public void setOrebRank(Integer orebRank) { this.orebRank = orebRank; }

    public Integer getDrebRank() { return drebRank; }
    public void setDrebRank(Integer drebRank) { this.drebRank = drebRank; }

    public Integer getRebRank() { return rebRank; }
    public void setRebRank(Integer rebRank) { this.rebRank = rebRank; }

    public Integer getAstRank() { return astRank; }
    public void setAstRank(Integer astRank) { this.astRank = astRank; }

    public Integer getTovRank() { return tovRank; }
    public void setTovRank(Integer tovRank) { this.tovRank = tovRank; }

    public Integer getStlRank() { return stlRank; }
    public void setStlRank(Integer stlRank) { this.stlRank = stlRank; }

    public Integer getBlkRank() { return blkRank; }
    public void setBlkRank(Integer blkRank) { this.blkRank = blkRank; }

    public Integer getBlkaRank() { return blkaRank; }
    public void setBlkaRank(Integer blkaRank) { this.blkaRank = blkaRank; }

    public Integer getPfRank() { return pfRank; }
    public void setPfRank(Integer pfRank) { this.pfRank = pfRank; }

    public Integer getPfdRank() { return pfdRank; }
    public void setPfdRank(Integer pfdRank) { this.pfdRank = pfdRank; }

    public Integer getPtsRank() { return ptsRank; }
    public void setPtsRank(Integer ptsRank) { this.ptsRank = ptsRank; }

    public Integer getPlusMinusRank() { return plusMinusRank; }
    public void setPlusMinusRank(Integer plusMinusRank) { this.plusMinusRank = plusMinusRank; }

    public Integer getNbaFantasyPtsRank() { return nbaFantasyPtsRank; }
    public void setNbaFantasyPtsRank(Integer nbaFantasyPtsRank) { this.nbaFantasyPtsRank = nbaFantasyPtsRank; }

    public Integer getDd2Rank() { return dd2Rank; }
    public void setDd2Rank(Integer dd2Rank) { this.dd2Rank = dd2Rank; }

    public Integer getTd3Rank() { return td3Rank; }
    public void setTd3Rank(Integer td3Rank) { this.td3Rank = td3Rank; }

    public Integer getCfid() { return cfid; }
    public void setCfid(Integer cfid) { this.cfid = cfid; }

    public String getCfparams() { return cfparams; }
    public void setCfparams(String cfparams) { this.cfparams = cfparams; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
}
