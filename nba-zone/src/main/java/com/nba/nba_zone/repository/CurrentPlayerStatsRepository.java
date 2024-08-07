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
    List<CurrentPlayerStats> findTop5ByGpRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.w DESC")
    List<CurrentPlayerStats> findTop5ByWRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.l DESC")
    List<CurrentPlayerStats> findTop5ByLRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.wPct DESC")
    List<CurrentPlayerStats> findTop5ByWPctRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.min DESC")
    List<CurrentPlayerStats> findTop5ByMinRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fgm DESC")
    List<CurrentPlayerStats> findTop5ByFgmRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fga DESC")
    List<CurrentPlayerStats> findTop5ByFgaRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fgPct DESC")
    List<CurrentPlayerStats> findTop5ByFgPctRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fg3m DESC")
    List<CurrentPlayerStats> findTop5ByFg3mRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fg3a DESC")
    List<CurrentPlayerStats> findTop5ByFg3aRank();

    @Query("SELECT p FROM CurrentPlayerStats p WHERE p.fg3a > 0 ORDER BY (p.fg3m / p.fg3a) DESC")
    List<CurrentPlayerStats> findTop5ByFg3PctRank(Pageable pageable);

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.ftm DESC")
    List<CurrentPlayerStats> findTop5ByFtmRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.fta DESC")
    List<CurrentPlayerStats> findTop5ByFtaRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.ftPct DESC")
    List<CurrentPlayerStats> findTop5ByFtPctRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.oreb DESC")
    List<CurrentPlayerStats> findTop5ByOrebRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.dreb DESC")
    List<CurrentPlayerStats> findTop5ByDrebRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.reb DESC")
    List<CurrentPlayerStats> findTop5ByRebRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.ast DESC")
    List<CurrentPlayerStats> findTop5ByAstRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.tov DESC")
    List<CurrentPlayerStats> findTop5ByTovRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.stl DESC")
    List<CurrentPlayerStats> findTop5ByStlRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.blk DESC")
    List<CurrentPlayerStats> findTop5ByBlkRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.blka DESC")
    List<CurrentPlayerStats> findTop5ByBlkaRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.pf DESC")
    List<CurrentPlayerStats> findTop5ByPfRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.pfd DESC")
    List<CurrentPlayerStats> findTop5ByPfdRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.pts DESC")
    List<CurrentPlayerStats> findTop5ByPtsRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.plusMinus DESC")
    List<CurrentPlayerStats> findTop5ByPlusMinusRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.nbaFantasyPts DESC")
    List<CurrentPlayerStats> findTop5ByNbaFantasyPtsRank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.dd2 DESC")
    List<CurrentPlayerStats> findTop5ByDd2Rank();

    @Query("SELECT c FROM CurrentPlayerStats c ORDER BY c.td3 DESC")
    List<CurrentPlayerStats> findTop5ByTd3Rank();
}

