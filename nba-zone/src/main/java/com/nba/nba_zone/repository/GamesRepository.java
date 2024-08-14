package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {

    List<Games> findByGameId(String gameId);

    List<Games> findBySeasonId(Integer seasonId);

    List<Games> findByTeamId(Integer teamId);

    List<Games> findByTeamAbbreviation(String teamAbbreviation);

    List<Games> findByWl(String wl);

    List<Games> findByPtsGreaterThan(Integer pts);
}
