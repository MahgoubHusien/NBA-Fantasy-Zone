import React, { useEffect, useState } from "react";
import Link from "next/link";
import axios from "axios";

interface GameHeader {
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
  seasonId: number;
  gameDate: string;
  homeTeamId: number;
  homeTeamName: string;
  homeTeamAbbreviation: string;
  visitorTeamId: number;
  visitorTeamName: string;
  visitorTeamAbbreviation: string;
  matchup: string;
  wl: string;
  min: number;
  pts: number;
  fgm: number;
  fga: number;
  fgPct: number;
  fg3m: number;
  fg3a: number;
  fg3Pct: number;
  ftm: number;
  fta: number;
  ftPct: number;
  oreb: number;
  dreb: number;
  reb: number;
  ast: number;
  stl: number;
  blk: number;
  tov: number;
  pf: number;
  plusMinus: number;
}

interface LineScore {
  gameDateEst: string;
  gameSequence: number;
  teamId: number;
  teamAbbreviation: string;
  teamCityName: string;
  teamName: string;
  teamWinsLosses: string;
  ptsQtr1: number;
  ptsQtr2: number;
  ptsQtr3: number;
  ptsQtr4: number;
  ptsOt1?: number;
  ptsOt2?: number;
  ptsOt3?: number;
  ptsOt4?: number;
  ptsOt5?: number;
  ptsOt6?: number;
  ptsOt7?: number;
  ptsOt8?: number;
  ptsOt9?: number;
  ptsOt10?: number;
  pts: number;
  fgPct: number;
  ftPct: number;
  fg3Pct: number;
  ast: number;
  reb: number;
  tov: number;
}

interface TeamStats {
  teamId: number;
  teamName: string;
  logoUrl: string;
}

const GamesTab: React.FC = () => {
  const [gameHeader, setGameHeader] = useState<GameHeader | null>(null);
  const [games, setGames] = useState<Games[]>([]);
  const [lineScore, setLineScore] = useState<LineScore[]>([]);
  const [teamStats, setTeamStats] = useState<TeamStats[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const gameHeaderResponse = await axios.get(`${process.env.NEXT_PUBLIC_API_URL}/gameHeader`);
        const gamesResponse = await axios.get(`${process.env.NEXT_PUBLIC_API_URL}/games`);
        const lineScoreResponse = await axios.get(`${process.env.NEXT_PUBLIC_API_URL}/lineScore`);
        const teamStatsResponse = await axios.get(`${process.env.NEXT_PUBLIC_API_URL}/teamStats`);

        setGameHeader(gameHeaderResponse.data);
        setGames(gamesResponse.data);
        setLineScore(lineScoreResponse.data);
        setTeamStats(teamStatsResponse.data);
      } catch (error) {
        console.error("Error fetching game data:", error);
      }
    };

    fetchData();
  }, []);

  const getTeamLogo = (teamId: number) => {
    const team = teamStats.find(team => team.teamId === teamId);
    return team ? team.logoUrl : "";
  };

  return (
    <div className="p-6 bg-[#333333] text-white">
      <h1 className="text-4xl font-bold mb-6 text-[#00BFA6]">Games</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {games.map((game, index) => (
          <div key={index} className="bg-gray-800 rounded-lg p-4 shadow-lg hover:shadow-xl transition-shadow duration-300">
            <Link href={`/game/${game.homeTeamId}-${game.visitorTeamId}`}>
              <div className="flex justify-between items-center mb-4">
                <div className="flex items-center">
                  <img src={getTeamLogo(game.homeTeamId)} alt={`${game.homeTeamName} Logo`} className="w-12 h-12 mr-2" />
                  <span className="text-2xl font-semibold">{game.homeTeamAbbreviation}</span>
                </div>
                <span className="text-xl font-bold">vs</span>
                <div className="flex items-center">
                  <img src={getTeamLogo(game.visitorTeamId)} alt={`${game.visitorTeamName} Logo`} className="w-12 h-12 mr-2" />
                  <span className="text-2xl font-semibold">{game.visitorTeamAbbreviation}</span>
                </div>
              </div>
              <div className="text-lg text-gray-400">{game.matchup}</div>
              <div className="text-sm text-gray-400">{game.gameDate}</div>
              {gameHeader && (
                <>
                  <div className="text-sm text-gray-400">{gameHeader.arenaName}</div>
                  <div className="text-sm text-gray-400">{gameHeader.natlTvBroadcasterAbbreviation || ''}</div>
                  <div className="text-sm text-gray-400">{gameHeader.homeTvBroadcasterAbbreviation || ''}</div>
                  <div className="text-sm text-gray-400">{gameHeader.awayTvBroadcasterAbbreviation || ''}</div>
                </>
              )}
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default GamesTab;
