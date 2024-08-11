package com.nba.nba_zone.repository;


import com.nba.nba_zone.model.TeamStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamStatsRepository extends JpaRepository<TeamStats, Integer> {

    // Custom query methods can be added here if needed

    List<TeamStats> findByTeamName(String teamName);

    List<TeamStats> findByWinPctGreaterThan(Double winPct);

    List<TeamStats> findAllByOrderByPointsDesc();
}