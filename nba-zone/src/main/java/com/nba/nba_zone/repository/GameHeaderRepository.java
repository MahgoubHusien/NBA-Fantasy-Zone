package com.nba.nba_zone.repository;

import com.nba.nba_zone.model.GameHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameHeaderRepository extends JpaRepository<GameHeader, Long> {

    List<GameHeader> findByGameDateEst(String gameDateEst);

    List<GameHeader> findByHomeTeamId(int homeTeamId);

    List<GameHeader> findByVisitorTeamId(int visitorTeamId);

    List<GameHeader> findByGameStatusText(String gameStatusText);
}
