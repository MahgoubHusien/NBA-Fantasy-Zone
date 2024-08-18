'use client';

import React, { useState, useEffect, useCallback } from 'react';
import Link from 'next/link';
import Image from 'next/image';


interface Player {
  id: number;
  playerId: number;
  playerName: string;
  teamAbbreviation: string;
  gp: number;
  w: number;
  l: number;
  min: number;
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
  tov: number;
  stl: number;
  blk: number;
  blka: number;
  pf: number;
  pfd: number;
  pts: number;
  plusMinus: number;
  nbaFantasyPts: number;
  dd2: number;
  td3: number;
  photoUrl: string;
  [key: string]: any;
}

const categories = [
  { name: 'pts-rank', label: 'Points', stat: 'pts', unit: 'PTS' },
  { name: 'nba-fantasy-points-rank', label: 'NBA Fantasy Points', stat: 'nbaFantasyPts', unit: 'FP' },
  { name: 'gp-rank', label: 'Games Played', stat: 'gp', unit: 'Games' },
  { name: 'w-rank', label: 'Wins', stat: 'w', unit: 'Wins' },
  { name: 'l-rank', label: 'Losses', stat: 'l', unit: 'Losses' },
  { name: 'wpct-rank', label: 'Win Percentage', stat: 'wpct', unit: '%' },
  { name: 'min-rank', label: 'Minutes Played', stat: 'min', unit: 'Minutes' },
  { name: 'plus-minus-rank', label: 'Plus Minus', stat: 'plusMinus', unit: 'PM' },
  { name: 'fgm-rank', label: 'Field Goals Made', stat: 'fgm', unit: 'FGM' },
  { name: 'fga-rank', label: 'Field Goals Attempted', stat: 'fga', unit: 'FGA' },
  { name: 'fgpct-rank', label: 'Field Goal Percentage', stat: 'fgPct', unit: '%' },
  { name: 'fg3m-rank', label: 'Three-Point Field Goals Made', stat: 'fg3m', unit: '3PM' },
  { name: 'fg3a-rank', label: 'Three-Point Field Goals Attempted', stat: 'fg3a', unit: '3PA' },
  { name: 'fg3pct-rank', label: 'Three-Point Field Goal Percentage', stat: 'fg3Pct', unit: '%' },
  { name: 'ftm-rank', label: 'Free Throws Made', stat: 'ftm', unit: 'FTM' },
  { name: 'fta-rank', label: 'Free Throws Attempted', stat: 'fta', unit: 'FTA' },
  { name: 'ftpct-rank', label: 'Free Throw Percentage', stat: 'ftPct', unit: '%' },
  { name: 'oreb-rank', label: 'Offensive Rebounds', stat: 'oreb', unit: 'OREB' },
  { name: 'dreb-rank', label: 'Defensive Rebounds', stat: 'dreb', unit: 'DREB' },
  { name: 'reb-rank', label: 'Total Rebounds', stat: 'reb', unit: 'REB' },
  { name: 'ast-rank', label: 'Assists', stat: 'ast', unit: 'AST' },
  { name: 'tov-rank', label: 'Turnovers', stat: 'tov', unit: 'TO' },
  { name: 'stl-rank', label: 'Steals', stat: 'stl', unit: 'STL' },
  { name: 'blk-rank', label: 'Blocks', stat: 'blk', unit: 'BLK' },
  { name: 'blka-rank', label: 'Blocked Attempts', stat: 'blka', unit: 'BLKA' },
  { name: 'pf-rank', label: 'Personal Fouls', stat: 'pf', unit: 'PF' },
  { name: 'pfd-rank', label: 'Personal Fouls Drawn', stat: 'pfd', unit: 'PFD' },
  { name: 'dd2-rank', label: 'Double-Doubles', stat: 'dd2', unit: 'DD2' },
  { name: 'td3-rank', label: 'Triple-Doubles', stat: 'td3', unit: 'TD3' },
];

const LeagueLeadersPage: React.FC = () => {
  const [playersByCategory, setPlayersByCategory] = useState<{ [key: string]: Player[] }>({});
  const [topPlayers, setTopPlayers] = useState<Player[]>([]);

  const fetchTopPlayers = useCallback(async (category: string) => {
    try {
      const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/top-${category}`);
      const data = await response.json();
      setPlayersByCategory((prev) => ({ ...prev, [category]: Array.isArray(data) ? data : [] }));
    } catch (error) {
      console.error(`Failed to fetch top players for ${category}:`, error);
      setPlayersByCategory((prev) => ({ ...prev, [category]: [] }));
    }
  }, []);

  const fetchTopOverallPlayers = useCallback(async () => {
    try {
      const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/leagueLeaders`);
      const data = await response.json();
      setTopPlayers(data.slice(0, 5));
    } catch (error) {
      console.error('Failed to fetch top overall players:', error);
    }
  }, []);

  useEffect(() => {
    categories.forEach((category) => {
      fetchTopPlayers(category.name);
    });
    fetchTopOverallPlayers();
  }, [fetchTopPlayers, fetchTopOverallPlayers]);

  return (
    <div className="container mx-auto p-4">
      <div className="title-container">
        <h1 className="text-4xl font-extrabold text-center text-[#333333]">League Leaders</h1>
      </div>

      <div className="top-players-section bg-white p-6 rounded-lg shadow-lg mb-8">
        <h2 className="text-3xl font-semibold mb-4 text-center text-[#333333]">Top Players</h2>
        <table className="min-w-full text-[#333333]rounded-lg overflow-hidden shadow-md border-collapse">
          <thead className="bg-gray text-white">
            <tr>
              <th className="py-3 px-4 text-left">Rank</th>
              <th className="py-3 px-4 text-left">Player</th>
              <th className="py-3 px-4 text-center">Points</th>
              <th className="py-3 px-4 text-center">Assists</th>
              <th className="py-3 px-4 text-center">Rebounds</th>
            </tr>
          </thead>
          <tbody>
            {topPlayers.map((player, index) => (
              <tr key={player.id} className="hover:bg-[#333333]-100 transition border-b last:border-b-0">
                <td className="py-3 px-4">{index + 1}</td>
                <td className="py-3 px-4 flex items-center">
                <Image
                  src={player.photoUrl || '/placeholder.png'}
                  alt={player.playerName}
                  width={40}
                  height={40}
                  className="w-10 h-10 object-cover rounded-full mr-4"
                />
                  <Link href={`/players/${player.playerId}`} className="text-[#333333] hover:underline flex items-center space-x-2">
                    <span>{player.playerName}</span>
                  </Link>
                </td>
                <td className="py-3 px-4 text-center">{player.pts}</td>
                <td className="py-3 px-4 text-center">{player.ast}</td>
                <td className="py-3 px-4 text-center">{player.reb}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="flex justify-center mt-4">
          <Link href="/top-players">
            <button className="bg-[#333333] hover:bg-[#1a1a1a] text-white font-semibold py-1.5 px-3 rounded-lg transition">
              See More
            </button>
          </Link>
        </div>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        {categories.map((category) => (
          <div key={category.name} className="category-card p-4 rounded-lg shadow-lg bg-white">
            <h2 className="text-2xl font-semibold mb-2 gradient-title text-center">{category.label}</h2>
            <div className="flex justify-between mb-2 text-[#333333] text-sm text-center">
              <span>Rank</span>
              <span>Name</span>
              <span>{category.unit}</span>
            </div>
            <ul>
              {playersByCategory[category.name]?.slice(0, 5).map((player, index) => (
                <li key={index} className="flex justify-between py-2 border-b last:border-b-0">
                  <span>{index + 1}</span>
                  <Link href={`/players/${player.playerId}`} className="text-[#333333] hover:underline flex items-center space-x-2">
                  <Image
                    src={player.photoUrl || '/placeholder.png'}
                    alt={player.playerName}
                    width={40}
                    height={40}
                    className="w-10 h-10 object-cover rounded-full mr-4"
                  />
                    <span>{player.playerName} ({player.teamAbbreviation})</span>
                  </Link>
                  <span>
                    {category.name === 'wpct-rank'
                      ? ((player.w / player.gp) * 100).toFixed(2)
                      : category.stat === 'min'
                      ? (player[category.stat] as number).toFixed(2)
                      : category.name === 'fg3pct-rank'
                      ? ((player.fg3m / player.fg3a) * 100).toFixed(2)
                      : ['fgPct', 'fg3Pct', 'ftPct', 'wPct'].includes(category.stat)
                      ? (player[category.stat] * 100).toFixed(2)
                      : player[category.stat]}
                  </span>
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>

      <style jsx>{`
        .container {
          padding: 2rem;
          background-color: #f9f9f9;
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
        .top-players-section {
          border-radius: 1rem;
          border: 2px solid #00BFA6;
          background-color: white;
          box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        table {
          width: 100%;
          box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
          border-collapse: collapse;
        }
        th, td {
          padding: 0.75rem;
        }
        th {
          background-color: #333333;
          color: white;
        }
        tbody tr {
          border-bottom: 1px solid #e2e8f0;
        }
        .category-card {
          border-radius: 1rem;
          box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .category-card ul {
          list-style: none;
          padding: 0;
        }
        .category-card li {
          display: flex;
          justify-content: space-between;
          padding-top: 0.5rem;
          padding-bottom: 0.5rem;
          border-bottom: 1px solid #e2e8f0;
        }
        .category-card li:last-child {
          border-bottom: 0;
        }
        .gradient-title {
          background: linear-gradient(to right, #00BFA6, #00796b);
          -webkit-background-clip: text;
          -webkit-text-fill-color: transparent;
        }
        
        button {
          padding: 0.5rem 1rem;
          font-size: 1rem;
          border-radius: 1rem;
          font-weight: 600;
          transition: all 0.3s ease;
          background: #333333; 
          color: white;
        }
        
        button:hover {
          background: linear-gradient(to right, #00997f, #005b4f); 
          box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }
        
      `}</style>
    </div>
  );
};

export default LeagueLeadersPage;
