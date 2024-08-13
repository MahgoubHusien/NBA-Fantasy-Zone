package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.LineScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineScoreRepository extends JpaRepository<LineScore, Long> {


    List<LineScore> findByGameId(String gameId);

    List<LineScore> findByTeamId(int teamId);

    List<LineScore> findByTeamAbbreviation(String teamAbbreviation);

    LineScore findByGameIdAndTeamId(String gameId, int teamId);
}
