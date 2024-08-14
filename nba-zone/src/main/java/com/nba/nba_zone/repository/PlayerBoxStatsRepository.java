package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.PlayerBoxStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerBoxStatsRepository extends JpaRepository<PlayerBoxStats, Long> {

    List<PlayerBoxStats> findByGameId(String gameId);

    List<PlayerBoxStats> findByPlayerId(Integer playerId);

    List<PlayerBoxStats> findByTeamId(Integer teamId);

    List<PlayerBoxStats> findByPtsGreaterThan(Integer pts);

    List<PlayerBoxStats> findByReb(Integer reb);

    List<PlayerBoxStats> findByFgPctGreaterThan(Double fgPct);
}
