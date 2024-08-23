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
@RequestMapping("/api/teams")
@CrossOrigin(origins = {"http://localhost:3000", "https://nbafantasyzone.com"})

public class TeamController {

    @Autowired
    private TeamService teamService;

    // TeamStats Endpoints

    @GetMapping("/team-stats")
    public ResponseEntity<List<TeamStats>> getAllTeamStats() {
        List<TeamStats> teamStatsList = teamService.getAllTeamStats();
        return ResponseEntity.ok(teamStatsList);
    }

    @GetMapping("/team-stats/{teamId}")
    public ResponseEntity<TeamStats> getTeamStatsById(@PathVariable Integer teamId) {
        TeamStats teamStats = teamService.getTeamStatsById(teamId);
        if (teamStats != null) {
            return ResponseEntity.ok(teamStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search-teams")
    public List<TeamStats> searchTeamsByName(@RequestParam String query) {
        return teamService.searchTeamsByName(query);
    }

    @PostMapping("/team-stats")
    public ResponseEntity<TeamStats> createTeamStats(@RequestBody TeamStats teamStats) {
        TeamStats createdTeamStats = teamService.saveTeamStats(teamStats);
        return ResponseEntity.ok(createdTeamStats);
    }

    @PutMapping("/team-stats/{teamId}")
    public ResponseEntity<TeamStats> updateTeamStats(@PathVariable Integer teamId, @RequestBody TeamStats teamStats) {
        TeamStats existingTeamStats = teamService.getTeamStatsById(teamId);
        if (existingTeamStats != null) {
            teamStats.setTeamId(teamId);
            TeamStats updatedTeamStats = teamService.saveTeamStats(teamStats);
            return ResponseEntity.ok(updatedTeamStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/team-stats/{teamId}")
    public ResponseEntity<Void> deleteTeamStats(@PathVariable Integer teamId) {
        teamService.deleteTeamStats(teamId);
        return ResponseEntity.noContent().build();
    }

    // TeamEstimatedMetrics Endpoints

    @GetMapping("/team-estimated-metrics")
    public ResponseEntity<List<TeamEstimatedMetrics>> getAllTeamEstimatedMetrics() {
        List<TeamEstimatedMetrics> teamEstimatedMetricsList = teamService.getAllTeamEstimatedMetrics();
        return ResponseEntity.ok(teamEstimatedMetricsList);
    }

    @GetMapping("/team-estimated-metrics/{teamId}")
    public ResponseEntity<TeamEstimatedMetrics> getTeamEstimatedMetricsById(@PathVariable Integer teamId) {
        TeamEstimatedMetrics teamEstimatedMetrics = teamService.getTeamEstimatedMetricsById(teamId);
        if (teamEstimatedMetrics != null) {
            return ResponseEntity.ok(teamEstimatedMetrics);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/team-estimated-metrics")
    public ResponseEntity<TeamEstimatedMetrics> createTeamEstimatedMetrics(@RequestBody TeamEstimatedMetrics teamEstimatedMetrics) {
        TeamEstimatedMetrics createdTeamEstimatedMetrics = teamService.saveTeamEstimatedMetrics(teamEstimatedMetrics);
        return ResponseEntity.ok(createdTeamEstimatedMetrics);
    }

    @PutMapping("/team-estimated-metrics/{teamId}")
    public ResponseEntity<TeamEstimatedMetrics> updateTeamEstimatedMetrics(@PathVariable Integer teamId, @RequestBody TeamEstimatedMetrics teamEstimatedMetrics) {
        TeamEstimatedMetrics existingTeamEstimatedMetrics = teamService.getTeamEstimatedMetricsById(teamId);
        if (existingTeamEstimatedMetrics != null) {
            teamEstimatedMetrics.setTeamId(teamId); // Ensure the ID remains the same
            TeamEstimatedMetrics updatedTeamEstimatedMetrics = teamService.saveTeamEstimatedMetrics(teamEstimatedMetrics);
            return ResponseEntity.ok(updatedTeamEstimatedMetrics);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/team-estimated-metrics/{teamId}")
    public ResponseEntity<Void> deleteTeamEstimatedMetrics(@PathVariable Integer teamId) {
        teamService.deleteTeamEstimatedMetrics(teamId);
        return ResponseEntity.noContent().build();
    }

    // Standings Endpoints

    @GetMapping("/standings")
    public ResponseEntity<List<Standings>> getAllStandings() {
        List<Standings> standingsList = teamService.getAllStandings();
        return ResponseEntity.ok(standingsList);
    }

    @GetMapping("/standings/{teamId}")
    public ResponseEntity<Standings> getStandingsById(@PathVariable Integer teamId) {
        Standings standings = teamService.getStandingsById(teamId);
        if (standings != null) {
            return ResponseEntity.ok(standings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/standings/league")
    public List<Standings> getLeagueRankings() {
        return teamService.getLeagueRankings();
    }

    @GetMapping("/standings/east")
    public List<Standings> getEasternConferenceStandings() {
        return teamService.getEasternConferenceStandings();
    }

    @GetMapping("/standings/west")
    public List<Standings> getWesternConferenceStandings() {
        return teamService.getWesternConferenceStandings();
    }


    @PostMapping("/standings")
    public ResponseEntity<Standings> createStandings(@RequestBody Standings standings) {
        Standings createdStandings = teamService.saveStandings(standings);
        return ResponseEntity.ok(createdStandings);
    }

    @PutMapping("/standings/{teamId}")
    public ResponseEntity<Standings> updateStandings(@PathVariable Integer teamId, @RequestBody Standings standings) {
        Standings existingStandings = teamService.getStandingsById(teamId);
        if (existingStandings != null) {
            standings.setTeamId(teamId); // Ensure the ID remains the same
            Standings updatedStandings = teamService.saveStandings(standings);
            return ResponseEntity.ok(updatedStandings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/standings/{teamId}")
    public ResponseEntity<Void> deleteStandings(@PathVariable Integer teamId) {
        teamService.deleteStandings(teamId);
        return ResponseEntity.noContent().build();
    }
}
