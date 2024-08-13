package com.nba.nba_zone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "player_box_stats")
public class PlayerBoxStats extends GameBase {

    private Integer playerId;
    private String playerName;
    private Integer teamId;
    private String teamAbbreviation;
    private String min;
    private Integer pts;
    private Integer reb;
    private Integer ast;
    private Integer stl;
    private Integer blk;
    private Integer tov;
    private Integer fgm;
    private Integer fga;
    private Double fgPct;
    private Integer fg3m;
    private Integer fg3a;
    private Double fg3Pct;
    private Integer ftm;
    private Integer fta;
    private Double ftPct;

    public PlayerBoxStats() {}

    public PlayerBoxStats(Long id, String gameId, Integer playerId, String playerName, Integer teamId, String teamAbbreviation, String min,
                          Integer pts, Integer reb, Integer ast, Integer stl, Integer blk, Integer tov, Integer fgm, Integer fga, Double fgPct, Integer fg3m,
                          Integer fg3a, Double fg3Pct, Integer ftm, Integer fta, Double ftPct) {
        super(id, gameId);
        this.playerId = playerId;
        this.playerName = playerName;
        this.teamId = teamId;
        this.teamAbbreviation = teamAbbreviation;
        this.min = min;
        this.pts = pts;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.tov = tov;
        this.fgm = fgm;
        this.fga = fga;
        this.fgPct = fgPct;
        this.fg3m = fg3m;
        this.fg3a = fg3a;
        this.fg3Pct = fg3Pct;
        this.ftm = ftm;
        this.fta = fta;
        this.ftPct = ftPct;
    }

    // Getters and Setters
    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
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
}
