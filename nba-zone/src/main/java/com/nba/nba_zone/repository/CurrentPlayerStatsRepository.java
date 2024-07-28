package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.CurrentPlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentPlayerStatsRepository extends JpaRepository<CurrentPlayerStats, Long> {

    // Custom query methods for additional statistics, if needed, can be added here.
}
