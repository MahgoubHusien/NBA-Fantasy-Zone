package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.PlayerBoxStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerBoxStatsRepository extends JpaRepository<PlayerBoxStats, Long> {

    List<PlayerBoxStats> findByGameId(String gameId);

    List<PlayerBoxStats> findByPlayerId(int playerId);

    List<PlayerBoxStats> findByTeamId(int teamId);

    List<PlayerBoxStats> findByPtsGreaterThan(int pts);

    List<PlayerBoxStats> findByReb(int reb);

    List<PlayerBoxStats> findByFgPctGreaterThan(double fgPct);
}
