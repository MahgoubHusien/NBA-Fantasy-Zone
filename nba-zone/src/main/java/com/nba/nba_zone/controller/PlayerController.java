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

    // Sort methods returning CommonPlayerInfo
    @GetMapping("/top-total-points")
    public List<CommonPlayerInfo> getTopTotalPoints() {
        return playerService.getTopTotalPoints();
    }

    @GetMapping("/top-total-assists")
    public List<CommonPlayerInfo> getTopTotalAssists() {
        return playerService.getTopTotalAssists();
    }

    @GetMapping("/top-total-rebounds")
    public List<CommonPlayerInfo> getTopTotalRebounds() {
        return playerService.getTopTotalRebounds();
    }

    @GetMapping("/top-total-steals")
    public List<CommonPlayerInfo> getTopTotalSteals() {
        return playerService.getTopTotalSteals();
    }

    @GetMapping("/top-total-blocks")
    public List<CommonPlayerInfo> getTopTotalBlocks() {
        return playerService.getTopTotalBlocks();
    }

    @GetMapping("/top-free-throw-shooters")
    public List<CommonPlayerInfo> getTopFreeThrowShooters() {
        return playerService.getTopFreeThrowShooters();
    }

    @GetMapping("/top-three-point-shooters")
    public List<CommonPlayerInfo> getTopThreePointShooters() {
        return playerService.getTopThreePointShooters();
    }

    @GetMapping("/top-offensive-rebounders")
    public List<CommonPlayerInfo> getTopOffensiveRebounders() {
        return playerService.getTopOffensiveRebounders();
    }

    @GetMapping("/top-defensive-rebounders")
    public List<CommonPlayerInfo> getTopDefensiveRebounders() {
        return playerService.getTopDefensiveRebounders();
    }

    @GetMapping("/most-efficient-players")
    public List<CommonPlayerInfo> getMostEfficientPlayers() {
        return playerService.getMostEfficientPlayers();
    }

    @GetMapping("/most-efficient-scorers")
    public List<CommonPlayerInfo> getMostEfficientScorers() {
        return playerService.getMostEfficientScorers();
    }

    @GetMapping("/most-foul-prone-players")
    public List<CommonPlayerInfo> getMostFoulPronePlayers() {
        return playerService.getMostFoulPronePlayers();
    }

    @GetMapping("/most-minutes-played")
    public List<CommonPlayerInfo> getMostMinutesPlayed() {
        return playerService.getMostMinutesPlayed();
    }

    @GetMapping("/most-double-doubles")
    public List<CommonPlayerInfo> getMostDoubleDoubles() {
        return playerService.getMostDoubleDoubles();
    }

    @GetMapping("/most-triple-doubles")
    public List<CommonPlayerInfo> getMostTripleDoubles() {
        return playerService.getMostTripleDoubles();
    }

    @GetMapping("/top-ppg")
    public List<CommonPlayerInfo> getTopPpg() {
        return playerService.getTopPpg();
    }

    @GetMapping("/top-apg")
    public List<CommonPlayerInfo> getTopApg() {
        return playerService.getTopApg();
    }

    @GetMapping("/top-rpg")
    public List<CommonPlayerInfo> getTopRpg() {
        return playerService.getTopRpg();
    }

    // New endpoint for top fantasy points
    @GetMapping("/top-fantasy-points")
    public List<CommonPlayerInfo> getTopFantasyPoints() {
        return playerService.getTopFantasyPoints();
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
