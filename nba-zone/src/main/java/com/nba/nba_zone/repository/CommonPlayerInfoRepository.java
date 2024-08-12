package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.CommonPlayerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommonPlayerInfoRepository extends JpaRepository<CommonPlayerInfo, Long> {

    Optional<CommonPlayerInfo> findByPlayerId(Integer playerId);
    // Method to find players by team abbreviation
    List<CommonPlayerInfo> findByTeamAbbreviation(String teamAbbreviation);

    // Method to find players by position
    List<CommonPlayerInfo> findByPosition(String position);

    // Method to search players by name
    List<CommonPlayerInfo> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    @Query("SELECT p FROM CommonPlayerInfo p WHERE p.teamId = :teamId")
    List<CommonPlayerInfo> findByTeamId(@Param("teamId") Integer teamId);
}
