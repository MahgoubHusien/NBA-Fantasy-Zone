package com.nba.nba_zone.service;

import com.nba.nba_zone.model.Standings;
import com.nba.nba_zone.model.TeamEstimatedMetrics;
import com.nba.nba_zone.model.TeamStats;
import com.nba.nba_zone.repository.StandingsRepository;
import com.nba.nba_zone.repository.TeamEstimatedMetricsRepository;
import com.nba.nba_zone.repository.TeamStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamStatsRepository teamStatsRepository;

    @Autowired
    private TeamEstimatedMetricsRepository teamEstimatedMetricsRepository;

    @Autowired
    private StandingsRepository standingsRepository;

    // TeamStats related methods

    public List<TeamStats> getAllTeamStats() {
        return teamStatsRepository.findAll();
    }

    public TeamStats getTeamStatsById(Integer teamId) {
        return teamStatsRepository.findById(teamId).orElse(null);
    }

    public List<TeamStats> getTeamStatsByTeamName(String teamName) {
        return teamStatsRepository.findByTeamName(teamName);
    }

    public List<TeamStats> getTeamStatsByWinPctGreaterThan(Double winPct) {
        return teamStatsRepository.findByWinPctGreaterThan(winPct);
    }

    public TeamStats saveTeamStats(TeamStats teamStats) {
        return teamStatsRepository.save(teamStats);
    }

    public void deleteTeamStats(Integer teamId) {
        teamStatsRepository.deleteById(teamId);
    }

    // TeamEstimatedMetrics related methods

    public List<TeamEstimatedMetrics> getAllTeamEstimatedMetrics() {
        return teamEstimatedMetricsRepository.findAll();
    }

    public TeamEstimatedMetrics getTeamEstimatedMetricsById(Integer teamId) {
        return teamEstimatedMetricsRepository.findById(teamId).orElse(null);
    }

    public List<TeamEstimatedMetrics> getTeamEstimatedMetricsByTeamName(String teamName) {
        return teamEstimatedMetricsRepository.findByTeamName(teamName);
    }

    public List<TeamEstimatedMetrics> getTeamEstimatedMetricsByOffensiveRatingGreaterThan(Double estimatedOffensiveRating) {
        return teamEstimatedMetricsRepository.findByEstimatedOffensiveRatingGreaterThan(estimatedOffensiveRating);
    }

    public TeamEstimatedMetrics saveTeamEstimatedMetrics(TeamEstimatedMetrics teamEstimatedMetrics) {
        return teamEstimatedMetricsRepository.save(teamEstimatedMetrics);
    }

    public void deleteTeamEstimatedMetrics(Integer teamId) {
        teamEstimatedMetricsRepository.deleteById(teamId);
    }

    // Standings related methods

    public List<Standings> getAllStandings() {
        return standingsRepository.findAll();
    }

    public Standings getStandingsById(Integer teamId) {
        return standingsRepository.findById(teamId).orElse(null);
    }

    public List<Standings> getStandingsByTeamName(String teamName) {
        return standingsRepository.findByTeamName(teamName);
    }

    public List<Standings> getStandingsByConference(String conference) {
        return standingsRepository.findByConference(conference);
    }

    public List<Standings> getStandingsByWinPct() {
        return standingsRepository.findAllByOrderByWinPctDesc();
    }

    public List<Standings> getStandingsByClinchedPlayoffBirth(Integer clinchedPlayoffBirth) {
        return standingsRepository.findByClinchedPlayoffBirth(clinchedPlayoffBirth);
    }

    public Standings saveStandings(Standings standings) {
        return standingsRepository.save(standings);
    }

    public void deleteStandings(Integer teamId) {
        standingsRepository.deleteById(teamId);
    }
}
