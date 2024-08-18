'use client';

import React, { useEffect, useState, useMemo } from "react";
import Link from "next/link";
import Image from "next/image";

interface GameHeader {
  gameId: string;
  gameDateEst: string;
  gameSequence: number;
  gameStatusId: number;
  gameStatusText: string;
  homeTeamId: number;
  visitorTeamId: number;
  season: string;
  livePeriod: number;
  livePcTime: string;
  natlTvBroadcasterAbbreviation: string;
  homeTvBroadcasterAbbreviation: string;
  awayTvBroadcasterAbbreviation: string;
  livePeriodTimeBcast: string;
  arenaName: string;
  whStatus: number;
  wnbaCommissionerFlag: string;
}

interface Games {
  game_id: string;
  season_id: number;
  game_date: string;
  team_id: number;
  team_name: string;
  team_abbreviation: string;
  matchup: string;
  wl: string;
  min: number;
  fgm: number;
  fga: number;
  fg_pct: number;
  fg3m: number;
  fg3a: number;
  fg3_pct: number;
  ftm: number;
  fta: number;
  ft_pct: number;
  oreb: number;
  dreb: number;
  reb: number;
  ast: number;
  stl: number;
  blk: number;
  tov: number;
  pf: number;
  pts: number;
  plus_minus: number;
  video_available: boolean;
  id: number;
  fg3pct: number;
}

interface LineScore {
  gameId: string;
  teamId: number;
  teamAbbreviation: string;
  teamName: string;
  ptsQtr1: number;
  ptsQtr2: number;
  ptsQtr3: number;
  ptsQtr4: number;
  pts: number;
}

interface TeamStats {
  teamId: number;
  teamName: string;
  teamAbbreviation: string;
  logoUrl: string;
  wins: number;
  losses: number;
}

const GamesTab: React.FC = () => {
  const [gameHeaders, setGameHeaders] = useState<GameHeader[]>([]);
  const [lineScores, setLineScores] = useState<LineScore[]>([]);
  const [teamStats, setTeamStats] = useState<TeamStats[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [gameHeaderResponse, lineScoreResponse, teamStatsResponse] = await Promise.all([
          fetch(`${process.env.NEXT_PUBLIC_API_URL}/games/headers`),
          fetch(`${process.env.NEXT_PUBLIC_API_URL}/games/linescores`),
          fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/team-stats`),
        ]);

        const [gameHeaderData, lineScoreData, teamStatsData] = await Promise.all([
          gameHeaderResponse.json(),
          lineScoreResponse.json(),
          teamStatsResponse.json(),
        ]);

        setGameHeaders(gameHeaderData);
        setLineScores(lineScoreData);
        setTeamStats(Array.isArray(teamStatsData) ? teamStatsData : []);
      } catch (error) {
        console.error('Error fetching data', error);
      }
    };

    fetchData();
  }, []);

  const getTeamLogo = useMemo(() => (teamId: number) => {
    const team = teamStats.find((team) => team.teamId === teamId);
    return team ? team.logoUrl : '';
  }, [teamStats]);

  const getTeamAbbreviation = useMemo(() => (teamId: number, gameId: string) => {
    const lineScore = lineScores.find((score) => score.teamId === teamId && score.gameId === gameId);
    return lineScore ? lineScore.teamAbbreviation : '';
  }, [lineScores]);

  const getTeamRecord = useMemo(() => (teamId: number) => {
    const team = teamStats.find((team) => team.teamId === teamId);
    return team ? `${team.wins}-${team.losses}` : '0-0';
  }, [teamStats]);

  const getScoreClass = (teamScore: number, opponentScore: number) => {
    return teamScore > opponentScore
      ? 'text-4xl font-bold text-white bg-[#00BFA6] rounded-lg px-2'
      : 'text-4xl font-bold text-[#333333]';
  };

  return (
    <div className="container mx-auto p-4 bg-[#f9f9f9]">
      <div className="title-container bg-white p-6 rounded-lg shadow-lg mb-8">
        <h1 className="text-4xl font-extrabold text-center text-[#333333]">Latest Games</h1>
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        {gameHeaders
          .filter((gameHeader) => gameHeader.gameSequence !== 1) // Filter out games with sequence 1
          .map((gameHeader) => {
            const homeTeamScore = lineScores.find(
              (score) => score.teamId === gameHeader.homeTeamId && score.gameId === gameHeader.gameId
            )?.pts || 0;
            const visitorTeamScore = lineScores.find(
              (score) => score.teamId === gameHeader.visitorTeamId && score.gameId === gameHeader.gameId
            )?.pts || 0;

            return (
              <div
                key={gameHeader.gameId}
                className="game-card bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300 border border-[#333333]"
              >
                <Link href={`/games/${gameHeader.gameId}`}>
                  <div className="flex justify-between items-center mb-4">
                    <div className="flex flex-col items-center w-1/3">
                    <Image
                        src={getTeamLogo(gameHeader.homeTeamId)}
                        alt={`${getTeamAbbreviation(gameHeader.homeTeamId, gameHeader.gameId)} Logo`}
                        width={64} 
                        height={64} 
                        className="w-16 h-16 mb-2"
                    />
                      <span className="text-2xl font-semibold text-[#333333]">
                        {getTeamAbbreviation(gameHeader.homeTeamId, gameHeader.gameId)}
                      </span>
                      <span className="text-sm text-gray-600">{getTeamRecord(gameHeader.homeTeamId)}</span>
                      <span className={getScoreClass(homeTeamScore, visitorTeamScore)}>{homeTeamScore}</span>
                    </div>
                    <div className="text-center text-[#333333] space-y-1 w-1/3">
                      <p className="text-xs">{gameHeader.gameDateEst}</p>
                      <p className="text-xs">{gameHeader.arenaName}</p>
                      <p className="text-lg font-bold">{gameHeader.gameStatusText}</p>
                    </div>
                    <div className="flex flex-col items-center w-1/3">
                    <Image
                        src={getTeamLogo(gameHeader.visitorTeamId)}
                        alt={`${getTeamAbbreviation(gameHeader.visitorTeamId, gameHeader.gameId)} Logo`}
                        width={64} 
                        height={64} 
                        className="w-16 h-16 mb-2"
                    />
                      <span className="text-2xl font-semibold text-[#333333]">
                        {getTeamAbbreviation(gameHeader.visitorTeamId, gameHeader.gameId)}
                      </span>
                      <span className="text-sm text-gray-600">{getTeamRecord(gameHeader.visitorTeamId)}</span>
                      <span className={getScoreClass(visitorTeamScore, homeTeamScore)}>{visitorTeamScore}</span>
                    </div>
                  </div>
                </Link>
              </div>
            );
          })}
      </div>

      <style jsx>{`
        .container {
          padding: 2rem;
        }
        .title-container {
          text-align: center;
          margin-bottom: 2rem;
          background-color: white;
          padding: 1rem;
          border-radius: 1rem;
          display: flex;
          justify-content: center;
          align-items: center;
          box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .game-card {
          background-color: white;
          border-radius: 1rem;
          padding: 1.5rem;
          box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
          transition: box-shadow 0.3s ease;
          border: 2px solid #333333;
        }
        .game-card:hover {
          box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .game-card .flex {
          justify-content: space-between;
          align-items: center;
        }
        .game-card img {
          max-width: 100%;
          height: auto;
        }
      `}</style>
    </div>
  );
};

export default GamesTab;
