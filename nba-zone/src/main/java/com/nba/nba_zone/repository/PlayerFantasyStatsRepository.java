package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.PlayerFantasyStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerFantasyStatsRepository extends JpaRepository<PlayerFantasyStats, Long> {

    Optional<PlayerFantasyStats> findByPlayerId(Integer playerId);

    // Method to find players by fantasy points per season greater than a specified value
    List<PlayerFantasyStats> findByNbaFantasyPtsSeasonGreaterThan(Double nbaFantasyPtsSeason);

    // Method to find the players with the highest fantasy points in the season
    @Query("SELECT p FROM PlayerFantasyStats p ORDER BY p.nbaFantasyPtsSeason DESC")
    List<PlayerFantasyStats> findTopByOrderByNbaFantasyPtsSeasonDesc();
}
