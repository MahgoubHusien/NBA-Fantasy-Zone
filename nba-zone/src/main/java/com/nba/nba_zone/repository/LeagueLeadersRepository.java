package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.LeagueLeaders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueLeadersRepository extends JpaRepository<LeagueLeaders, Long> {

    // Method to retrieve ranks of players
    List<LeagueLeaders> findByOrderByRankAsc();

    // Optional: If you want to retrieve the rank of a specific player by their ID
    LeagueLeaders findByPlayerId(Integer playerId);

    // Optional: If you want to retrieve a player's rank within a specific team
    List<LeagueLeaders> findByTeamAbbreviationOrderByRankAsc(String teamAbbreviation);
}
