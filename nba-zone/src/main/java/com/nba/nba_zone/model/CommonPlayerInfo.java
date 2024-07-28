package com.nba.nba_zone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "common_player_info")
public class CommonPlayerInfo extends PlayerBase {

    private Integer teamId;
    private Double ppg;
    private Double apg;
    private Double rpg;
    private Double spg;
    private Double topg;
    private Double bpg;
    private Double pfpg;
    private String firstName;
    private String lastName;
    private String weight;
    private String teamName;
    private String jersey;
    private String position;
    private String height;

    // No-argument constructor
    public CommonPlayerInfo() {}

    // All-argument constructor
    public CommonPlayerInfo(Long id, Integer playerId, String teamAbbreviation, Integer teamId,
                            Double ppg, Double apg, Double rpg, Double spg, Double topg,
                            Double bpg, Double pfpg, String firstName, String lastName,
                            String weight, String teamName, String jersey,
                            String position, String height) {
        super(id, playerId, teamAbbreviation);
        this.teamId = teamId;
        this.ppg = ppg;
        this.apg = apg;
        this.rpg = rpg;
        this.spg = spg;
        this.topg = topg;
        this.bpg = bpg;
        this.pfpg = pfpg;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.teamName = teamName;
        this.jersey = jersey;
        this.position = position;
        this.height = height;
    }

    // Getters and Setters
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Double getPpg() {
        return ppg;
    }

    public void setPpg(Double ppg) {
        this.ppg = ppg;
    }

    public Double getApg() {
        return apg;
    }

    public void setApg(Double apg) {
        this.apg = apg;
    }

    public Double getRpg() {
        return rpg;
    }

    public void setRpg(Double rpg) {
        this.rpg = rpg;
    }

    public Double getSpg() {
        return spg;
    }

    public void setSpg(Double spg) {
        this.spg = spg;
    }

    public Double getTopg() {
        return topg;
    }

    public void setTopg(Double topg) {
        this.topg = topg;
    }

    public Double getBpg() {
        return bpg;
    }

    public void setBpg(Double bpg) {
        this.bpg = bpg;
    }

    public Double getPfpg() {
        return pfpg;
    }

    public void setPfpg(Double pfpg) {
        this.pfpg = pfpg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getJersey() {
        return jersey;
    }

    public void setJersey(String jersey) {
        this.jersey = jersey;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
