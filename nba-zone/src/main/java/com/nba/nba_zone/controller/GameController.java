package com.nba.nba_zone.controller;

import com.nba.nba_zone.model.GameHeader;
import com.nba.nba_zone.model.LineScore;
import com.nba.nba_zone.model.Games;
import com.nba.nba_zone.model.PlayerBoxStats;
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

    @GetMapping("/headers/{id}")
    public ResponseEntity<GameHeader> getGameHeaderById(@PathVariable Long id) {
        Optional<GameHeader> gameHeader = gameService.getGameHeaderById(id);
        return gameHeader.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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

    @GetMapping("/linescores/{id}")
    public ResponseEntity<LineScore> getLineScoreById(@PathVariable Long id) {
        Optional<LineScore> lineScore = gameService.getLineScoreById(id);
        return lineScore.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/linescores/game/{gameId}")
    public ResponseEntity<List<LineScore>> getLineScoresByGameId(@PathVariable String gameId) {
        return new ResponseEntity<>(gameService.getLineScoresByGameId(gameId), HttpStatus.OK);
    }

    @GetMapping("/linescores/team/{teamId}")
    public ResponseEntity<List<LineScore>> getLineScoresByTeamId(@PathVariable int teamId) {
        return new ResponseEntity<>(gameService.getLineScoresByTeamId(teamId), HttpStatus.OK);
    }

    @GetMapping("/linescores/game/{gameId}/team/{teamId}")
    public ResponseEntity<LineScore> getLineScoreByGameIdAndTeamId(@PathVariable String gameId, @PathVariable int teamId) {
        LineScore lineScore = gameService.getLineScoreByGameIdAndTeamId(gameId, teamId);
        return new ResponseEntity<>(lineScore, HttpStatus.OK);
    }

    // Games Endpoints

    @GetMapping
    public ResponseEntity<List<Games>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> getGameById(@PathVariable Long id) {
        Optional<Games> game = gameService.getGameById(id);
        return game.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/season/{seasonId}")
    public ResponseEntity<List<Games>> getGamesBySeason(@PathVariable int seasonId) {
        return new ResponseEntity<>(gameService.getGamesBySeason(seasonId), HttpStatus.OK);
    }

    @GetMapping("/home/{homeTeamId}")
    public ResponseEntity<List<Games>> getGamesByHomeTeam(@PathVariable int homeTeamId) {
        return new ResponseEntity<>(gameService.getGamesByHomeTeam(homeTeamId), HttpStatus.OK);
    }

    @GetMapping("/visitor/{visitorTeamId}")
    public ResponseEntity<List<Games>> getGamesByVisitorTeam(@PathVariable int visitorTeamId) {
        return new ResponseEntity<>(gameService.getGamesByVisitorTeam(visitorTeamId), HttpStatus.OK);
    }

    @GetMapping("/wl/{wl}")
    public ResponseEntity<List<Games>> getGamesByWinLoss(@PathVariable String wl) {
        return new ResponseEntity<>(gameService.getGamesByWinLoss(wl), HttpStatus.OK);
    }

    // PlayerBoxStats Endpoints

    @GetMapping("/playerboxstats")
    public ResponseEntity<List<PlayerBoxStats>> getAllPlayerBoxStats() {
        return new ResponseEntity<>(gameService.getAllPlayerBoxStats(), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/{id}")
    public ResponseEntity<PlayerBoxStats> getPlayerBoxStatsById(@PathVariable Long id) {
        Optional<PlayerBoxStats> playerBoxStats = gameService.getPlayerBoxStatsById(id);
        return playerBoxStats.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/playerboxstats/game/{gameId}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByGameId(@PathVariable String gameId) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByGameId(gameId), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/player/{playerId}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByPlayerId(@PathVariable int playerId) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByPlayerId(playerId), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/team/{teamId}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByTeamId(@PathVariable int teamId) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByTeamId(teamId), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/points/{pts}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByPointsGreaterThan(@PathVariable int pts) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByPointsGreaterThan(pts), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/rebounds/{reb}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByRebounds(@PathVariable int reb) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByRebounds(reb), HttpStatus.OK);
    }

    @GetMapping("/playerboxstats/fgpct/{fgPct}")
    public ResponseEntity<List<PlayerBoxStats>> getPlayerBoxStatsByFieldGoalPercentageGreaterThan(@PathVariable double fgPct) {
        return new ResponseEntity<>(gameService.getPlayerBoxStatsByFieldGoalPercentageGreaterThan(fgPct), HttpStatus.OK);
    }
}
