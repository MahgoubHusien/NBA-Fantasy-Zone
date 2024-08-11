package com.nba.nba_zone.repository;


import com.nba.nba_zone.model.TeamEstimatedMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamEstimatedMetricsRepository extends JpaRepository<TeamEstimatedMetrics, Integer> {

    // Custom query methods can be added here if needed

    List<TeamEstimatedMetrics> findByTeamName(String teamName);

    List<TeamEstimatedMetrics> findByEstimatedOffensiveRatingGreaterThan(Double estimatedOffensiveRating);

    List<TeamEstimatedMetrics> findAllByOrderByEstimatedNetRatingDesc();
}

