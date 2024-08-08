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
    List<CurrentPlayerStats> findMostTripleDoubles(Pageable pageable);



    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.gp DESC")
    List<CurrentPlayerStats> findTopGpRank();

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.w DESC")
    List<CurrentPlayerStats> findTopWinsRank();

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.l DESC")
    List<CurrentPlayerStats> findTopLossesRank();

    @Query("SELECT p FROM CurrentPlayerStats p WHERE p.gp > 0 ORDER BY (p.w / p.gp) DESC")
    List<CurrentPlayerStats> findTopWinPctRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.min DESC")
    List<CurrentPlayerStats> findTopMinRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fgm DESC")
    List<CurrentPlayerStats> findTopFgmRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fga DESC")
    List<CurrentPlayerStats> findTopFgaRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fgPct DESC")
    List<CurrentPlayerStats> findTopFgPctRank();

    @Query("SELECT p FROM CurrentPlayerStats p WHERE p.gp > 0 ORDER BY p.wPct DESC")
    List<CurrentPlayerStats> findTopWPct();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fg3m DESC")
    List<CurrentPlayerStats> findTopFg3mRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fg3a DESC")
    List<CurrentPlayerStats> findTopFg3aRank();

    @Query("SELECT p FROM CurrentPlayerStats p WHERE p.fg3a > 0 ORDER BY (p.fg3m / p.fg3a) DESC")
    List<CurrentPlayerStats> findTopFg3PctRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.ftm DESC")
    List<CurrentPlayerStats> findTopFtmRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fta DESC")
    List<CurrentPlayerStats> findTopFtaRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.ftPct DESC")
    List<CurrentPlayerStats> findTopFtPctRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.oreb DESC")
    List<CurrentPlayerStats> findTopOrebRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.dreb DESC")
    List<CurrentPlayerStats> findTopDrebRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.reb DESC")
    List<CurrentPlayerStats> findTopRebRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.ast DESC")
    List<CurrentPlayerStats> findTopAstRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.tov DESC")
    List<CurrentPlayerStats> findTopTovRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.stl DESC")
    List<CurrentPlayerStats> findTopStlRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.blk DESC")
    List<CurrentPlayerStats> findTopBlkRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.blka DESC")
    List<CurrentPlayerStats> findTopBlkaRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.pf DESC")
    List<CurrentPlayerStats> findTopPfRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.pfd DESC")
    List<CurrentPlayerStats> findTopPfdRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.pts DESC")
    List<CurrentPlayerStats> findTopPtsRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.plusMinus DESC")
    List<CurrentPlayerStats> findTopPlusMinusRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.nbaFantasyPts DESC")
    List<CurrentPlayerStats> findTopNbaFantasyPtsRank();

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.dd2 DESC")
    List<CurrentPlayerStats> findTopDd2Rank();

    @Query("SELECT p FROM CurrentPlayerStats p ORDER BY p.td3 DESC")
    List<CurrentPlayerStats> findTopTd3Rank();
}

