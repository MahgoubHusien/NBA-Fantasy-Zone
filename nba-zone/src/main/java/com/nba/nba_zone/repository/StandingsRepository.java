package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.Standings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface StandingsRepository extends JpaRepository<Standings, Integer> {

    List<Standings> findByTeamName(String teamName);

    List<Standings> findByConference(String conference);

    List<Standings> findAllByOrderByWinPctDesc();

    List<Standings> findByClinchedPlayoffBirth(Integer clinchedPlayoffBirth);

    @Query("SELECT s FROM Standings s WHERE s.conference = :conference ORDER BY s.playoffRank ASC")
    List<Standings> findByConferenceOrderByWinPctDesc(@Param("conference") String conference);

    @Query("SELECT s FROM Standings s ORDER BY s.winPct DESC, s.teamName ASC")
    List<Standings> findAllOrderByWinPctDescAndTeamNameAsc();
}
