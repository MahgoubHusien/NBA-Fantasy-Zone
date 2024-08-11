package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.Standings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandingsRepository extends JpaRepository<Standings, Integer> {

    // Custom query methods can be added here if needed

    List<Standings> findByTeamName(String teamName);

    List<Standings> findByConference(String conference);

    List<Standings> findAllByOrderByWinPctDesc();

    List<Standings> findByClinchedPlayoffBirth(Integer clinchedPlayoffBirth);
}
