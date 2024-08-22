package com.nba.nba_zone.controller;

import com.nba.nba_zone.model.*;
import com.nba.nba_zone.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // GameHeader Endpoints

    @GetMapping("/headers")
    public ResponseEntity<List<GameHeader>> getAllGameHeaders() {
        return new ResponseEntity<>(gameService.getAllGameHeaders(), HttpStatus.OK);
    }

    @GetMapping("/headers/{gameId}")
    public ResponseEntity<List<GameHeader>> getGameHeaderByGameId(@PathVariable String gameId) {
        List<GameHeader> gameHeader = gameService.getGameHeaderByGameId(gameId);
        return new ResponseEntity<>(gameHeader, HttpStatus.OK);
    }

    @GetMapping("/headers/date/{date}")
    public ResponseEntity<List<GameHeader>> getGameHeadersByDate(@PathVariable String date) {
        return new ResponseEntity<>(gameService.getGameHeadersByDate(date), HttpStatus.OK);
    }

    @GetMapping("/headers/home/{homeTeamId}")
    public ResponseEntity<List<GameHeader>> getGameHeadersByHomeTeam(@PathVariable int homeTeamId) {
        return new ResponseEntity<>(gameService.getGameHeadersByHomeTeam(homeTeamId), HttpStatus.OK);
    }

    @GetMapping("/headers/visitor/{visitorTeamId}")
    public ResponseEntity<List<GameHeader>> getGameHeadersByVisitorTeam(@PathVariable int visitorTeamId) {
        return new ResponseEntity<>(gameService.getGameHeadersByVisitorTeam(visitorTeamId), HttpStatus.OK);
    }

    // LineScore Endpoints

    @GetMapping("/linescores")
    public ResponseEntity<List<LineScore>> getAllLineScores() {
        return new ResponseEntity<>(gameService.getAllLineScores(), HttpStatus.OK);
    }

//    @GetMapping("/linescores/{gameId}")
//    public ResponseEntity<List<LineScore>> getLineScoreByGameId(@PathVariable String gameId) {
//        List<LineScore> lineScore = gameService.getLineScoreByGameId(gameId);
//        return new ResponseEntity<>(lineScore, HttpStatus.OK);
//    }

    @GetMapping("/linescores/game/{gameId}")
    public ResponseEntity<List<LineScore>> getLineScoresByGameId(@PathVariable String gameId) {
        return new ResponseEntity<>(gameService.getLineScoresByGameId(gameId), HttpStatus.OK);
    }

    @GetMapping("/linescores/team/{teamId}")
    public ResponseEntity<List<LineScore>> getLineScoresByTeamId(@PathVariable int teamId) {
        return new ResponseEntity<>(gameService.getLineScoresByTeamId(teamId), HttpStatus.OK);
    }

//    @GetMapping("/linescores/game/{gameId}/team/{teamId}")
//    public ResponseEntity<LineScore> getLineScoreByGameIdAndTeamId(@PathVariable String gameId, @PathVariable int teamId) {
//        Optional<LineScore> lineScore = Optional.ofNullable(gameService.getLineScoreByGameIdAndTeamId(gameId, teamId));
//        return lineScore.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @GetMapping("/search-linescores")
    public List<LineScore> searchLineScoresByName(@RequestParam String query) {
        return gameService.searchLineScoresByName(query);
    }

    // Games Endpoints

    @GetMapping("/gamer")
    public ResponseEntity<List<Games>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/gamer/{gameId}")
    public ResponseEntity<List<Games>> getGameById(@PathVariable String gameId) {
        List<Games> game = gameService.getGameById(gameId);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/gamer/season/{seasonId}")
    public ResponseEntity<List<Games>> getGamesBySeason(@PathVariable Integer seasonId) {
        return new ResponseEntity<>(gameService.getGamesBySeason(seasonId), HttpStatus.OK);
    }

    @GetMapping("/gamer/team/{teamId}")
    public ResponseEntity<List<Games>> getGamesByTeamId(@PathVariable Integer teamId) {
        return new ResponseEntity<>(gameService.getGamesByTeamId(teamId), HttpStatus.OK);
    }

    @GetMapping("/gamer/wl/{wl}")
    public ResponseEntity<List<Games>> getGamesByWinLoss(@PathVariable String wl) {
        return new ResponseEntity<>(gameService.getGamesByWinLoss(wl), HttpStatus.OK);
    }

    @GetMapping("/gamer/points/{pts}")
    public ResponseEntity<List<Games>> getGamesByPointsGreaterThan(@PathVariable Integer pts) {
        return new ResponseEntity<>(gameService.getGamesByPointsGreaterThan(pts), HttpStatus.OK);
    }

    // PlayerBoxStats Endpoints

    @GetMapping("/playerboxstats")
    public ResponseEntity<List<PlayerBoxStats>> getAllPlayerBoxStats() {
        return new ResponseEntity<>(gameService.getAllPlayerBoxStats(), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/id/{id}")
    public ResponseEntity<PlayerBoxStats> getPlayerBoxStatsById(@PathVariable Long id) {
        Optional<PlayerBoxStats> playerBoxStats = gameService.getPlayerBoxStatsById(id);
        return playerBoxStats.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/playerboxstats/{gameId}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByGameId(@PathVariable String gameId) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByGameId(gameId), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/player/{playerId}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByPlayerId(@PathVariable Integer playerId) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByPlayerId(playerId), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/team/{teamId}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByTeamId(@PathVariable Integer teamId) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByTeamId(teamId), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/points/{pts}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByPointsGreaterThan(@PathVariable Integer pts) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByPointsGreaterThan(pts), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/rebounds/{reb}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByRebounds(@PathVariable Integer reb) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByRebounds(reb), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/fgpct/{fgPct}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByFieldGoalPercentageGreaterThan(@PathVariable Double fgPct) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByFieldGoalPercentageGreaterThan(fgPct), HttpStatus.OK);
    }
}
