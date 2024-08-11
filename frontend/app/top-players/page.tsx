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
    <div className="container mx-auto p-4">
      <div className="bg-white p-6 rounded-lg shadow-lg mb-6">
        <h1 className="text-3xl font-bold text-center">Top Players</h1>
      </div>
      <div className="bg-white p-6 rounded-lg shadow-lg">
        <table className="min-w-full divide-y divide-gray-200">
          <thead>
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Rank</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Player</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Points</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Assists</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Rebounds</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Steals</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Blocks</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Turnovers</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Efficiency</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {leagueLeaders.map((player) => (
              <tr key={player.id}>
                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{player.rank}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{player.playerName}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{player.pts}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{player.ast}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{player.reb}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{player.stl}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{player.blk}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{player.tov}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{player.eff}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default TopPlayersPage;
