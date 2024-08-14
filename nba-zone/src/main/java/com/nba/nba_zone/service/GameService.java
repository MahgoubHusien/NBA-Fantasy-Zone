package com.nba.nba_zone.service;

import com.nba.nba_zone.model.GameHeader;
import com.nba.nba_zone.model.LineScore;
import com.nba.nba_zone.model.Games;
import com.nba.nba_zone.model.PlayerBoxStats;
import com.nba.nba_zone.repository.GameHeaderRepository;
import com.nba.nba_zone.repository.GamesRepository;
import com.nba.nba_zone.repository.LineScoreRepository;
import com.nba.nba_zone.repository.PlayerBoxStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameHeaderRepository gameHeaderRepository;
    private final LineScoreRepository lineScoreRepository;
    private final GamesRepository gamesRepository;
    private final PlayerBoxStatsRepository playerBoxStatsRepository;

    @Autowired
    public GameService(GameHeaderRepository gameHeaderRepository, LineScoreRepository lineScoreRepository,
                       GamesRepository gamesRepository, PlayerBoxStatsRepository playerBoxStatsRepository) {
        this.gameHeaderRepository = gameHeaderRepository;
        this.lineScoreRepository = lineScoreRepository;
        this.gamesRepository = gamesRepository;
        this.playerBoxStatsRepository = playerBoxStatsRepository;
    }

    // GameHeader Methods
    public List<GameHeader> getAllGameHeaders() {
        return gameHeaderRepository.findAll();
    }

    public List<GameHeader> getGameHeaderByGameId(String gameId) {
        return gameHeaderRepository.findByGameId(gameId);
    }

    public List<GameHeader> getGameHeadersByDate(String gameDateEst) {
        return gameHeaderRepository.findByGameDateEst(gameDateEst);
    }

    public List<GameHeader> getGameHeadersByHomeTeam(int homeTeamId) {
        return gameHeaderRepository.findByHomeTeamId(homeTeamId);
    }

    public List<GameHeader> getGameHeadersByVisitorTeam(int visitorTeamId) {
        return gameHeaderRepository.findByVisitorTeamId(visitorTeamId);
    }

    // LineScore Methods
    public List<LineScore> getAllLineScores() {
        return lineScoreRepository.findAll();
    }

    public Optional<LineScore> getLineScoreById(Long id) {
        return lineScoreRepository.findById(id);
    }

    public List<LineScore> getLineScoresByGameId(String gameId) {
        return lineScoreRepository.findByGameId(gameId);
    }

    public List<LineScore> getLineScoresByTeamId(int teamId) {
        return lineScoreRepository.findByTeamId(teamId);
    }

    public LineScore getLineScoreByGameIdAndTeamId(String gameId, int teamId) {
        return lineScoreRepository.findByGameIdAndTeamId(gameId, teamId);
    }

    public List<LineScore> getLineScoreByGameId(String gameId) {
        return lineScoreRepository.findByGameId(gameId);
    }

    // Games Methods
    public List<Games> getAllGames() {
        return gamesRepository.findAll();
    }

    public List<Games> getGameById(String gameId) {
        return gamesRepository.findByGameId(gameId);
    }

    public List<Games> getGamesBySeason(Integer seasonId) {
        return gamesRepository.findBySeasonId(seasonId);
    }

    public List<Games> getGamesByTeamId(Integer teamId) {
        return gamesRepository.findByTeamId(teamId);
    }

    public List<Games> getGamesByWinLoss(String wl) {
        return gamesRepository.findByWl(wl);
    }

    public List<Games> getGamesByPointsGreaterThan(Integer pts) {
        return gamesRepository.findByPtsGreaterThan(pts);
    }

    // PlayerBoxStats Methods
    public List<PlayerBoxStats> getAllPlayerBoxStats() {
        return playerBoxStatsRepository.findAll();
    }

    public Optional<PlayerBoxStats> getPlayerBoxStatsById(Long id) {
        return playerBoxStatsRepository.findById(id);
    }

    public List<PlayerBoxStats> getPlayerBoxStatsByGameId(String gameId) {
        return playerBoxStatsRepository.findByGameId(gameId);
    }

    public List<PlayerBoxStats> getPlayerBoxStatsByPlayerId(Integer playerId) {
        return playerBoxStatsRepository.findByPlayerId(playerId);
    }

    public List<PlayerBoxStats> getPlayerBoxStatsByTeamId(Integer teamId) {
        return playerBoxStatsRepository.findByTeamId(teamId);
    }

    public List<PlayerBoxStats> getPlayerBoxStatsByPointsGreaterThan(Integer pts) {
        return playerBoxStatsRepository.findByPtsGreaterThan(pts);
    }

    public List<PlayerBoxStats> getPlayerBoxStatsByRebounds(Integer reb) {
        return playerBoxStatsRepository.findByReb(reb);
    }

    public List<PlayerBoxStats> getPlayerBoxStatsByFieldGoalPercentageGreaterThan(Double fgPct) {
        return playerBoxStatsRepository.findByFgPctGreaterThan(fgPct);
    }

}
