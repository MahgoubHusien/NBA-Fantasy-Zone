package com.nba.nba_zone.controller;

import com.nba.nba_zone.model.CommonPlayerInfo;
import com.nba.nba_zone.model.CurrentPlayerStats;
import com.nba.nba_zone.model.PlayerFantasyStats;
import com.nba.nba_zone.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Retrieve all common player info
    @GetMapping("/commonPlayerInfo")
    public List<CommonPlayerInfo> getAllCommonPlayerInfo() {
        return playerService.getAllCommonPlayerInfo();
    }

    // Retrieve common player info by player ID
    @GetMapping("/commonPlayerInfo/{id}")
    public ResponseEntity<CommonPlayerInfo> getCommonPlayerInfoById(@PathVariable Long id) {
        CommonPlayerInfo playerInfo = playerService.getCommonPlayerInfoById(id);
        return new ResponseEntity<>(playerInfo, HttpStatus.OK);
    }

    // Retrieve current player stats by player ID
    @GetMapping("/currentPlayerStats/{id}")
    public ResponseEntity<CurrentPlayerStats> getCurrentPlayerStatsById(@PathVariable Long id) {
        CurrentPlayerStats playerStats = playerService.getCurrentPlayerStatsById(id);
        return new ResponseEntity<>(playerStats, HttpStatus.OK);
    }

    // Retrieve player fantasy stats by player ID
    @GetMapping("/playerFantasyStats/{id}")
    public ResponseEntity<PlayerFantasyStats> getPlayerFantasyStatsById(@PathVariable Long id) {
        PlayerFantasyStats playerFantasyStats = playerService.getPlayerFantasyStatsById(id);
        return new ResponseEntity<>(playerFantasyStats, HttpStatus.OK);
    }

    // Retrieve top scorers
    @GetMapping("/top-scorers")
    public List<CommonPlayerInfo> getTopScorers(@RequestParam int limit) {
        return playerService.getTopScorers(limit);
    }

    // Retrieve top total points
    @GetMapping("/top-total-points")
    public List<CurrentPlayerStats> getTopTotalPoints(@RequestParam int limit) {
        return playerService.getTopTotalPoints(limit);
    }

    // Retrieve top total assists
    @GetMapping("/top-total-assists")
    public List<CurrentPlayerStats> getTopTotalAssists(@RequestParam int limit) {
        return playerService.getTopTotalAssists(limit);
    }

    // Retrieve top total rebounds
    @GetMapping("/top-total-rebounds")
    public List<CurrentPlayerStats> getTopTotalRebounds(@RequestParam int limit) {
        return playerService.getTopTotalRebounds(limit);
    }

    // Retrieve top total steals
    @GetMapping("/top-total-steals")
    public List<CurrentPlayerStats> getTopTotalSteals(@RequestParam int limit) {
        return playerService.getTopTotalSteals(limit);
    }

    // Retrieve top total blocks
    @GetMapping("/top-total-blocks")
    public List<CurrentPlayerStats> getTopTotalBlocks(@RequestParam int limit) {
        return playerService.getTopTotalBlocks(limit);
    }

    // Retrieve top free throw shooters
    @GetMapping("/top-free-throw-shooters")
    public List<CurrentPlayerStats> getTopFreeThrowShooters(@RequestParam int limit) {
        return playerService.getTopFreeThrowShooters(limit);
    }

    // Retrieve top three-point shooters
    @GetMapping("/top-three-point-shooters")
    public List<CurrentPlayerStats> getTopThreePointShooters(@RequestParam int limit) {
        return playerService.getTopThreePointShooters(limit);
    }

    // Retrieve top offensive rebounders
    @GetMapping("/top-offensive-rebounders")
    public List<CurrentPlayerStats> getTopOffensiveRebounders(@RequestParam int limit) {
        return playerService.getTopOffensiveRebounders(limit);
    }

    // Retrieve top defensive rebounders
    @GetMapping("/top-defensive-rebounders")
    public List<CurrentPlayerStats> getTopDefensiveRebounders(@RequestParam int limit) {
        return playerService.getTopDefensiveRebounders(limit);
    }

    // Retrieve most efficient players
    @GetMapping("/most-efficient-players")
    public List<CurrentPlayerStats> getMostEfficientPlayers(@RequestParam int limit) {
        return playerService.getMostEfficientPlayers(limit);
    }

    // Retrieve most efficient scorers
    @GetMapping("/most-efficient-scorers")
    public List<CurrentPlayerStats> getMostEfficientScorers(@RequestParam int limit) {
        return playerService.getMostEfficientScorers(limit);
    }

    // Retrieve most foul-prone players
    @GetMapping("/most-foul-prone-players")
    public List<CurrentPlayerStats> getMostFoulPronePlayers(@RequestParam int limit) {
        return playerService.getMostFoulPronePlayers(limit);
    }

    // Retrieve best assist-to-turnover ratio
    @GetMapping("/best-assist-to-turnover-ratio")
    public List<CurrentPlayerStats> getBestAssistToTurnoverRatio(@RequestParam int limit) {
        return playerService.getBestAssistToTurnoverRatio(limit);
    }

    // Retrieve most minutes played
    @GetMapping("/most-minutes-played")
    public List<CurrentPlayerStats> getMostMinutesPlayed(@RequestParam int limit) {
        return playerService.getMostMinutesPlayed(limit);
    }

    // Retrieve most double-doubles
    @GetMapping("/most-double-doubles")
    public List<CurrentPlayerStats> getMostDoubleDoubles(@RequestParam int limit) {
        return playerService.getMostDoubleDoubles(limit);
    }

    // Retrieve most triple-doubles
    @GetMapping("/most-triple-doubles")
    public List<CurrentPlayerStats> getMostTripleDoubles(@RequestParam int limit) {
        return playerService.getMostTripleDoubles(limit);
    }

    // Search players by name
    @GetMapping("/search-players")
    public List<CommonPlayerInfo> searchPlayersByName(@RequestParam String query) {
        return playerService.searchPlayersByName(query);
    }

    // Retrieve players by team abbreviation
    @GetMapping("/team")
    public List<CommonPlayerInfo> findPlayersByTeamAbbreviation(@RequestParam String teamAbbreviation) {
        return playerService.findPlayersByTeamAbbreviation(teamAbbreviation);
    }

    // Retrieve players by position
    @GetMapping("/position")
    public List<CommonPlayerInfo> findPlayersByPosition(@RequestParam String position) {
        return playerService.findPlayersByPosition(position);
    }

    // Retrieve players by team and position
    @GetMapping("/team-position")
    public List<CommonPlayerInfo> findPlayersByTeamAndPosition(@RequestParam String teamAbbreviation, @RequestParam String position) {
        return playerService.findPlayersByTeamAndPosition(teamAbbreviation, position);
    }
    // Retrieve players with fantasy points greater than a specified value
    @GetMapping("/fantasy-points-greater-than")
    public ResponseEntity<List<PlayerFantasyStats>> findByNbaFantasyPtsSeasonGreaterThan(@RequestParam Double nbaFantasyPtsSeason) {
        List<PlayerFantasyStats> players = playerService.findByNbaFantasyPtsSeasonGreaterThan(nbaFantasyPtsSeason);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Retrieve the player with the highest fantasy points
    @GetMapping("/top-fantasy-player")
    public ResponseEntity<PlayerFantasyStats> findTopByOrderByNbaFantasyPtsSeasonDesc() {
        PlayerFantasyStats player = playerService.findTopByOrderByNbaFantasyPtsSeasonDesc();
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    // Add a new player
    @PostMapping("/add")
    public ResponseEntity<CommonPlayerInfo> addPlayer(@RequestBody CommonPlayerInfo player) {
        CommonPlayerInfo createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    // Update an existing player
    @PutMapping("/update/{id}")
    public ResponseEntity<CommonPlayerInfo> updatePlayer(@PathVariable Long id, @RequestBody CommonPlayerInfo player) {
        CommonPlayerInfo updatedPlayer = playerService.updatePlayer(id, player);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    // Delete a player
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
