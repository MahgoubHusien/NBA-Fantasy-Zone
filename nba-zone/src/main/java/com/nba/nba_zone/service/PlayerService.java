package com.nba.nba_zone.service;

import com.nba.nba_zone.model.CommonPlayerInfo;
import com.nba.nba_zone.model.CurrentPlayerStats;
import com.nba.nba_zone.model.PlayerFantasyStats;
import com.nba.nba_zone.repository.CommonPlayerInfoRepository;
import com.nba.nba_zone.repository.CurrentPlayerStatsRepository;
import com.nba.nba_zone.repository.PlayerFantasyStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    public CommonPlayerInfo getCommonPlayerInfoById(Long id) {
        return commonPlayerInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    // Current Player Stats Methods
    public List<CurrentPlayerStats> getAllCurrentPlayerStats() {
        return currentPlayerStatsRepository.findAll();
    }

    public CurrentPlayerStats getCurrentPlayerStatsById(Long id) {
        return currentPlayerStatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    // Method to fetch and map sorted CurrentPlayerStats to CommonPlayerInfo
    private List<CommonPlayerInfo> mapSortedStatsToCommonInfo(List<CurrentPlayerStats> sortedStats) {
        return sortedStats.stream()
                .map(stats -> commonPlayerInfoRepository.findByPlayerId(stats.getPlayerId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Sort Methods
    public List<CommonPlayerInfo> getTopTotalPoints() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "pts"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopTotalAssists() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "ast"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopTotalRebounds() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "reb"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopTotalSteals() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "stl"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopTotalBlocks() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "blk"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopFreeThrowShooters() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "ftPct"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopThreePointShooters() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "fg3Pct"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopOffensiveRebounders() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "oreb"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopDefensiveRebounders() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "dreb"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getMostEfficientPlayers() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "plusMinus"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getMostEfficientScorers() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "fgPct"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getMostFoulPronePlayers() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "pf"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getMostMinutesPlayed() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "min"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getMostDoubleDoubles() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "dd2"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getMostTripleDoubles() {
        List<CurrentPlayerStats> sortedStats = currentPlayerStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "td3"));
        return mapSortedStatsToCommonInfo(sortedStats);
    }

    public List<CommonPlayerInfo> getTopPpg() {
        return commonPlayerInfoRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CommonPlayerInfo::getPpg).reversed())
                .collect(Collectors.toList());
    }

    public List<CommonPlayerInfo> getTopApg() {
        return commonPlayerInfoRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CommonPlayerInfo::getApg).reversed())
                .collect(Collectors.toList());
    }

    public List<CommonPlayerInfo> getTopRpg() {
        return commonPlayerInfoRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(CommonPlayerInfo::getRpg).reversed())
                .collect(Collectors.toList());
    }

    public List<CommonPlayerInfo> getTopFantasyPoints() {
        List<PlayerFantasyStats> sortedStats = playerFantasyStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "nbaFantasyPtsSeason"));
        return sortedStats.stream()
                .map(stats -> commonPlayerInfoRepository.findByPlayerId(stats.getPlayerId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<CurrentPlayerStats> getTopGpRank() {
        return currentPlayerStatsRepository.findTop5ByGpRank();
    }

    public List<CurrentPlayerStats> getTopWRank() {
        return currentPlayerStatsRepository.findTop5ByWRank();
    }

    public List<CurrentPlayerStats> getTopLRank() {
        return currentPlayerStatsRepository.findTop5ByLRank();
    }

    public List<CurrentPlayerStats> getTopWPctRank() {
        return currentPlayerStatsRepository.findTop5ByWPctRank();
    }

    public List<CurrentPlayerStats> getTopMinRank() {
        return currentPlayerStatsRepository.findTop5ByMinRank();
    }

    public List<CurrentPlayerStats> getTopFgmRank() {
        return currentPlayerStatsRepository.findTop5ByFgmRank();
    }

    public List<CurrentPlayerStats> getTopFgaRank() {
        return currentPlayerStatsRepository.findTop5ByFgaRank();
    }

    public List<CurrentPlayerStats> getTopFgPctRank() {
        return currentPlayerStatsRepository.findTop5ByFgPctRank();
    }

    public List<CurrentPlayerStats> getTopFg3mRank() {
        return currentPlayerStatsRepository.findTop5ByFg3mRank();
    }

    public List<CurrentPlayerStats> getTopFg3aRank() {
        return currentPlayerStatsRepository.findTop5ByFg3aRank();
    }

    public List<CurrentPlayerStats> getTopFg3PctRank() {
        return currentPlayerStatsRepository.findTop5ByFg3PctRank(PageRequest.of(0, 5));
    }

    public List<CurrentPlayerStats> getTopFtmRank() {
        return currentPlayerStatsRepository.findTop5ByFtmRank();
    }

    public List<CurrentPlayerStats> getTopFtaRank() {
        return currentPlayerStatsRepository.findTop5ByFtaRank();
    }

    public List<CurrentPlayerStats> getTopFtPctRank() {
        return currentPlayerStatsRepository.findTop5ByFtPctRank();
    }

    public List<CurrentPlayerStats> getTopOrebRank() {
        return currentPlayerStatsRepository.findTop5ByOrebRank();
    }

    public List<CurrentPlayerStats> getTopDrebRank() {
        return currentPlayerStatsRepository.findTop5ByDrebRank();
    }

    public List<CurrentPlayerStats> getTopRebRank() {
        return currentPlayerStatsRepository.findTop5ByRebRank();
    }

    public List<CurrentPlayerStats> getTopAstRank() {
        return currentPlayerStatsRepository.findTop5ByAstRank();
    }

    public List<CurrentPlayerStats> getTopTovRank() {
        return currentPlayerStatsRepository.findTop5ByTovRank();
    }

    public List<CurrentPlayerStats> getTopStlRank() {
        return currentPlayerStatsRepository.findTop5ByStlRank();
    }

    public List<CurrentPlayerStats> getTopBlkRank() {
        return currentPlayerStatsRepository.findTop5ByBlkRank();
    }

    public List<CurrentPlayerStats> getTopBlkaRank() {
        return currentPlayerStatsRepository.findTop5ByBlkaRank();
    }

    public List<CurrentPlayerStats> getTopPfRank() {
        return currentPlayerStatsRepository.findTop5ByPfRank();
    }

    public List<CurrentPlayerStats> getTopPfdRank() {
        return currentPlayerStatsRepository.findTop5ByPfdRank();
    }

    public List<CurrentPlayerStats> getTopPtsRank() {
        return currentPlayerStatsRepository.findTop5ByPtsRank();
    }

    public List<CurrentPlayerStats> getTopPlusMinusRank() {
        return currentPlayerStatsRepository.findTop5ByPlusMinusRank();
    }

    public List<CurrentPlayerStats> getTopNbaFantasyPtsRank() {
        return currentPlayerStatsRepository.findTop5ByNbaFantasyPtsRank();
    }

    public List<CurrentPlayerStats> getTopDd2Rank() {
        return currentPlayerStatsRepository.findTop5ByDd2Rank();
    }

    public List<CurrentPlayerStats> getTopTd3Rank() {
        return currentPlayerStatsRepository.findTop5ByTd3Rank();
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

    public PlayerFantasyStats getPlayerFantasyStatsById(Long id) {
        return playerFantasyStatsRepository.findById(id)
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
