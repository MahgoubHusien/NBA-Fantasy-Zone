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

    // Retrieve top ranked players for each category
    @GetMapping("/top-gp-rank")
    public List<CurrentPlayerStats> getTopGpRank() {
        return playerService.getTopGpRank();
    }

    @GetMapping("/top-w-rank")
    public List<CurrentPlayerStats> getTopWRank() {
        return playerService.getTopWRank();
    }

    @GetMapping("/top-l-rank")
    public List<CurrentPlayerStats> getTopLRank() {
        return playerService.getTopLRank();
    }

    @GetMapping("/top-wpct-rank")
    public List<CurrentPlayerStats> getTopWPctRank() {
        return playerService.getTopWPctRank();
    }

    @GetMapping("/top-min-rank")
    public List<CurrentPlayerStats> getTopMinRank() {
        return playerService.getTopMinRank();
    }

    @GetMapping("/top-fgm-rank")
    public List<CurrentPlayerStats> getTopFgmRank() {
        return playerService.getTopFgmRank();
    }

    @GetMapping("/top-fga-rank")
    public List<CurrentPlayerStats> getTopFgaRank() {
        return playerService.getTopFgaRank();
    }

    @GetMapping("/top-fgpct-rank")
    public List<CurrentPlayerStats> getTopFgPctRank() {
        return playerService.getTopFgPctRank();
    }

    @GetMapping("/top-fg3m-rank")
    public List<CurrentPlayerStats> getTopFg3mRank() {
        return playerService.getTopFg3mRank();
    }

    @GetMapping("/top-fg3a-rank")
    public List<CurrentPlayerStats> getTopFg3aRank() {
        return playerService.getTopFg3aRank();
    }

    @GetMapping("/top-fg3pct-rank")
    public List<CurrentPlayerStats> getTopFg3PctRank() {
        return playerService.getTopFg3PctRank();
    }

    @GetMapping("/top-ftm-rank")
    public List<CurrentPlayerStats> getTopFtmRank() {
        return playerService.getTopFtmRank();
    }

    @GetMapping("/top-fta-rank")
    public List<CurrentPlayerStats> getTopFtaRank() {
        return playerService.getTopFtaRank();
    }

    @GetMapping("/top-ftpct-rank")
    public List<CurrentPlayerStats> getTopFtPctRank() {
        return playerService.getTopFtPctRank();
    }

    @GetMapping("/top-oreb-rank")
    public List<CurrentPlayerStats> getTopOrebRank() {
        return playerService.getTopOrebRank();
    }

    @GetMapping("/top-dreb-rank")
    public List<CurrentPlayerStats> getTopDrebRank() {
        return playerService.getTopDrebRank();
    }

    @GetMapping("/top-reb-rank")
    public List<CurrentPlayerStats> getTopRebRank() {
        return playerService.getTopRebRank();
    }

    @GetMapping("/top-ast-rank")
    public List<CurrentPlayerStats> getTopAstRank() {
        return playerService.getTopAstRank();
    }

    @GetMapping("/top-tov-rank")
    public List<CurrentPlayerStats> getTopTovRank() {
        return playerService.getTopTovRank();
    }

    @GetMapping("/top-stl-rank")
    public List<CurrentPlayerStats> getTopStlRank() {
        return playerService.getTopStlRank();
    }

    @GetMapping("/top-blk-rank")
    public List<CurrentPlayerStats> getTopBlkRank() {
        return playerService.getTopBlkRank();
    }

    @GetMapping("/top-blka-rank")
    public List<CurrentPlayerStats> getTopBlkaRank() {
        return playerService.getTopBlkaRank();
    }

    @GetMapping("/top-pf-rank")
    public List<CurrentPlayerStats> getTopPfRank() {
        return playerService.getTopPfRank();
    }

    @GetMapping("/top-pfd-rank")
    public List<CurrentPlayerStats> getTopPfdRank() {
        return playerService.getTopPfdRank();
    }

    @GetMapping("/top-pts-rank")
    public List<CurrentPlayerStats> getTopPtsRank() {
        return playerService.getTopPtsRank();
    }

    @GetMapping("/top-plus-minus-rank")
    public List<CurrentPlayerStats> getTopPlusMinusRank() {
        return playerService.getTopPlusMinusRank();
    }

    @GetMapping("/top-nba-fantasy-points-rank")
    public List<CurrentPlayerStats> getTopNbaFantasyPtsRank() {
        return playerService.getTopNbaFantasyPtsRank();
    }

    @GetMapping("/top-dd2-rank")
    public List<CurrentPlayerStats> getTopDd2Rank() {
        return playerService.getTopDd2Rank();
    }

    @GetMapping("/top-td3-rank")
    public List<CurrentPlayerStats> getTopTd3Rank() {
        return playerService.getTopTd3Rank();
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
