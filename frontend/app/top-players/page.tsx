"use client";

import React, { useEffect, useState } from 'react';

type LeagueLeader = {
  id: number;
  playerId: number;
  teamAbbreviation: string | null;
  rank: number;
  playerName: string;
  gp: number;
  min: number;
  fgm: number;
  fga: number;
  fgPct: number;
  fg3m: number;
  fg3a: number;
  fg3Pct: number | null;
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
  pts: number;
  eff: number;
  astTov: number;
  stlTov: number;
};

const formatPercentage = (value: number) => (value).toFixed(3);

const TopPlayersPage: React.FC = () => {
  const [leagueLeaders, setLeagueLeaders] = useState<LeagueLeader[]>([]);

  useEffect(() => {
    const fetchLeagueLeaders = async () => {
      try {
        const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/leagueLeaders`);
        const data = await response.json();
        setLeagueLeaders(data);
      } catch (error) {
        console.error('Error fetching league leaders:', error);
      }
    };

    fetchLeagueLeaders();
  }, []);

  return (
    <div className="container mx-auto p-4 max-w-4xl bg-[#f9f9f9]">
      <div className="bg-white p-6 rounded-lg shadow-lg mb-6">
        <h1 className="text-3xl font-bold text-center text-[#333333]">Top Players</h1>
      </div>
      <div className="bg-white p-6 rounded-lg shadow-lg border-2 border-[#00BFA6]">
        <div className="overflow-x-auto scrollbar-thin scrollbar-thumb-rounded scrollbar-thumb-gray-400">
          <table className="min-w-full divide-y divide-gray-200" style={{ minWidth: '900px' }}>
            <thead className="bg-gray-50">
              <tr>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Rank</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Player</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">GP</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Min</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">FGM</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">FGA</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">FG%</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">3PM</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">3PA</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">3P%</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">FTM</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">FTA</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">FT%</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">OREB</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">DREB</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">REB</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">AST</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">STL</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">BLK</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">TOV</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">PF</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">PTS</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">EFF</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">AST/TO</th>
                <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">STL/TO</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {leagueLeaders.map((player) => (
                <tr key={player.id}>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{player.rank}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{player.playerName}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.gp}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.min}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.fgm}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.fga}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.fgPct}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.fg3m}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.fg3a}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.fg3Pct !== null ? player.fg3Pct : (formatPercentage(player.fg3m/player.fg3a))}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.ftm}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.fta}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.ftPct}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.oreb}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.dreb}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.reb}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.ast}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.stl}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.blk}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.tov}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.pf}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.pts}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.eff}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.astTov}</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">{player.stlTov}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default TopPlayersPage;
