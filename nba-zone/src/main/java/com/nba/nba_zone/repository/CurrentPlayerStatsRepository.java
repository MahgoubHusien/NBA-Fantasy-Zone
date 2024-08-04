package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.CurrentPlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrentPlayerStatsRepository extends JpaRepository<CurrentPlayerStats, Long> {
    Optional<CurrentPlayerStats> findByPlayerId(Integer playerId);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.pts DESC")
    List<CurrentPlayerStats> findTopTotalPoints(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.ast DESC")
    List<CurrentPlayerStats> findTopTotalAssists(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.reb DESC")
    List<CurrentPlayerStats> findTopTotalRebounds(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.stl DESC")
    List<CurrentPlayerStats> findTopTotalSteals(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.blk DESC")
    List<CurrentPlayerStats> findTopTotalBlocks(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.ftPct DESC")
    List<CurrentPlayerStats> findTopFreeThrowShooters(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.fg3Pct DESC")
    List<CurrentPlayerStats> findTopThreePointShooters(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.oreb DESC")
    List<CurrentPlayerStats> findTopOffensiveRebounders(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.dreb DESC")
    List<CurrentPlayerStats> findTopDefensiveRebounders(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.plusMinus DESC")
    List<CurrentPlayerStats> findMostEfficientPlayers(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.plusMinus DESC")
    List<CurrentPlayerStats> findMostEfficientScorers(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.pf DESC")
    List<CurrentPlayerStats> findMostFoulPronePlayers(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.ast DESC")
    List<CurrentPlayerStats> findBestAssistToTurnoverRatio(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.min DESC")
    List<CurrentPlayerStats> findMostMinutesPlayed(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.dd2 DESC")
    List<CurrentPlayerStats> findMostDoubleDoubles(Pageable pageable);

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.td3 DESC")
    List<CurrentPlayerStats> findMostTripleDoubles(Pageable pageable);}
