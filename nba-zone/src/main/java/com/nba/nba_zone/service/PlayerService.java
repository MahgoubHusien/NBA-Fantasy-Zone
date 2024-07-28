package com.nba.nba_zone.service;

import com.nba.nba_zone.model.CommonPlayerInfo;
import com.nba.nba_zone.model.CurrentPlayerStats;
import com.nba.nba_zone.model.PlayerFantasyStats;
import com.nba.nba_zone.repository.CommonPlayerInfoRepository;
import com.nba.nba_zone.repository.CurrentPlayerStatsRepository;
import com.nba.nba_zone.repository.PlayerFantasyStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final CommonPlayerInfoRepository commonPlayerInfoRepository;
    private final CurrentPlayerStatsRepository currentPlayerStatsRepository;
    private final PlayerFantasyStatsRepository playerFantasyStatsRepository;

    @Autowired
    public PlayerService(CommonPlayerInfoRepository commonPlayerInfoRepository, CurrentPlayerStatsRepository currentPlayerStatsRepository, PlayerFantasyStatsRepository playerFantasyStatsRepository) {
        this.commonPlayerInfoRepository = commonPlayerInfoRepository;
        this.currentPlayerStatsRepository = currentPlayerStatsRepository;
        this.playerFantasyStatsRepository = playerFantasyStatsRepository;
    }

    // Common Player Info Methods
    public List<CommonPlayerInfo> getAllCommonPlayerInfo() {
        return commonPlayerInfoRepository.findAll();
    }

    public List<CommonPlayerInfo> getTopScorers(int limit) {
        return commonPlayerInfoRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CommonPlayerInfo::getPpg).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopTotalPoints(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getPts).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopTotalAssists(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getAst).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopTotalRebounds(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getReb).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopTotalSteals(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getStl).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopTotalBlocks(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getBlk).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopFreeThrowShooters(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getFtPct).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopThreePointShooters(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getFg3Pct).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopOffensiveRebounders(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getOreb).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopDefensiveRebounders(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getDreb).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getMostEfficientPlayers(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getPlusMinus).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getMostEfficientScorers(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getFgPct).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getMostFoulPronePlayers(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getPf).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getBestAssistToTurnoverRatio(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .filter(player -> player.getTov() != 0) // Avoid division by zero
                .sorted((p1, p2) -> Double.compare(p2.getAst() / p2.getTov(), p1.getAst() / p1.getTov()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getMostMinutesPlayed(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CurrentPlayerStats::getMin).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getMostDoubleDoubles(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingInt(CurrentPlayerStats::getDd2Rank).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getMostTripleDoubles(int limit) {
        return currentPlayerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingInt(CurrentPlayerStats::getTd3Rank).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CommonPlayerInfo> searchPlayersByName(String name) {
        return commonPlayerInfoRepository.findAll().stream()
                .filter(player -> player.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
                        player.getLastName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<CommonPlayerInfo> findPlayersByTeamAbbreviation(String teamAbbreviation) {
        return commonPlayerInfoRepository.findByTeamAbbreviation(teamAbbreviation);
    }

    public List<CommonPlayerInfo> findPlayersByPosition(String position) {
        return commonPlayerInfoRepository.findByPosition(position);
    }

    public List<CommonPlayerInfo> findPlayersByTeamAndPosition(String teamAbbreviation, String position) {
        return commonPlayerInfoRepository.findAll().stream()
                .filter(player -> player.getTeamAbbreviation().equalsIgnoreCase(teamAbbreviation) &&
                        player.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
    }

    public CommonPlayerInfo getPlayerById(Long id) {
        return commonPlayerInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    @Transactional
    public CommonPlayerInfo addPlayer(CommonPlayerInfo player) {
        return commonPlayerInfoRepository.save(player);
    }

    @Transactional
    public CommonPlayerInfo updatePlayer(Long id, CommonPlayerInfo updatedPlayer) {
        return commonPlayerInfoRepository.findById(id)
                .map(player -> {
                    player.setFirstName(updatedPlayer.getFirstName());
                    player.setLastName(updatedPlayer.getLastName());
                    player.setTeamAbbreviation(updatedPlayer.getTeamAbbreviation());
                    player.setPpg(updatedPlayer.getPpg());
                    player.setApg(updatedPlayer.getApg());
                    player.setRpg(updatedPlayer.getRpg());
                    player.setSpg(updatedPlayer.getSpg());
                    player.setTopg(updatedPlayer.getTopg());
                    player.setBpg(updatedPlayer.getBpg());
                    player.setPfpg(updatedPlayer.getPfpg());
                    player.setWeight(updatedPlayer.getWeight());
                    player.setTeamName(updatedPlayer.getTeamName());
                    player.setJersey(updatedPlayer.getJersey());
                    player.setPosition(updatedPlayer.getPosition());
                    player.setHeight(updatedPlayer.getHeight());
                    return commonPlayerInfoRepository.save(player);
                }).orElseThrow(() -> new RuntimeException("Player not found"));
    }

    @Transactional
    public void deletePlayer(Long id) {
        if (commonPlayerInfoRepository.existsById(id)) {
            commonPlayerInfoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Player not found");
        }
    }

    // Player Fantasy Stats Methods
    public List<PlayerFantasyStats> findByNbaFantasyPtsSeasonGreaterThan(Double nbaFantasyPtsSeason) {
        return playerFantasyStatsRepository.findByNbaFantasyPtsSeasonGreaterThan(nbaFantasyPtsSeason);
    }

    public List<PlayerFantasyStats> getTopFantasyPlayers(int limit) {
        return playerFantasyStatsRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(PlayerFantasyStats::getNbaFantasyPtsSeason).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public PlayerFantasyStats findTopByOrderByNbaFantasyPtsSeasonDesc() {
        List<PlayerFantasyStats> topPlayers = playerFantasyStatsRepository.findTopByOrderByNbaFantasyPtsSeasonDesc();
        return topPlayers.isEmpty() ? null : topPlayers.get(0);
    }
}
