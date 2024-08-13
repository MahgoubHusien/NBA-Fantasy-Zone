package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {

    List<Games> findBySeasonId(int seasonId);

    List<Games> findByHomeTeamId(int homeTeamId);

    List<Games> findByVisitorTeamId(int visitorTeamId);

    List<Games> findByWl(String wl);

    List<Games> findByPtsGreaterThan(int pts);
}
