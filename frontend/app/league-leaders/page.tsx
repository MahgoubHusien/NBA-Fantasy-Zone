'use client';

import React, { useState, useEffect } from 'react';
import Link from 'next/link';

interface Player {
  id: number;
  playerId: number;
  teamAbbreviation: string;
  firstName: string;
  lastName: string;
  gp: number;
  w: number;
  l: number;
  wPct: number;
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
  photoUrl?: string;
  [key: string]: any;
}

const categories = [
  { name: 'gp-rank', label: 'Games Played', stat: 'gp', unit: 'games' },
  { name: 'w-rank', label: 'Wins', stat: 'w', unit: 'wins' },
  { name: 'l-rank', label: 'Losses', stat: 'l', unit: 'losses' },
  { name: 'wpct-rank', label: 'Win Percentage', stat: 'wPct', unit: '%' },
  { name: 'min-rank', label: 'Minutes Played', stat: 'min', unit: 'minutes' },
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
  { name: 'pts-rank', label: 'Points', stat: 'pts', unit: 'PTS' },
  { name: 'plus-minus-rank', label: 'Plus Minus', stat: 'plusMinus', unit: 'PM' },
  { name: 'nba-fantasy-points-rank', label: 'NBA Fantasy Points', stat: 'nbaFantasyPts', unit: 'FP' },
  { name: 'dd2-rank', label: 'Double-Doubles', stat: 'dd2', unit: 'DD2' },
  { name: 'td3-rank', label: 'Triple-Doubles', stat: 'td3', unit: 'TD3' },
];

const LeagueLeadersPage = () => {
  const [playersByCategory, setPlayersByCategory] = useState<{ [key: string]: Player[] }>({});

  const fetchTopPlayers = async (category: string) => {
    try {
      const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/top-${category}`);
      const data = await response.json();
      setPlayersByCategory((prev) => ({ ...prev, [category]: Array.isArray(data) ? data : [] }));
    } catch (error) {
      console.error(`Failed to fetch top players for ${category}:`, error);
      setPlayersByCategory((prev) => ({ ...prev, [category]: [] }));
    }
  };

  useEffect(() => {
    categories.forEach((category) => {
      fetchTopPlayers(category.name);
    });
  }, []);

  return (
    <div className="container mx-auto p-4">
      <div className="title-container">
        <h1 className="text-3xl font-bold mb-4">League Leaders</h1>
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {categories.map((category) => (
          <div key={category.name} className="category-card">
            <h2 className="text-xl font-semibold mb-2 text-center rounded-full bg-gray-100 p-2 shadow-md">{category.label}</h2>
            <div className="text-sm text-gray-500 mb-2 flex justify-between">
              <span>Rank</span>
              <span>Name</span>
              <span>{category.unit}</span>
            </div>
            <ul>
              {playersByCategory[category.name]?.slice(0, 5).map((player, index) => (
                <li key={index} className="flex justify-between items-center py-2 border-b last:border-b-0">
                  <span>{index + 1}.</span>
                  <div className="flex items-center space-x-2">
                    <img
                      src={player.photoUrl || '/placeholder.png'}
                      alt={`${player.firstName} ${player.lastName}`}
                      className="w-8 h-8 rounded-full"
                    />
                    <Link href={`/players/${player.id}`} legacyBehavior>
                      <a className="flex items-center space-x-2 hover:underline">
                        <span>{player.firstName} {player.lastName}</span>
                        <span>({player.teamAbbreviation})</span>
                      </a>
                    </Link>
                  </div>
                  <span>{category.stat === 'min' ? (player[category.stat] as number).toFixed(2) : player[category.stat]}</span>
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
      <style jsx>{`
        .container {
          padding: 2rem;
        }
        .title-container {
          display: flex;
          justify-content: center;
          align-items: center;
          background-color: white;
          box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
          border-radius: 0.5rem;
          padding: 1rem;
          margin-bottom: 2rem;
        }
        .category-card {
          background-color: white;
          box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
          border-radius: 0.5rem;
          padding: 1rem;
        }
        .category-card ul {
          list-style: none;
          padding: 0;
        }
        .category-card li {
          display: flex;
          justify-content: space-between;
          align-items: center;
          padding-top: 0.5rem;
          padding-bottom: 0.5rem;
          border-bottom: 1px solid #e2e8f0;
        }
        .category-card li:last-child {
          border-bottom: 0;
        }
        .category-card h2 {
          margin-bottom: 0.5rem;
        }
      `}</style>
    </div>
  );
};

export default LeagueLeadersPage;
