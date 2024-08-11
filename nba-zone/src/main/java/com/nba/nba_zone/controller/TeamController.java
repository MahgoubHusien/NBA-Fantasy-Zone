package com.nba.nba_zone.controller;


import com.nba.nba_zone.model.Standings;
import com.nba.nba_zone.model.TeamEstimatedMetrics;
import com.nba.nba_zone.model.TeamStats;
import com.nba.nba_zone.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nba")
public class TeamController {

    @Autowired
    private TeamService nbaService;

    // TeamStats Endpoints

    @GetMapping("/team-stats")
    public ResponseEntity<List<TeamStats>> getAllTeamStats() {
        List<TeamStats> teamStatsList = nbaService.getAllTeamStats();
        return ResponseEntity.ok(teamStatsList);
    }

    @GetMapping("/team-stats/{teamId}")
    public ResponseEntity<TeamStats> getTeamStatsById(@PathVariable int teamId) {
        TeamStats teamStats = nbaService.getTeamStatsById(teamId);
        if (teamStats != null) {
            return ResponseEntity.ok(teamStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/team-stats")
    public ResponseEntity<TeamStats> createTeamStats(@RequestBody TeamStats teamStats) {
        TeamStats createdTeamStats = nbaService.saveTeamStats(teamStats);
        return ResponseEntity.ok(createdTeamStats);
    }

    @PutMapping("/team-stats/{teamId}")
    public ResponseEntity<TeamStats> updateTeamStats(@PathVariable int teamId, @RequestBody TeamStats teamStats) {
        TeamStats existingTeamStats = nbaService.getTeamStatsById(teamId);
        if (existingTeamStats != null) {
            teamStats.setTeamId(teamId); // Ensure the ID remains the same
            TeamStats updatedTeamStats = nbaService.saveTeamStats(teamStats);
            return ResponseEntity.ok(updatedTeamStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/team-stats/{teamId}")
    public ResponseEntity<Void> deleteTeamStats(@PathVariable int teamId) {
        nbaService.deleteTeamStats(teamId);
        return ResponseEntity.noContent().build();
    }

    // TeamEstimatedMetrics Endpoints

    @GetMapping("/team-estimated-metrics")
    public ResponseEntity<List<TeamEstimatedMetrics>> getAllTeamEstimatedMetrics() {
        List<TeamEstimatedMetrics> teamEstimatedMetricsList = nbaService.getAllTeamEstimatedMetrics();
        return ResponseEntity.ok(teamEstimatedMetricsList);
    }

    @GetMapping("/team-estimated-metrics/{teamId}")
    public ResponseEntity<TeamEstimatedMetrics> getTeamEstimatedMetricsById(@PathVariable int teamId) {
        TeamEstimatedMetrics teamEstimatedMetrics = nbaService.getTeamEstimatedMetricsById(teamId);
        if (teamEstimatedMetrics != null) {
            return ResponseEntity.ok(teamEstimatedMetrics);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/team-estimated-metrics")
    public ResponseEntity<TeamEstimatedMetrics> createTeamEstimatedMetrics(@RequestBody TeamEstimatedMetrics teamEstimatedMetrics) {
        TeamEstimatedMetrics createdTeamEstimatedMetrics = nbaService.saveTeamEstimatedMetrics(teamEstimatedMetrics);
        return ResponseEntity.ok(createdTeamEstimatedMetrics);
    }

    @PutMapping("/team-estimated-metrics/{teamId}")
    public ResponseEntity<TeamEstimatedMetrics> updateTeamEstimatedMetrics(@PathVariable int teamId, @RequestBody TeamEstimatedMetrics teamEstimatedMetrics) {
        TeamEstimatedMetrics existingTeamEstimatedMetrics = nbaService.getTeamEstimatedMetricsById(teamId);
        if (existingTeamEstimatedMetrics != null) {
            teamEstimatedMetrics.setTeamId(teamId); // Ensure the ID remains the same
            TeamEstimatedMetrics updatedTeamEstimatedMetrics = nbaService.saveTeamEstimatedMetrics(teamEstimatedMetrics);
            return ResponseEntity.ok(updatedTeamEstimatedMetrics);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/team-estimated-metrics/{teamId}")
    public ResponseEntity<Void> deleteTeamEstimatedMetrics(@PathVariable int teamId) {
        nbaService.deleteTeamEstimatedMetrics(teamId);
        return ResponseEntity.noContent().build();
    }

    // Standings Endpoints

    @GetMapping("/standings")
    public ResponseEntity<List<Standings>> getAllStandings() {
        List<Standings> standingsList = nbaService.getAllStandings();
        return ResponseEntity.ok(standingsList);
    }

    @GetMapping("/standings/{teamId}")
    public ResponseEntity<Standings> getStandingsById(@PathVariable int teamId) {
        Standings standings = nbaService.getStandingsById(teamId);
        if (standings != null) {
            return ResponseEntity.ok(standings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/standings")
    public ResponseEntity<Standings> createStandings(@RequestBody Standings standings) {
        Standings createdStandings = nbaService.saveStandings(standings);
        return ResponseEntity.ok(createdStandings);
    }

    @PutMapping("/standings/{teamId}")
    public ResponseEntity<Standings> updateStandings(@PathVariable int teamId, @RequestBody Standings standings) {
        Standings existingStandings = nbaService.getStandingsById(teamId);
        if (existingStandings != null) {
            standings.setTeamId(teamId); // Ensure the ID remains the same
            Standings updatedStandings = nbaService.saveStandings(standings);
            return ResponseEntity.ok(updatedStandings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/standings/{teamId}")
    public ResponseEntity<Void> deleteStandings(@PathVariable int teamId) {
        nbaService.deleteStandings(teamId);
        return ResponseEntity.noContent().build();
    }
}
