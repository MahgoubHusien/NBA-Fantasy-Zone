'use client';

import React, { useState, useEffect } from 'react';
import PlayerCard from '@/components/player-card';

type CommonPlayerInfo = {
  id: number;
  firstName: string;
  lastName: string;
  teamName: string;
  teamAbbreviation: string;
  jersey: string;
  position: string;
  height: string;
  weight: string;
  age: number;
  ppg: number;
  apg: number;
  rpg: number;
  spg: number;
  topg: number;
  bpg: number;
  pfpg: number;
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
  photoUrl?: string;
};

const PlayersPage = () => {
  const [players, setPlayers] = useState<CommonPlayerInfo[]>([]);
  const [sortCriteria, setSortCriteria] = useState<string>('commonPlayerInfo');

  const fetchPlayers = async (sortCriteria: string) => {
    try {
      const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/${sortCriteria}`);
      const data = await response.json();
      setPlayers(data);
    } catch (error) {
      console.error('Failed to fetch players:', error);
    }
  };

  useEffect(() => {
    fetchPlayers(sortCriteria);
  }, [sortCriteria]);

  const handleSort = (criteria: string) => {
    if (sortCriteria === criteria) {
      setSortCriteria('commonPlayerInfo');
    } else {
      setSortCriteria(criteria);
    }
  };

  return (
    <div className="container mx-auto p-4">
      <div className="w-full bg-white p-2 rounded-lg shadow-md">
        <div className="flex items-center">
          <span className="mr-2 text-sm font-semibold">Sort by:</span>
          <div className="sort-container flex space-x-2 overflow-x-auto">
            <button
              onClick={() => handleSort('top-fantasy-points')}
              className={`sort-button ${sortCriteria === 'top-fantasy-points' ? 'active' : ''}`}
            >
              Top Fantasy Points
            </button>
            <button
              onClick={() => handleSort('top-ppg')}
              className={`sort-button ${sortCriteria === 'top-ppg' ? 'active' : ''}`}
            >
              Top PPG
            </button>
            <button
              onClick={() => handleSort('top-apg')}
              className={`sort-button ${sortCriteria === 'top-apg' ? 'active' : ''}`}
            >
              Top APG
            </button>
            <button
              onClick={() => handleSort('top-rpg')}
              className={`sort-button ${sortCriteria === 'top-rpg' ? 'active' : ''}`}
            >
              Top RPG
            </button>
            <button
              onClick={() => handleSort('top-total-points')}
              className={`sort-button ${sortCriteria === 'top-total-points' ? 'active' : ''}`}
            >
              Top Points
            </button>
            <button
              onClick={() => handleSort('top-total-assists')}
              className={`sort-button ${sortCriteria === 'top-total-assists' ? 'active' : ''}`}
            >
              Top Assists
            </button>
            <button
              onClick={() => handleSort('top-total-rebounds')}
              className={`sort-button ${sortCriteria === 'top-total-rebounds' ? 'active' : ''}`}
            >
              Top Rebounds
            </button>
            <button
              onClick={() => handleSort('top-total-steals')}
              className={`sort-button ${sortCriteria === 'top-total-steals' ? 'active' : ''}`}
            >
              Top Steals
            </button>
            <button
              onClick={() => handleSort('top-total-blocks')}
              className={`sort-button ${sortCriteria === 'top-total-blocks' ? 'active' : ''}`}
            >
              Top Blocks
            </button>
            <button
              onClick={() => handleSort('top-free-throw-shooters')}
              className={`sort-button ${sortCriteria === 'top-free-throw-shooters' ? 'active' : ''}`}
            >
              Top FT%
            </button>
            <button
              onClick={() => handleSort('top-three-point-shooters')}
              className={`sort-button ${sortCriteria === 'top-three-point-shooters' ? 'active' : ''}`}
            >
              Top 3P%
            </button>
            <button
              onClick={() => handleSort('top-offensive-rebounders')}
              className={`sort-button ${sortCriteria === 'top-offensive-rebounders' ? 'active' : ''}`}
            >
              Top Off. Rebounds
            </button>
            <button
              onClick={() => handleSort('top-defensive-rebounders')}
              className={`sort-button ${sortCriteria === 'top-defensive-rebounders' ? 'active' : ''}`}
            >
              Top Def. Rebounds
            </button>
            <button
              onClick={() => handleSort('most-efficient-players')}
              className={`sort-button ${sortCriteria === 'most-efficient-players' ? 'active' : ''}`}
            >
              Most Efficient
            </button>
            <button
              onClick={() => handleSort('most-efficient-scorers')}
              className={`sort-button ${sortCriteria === 'most-efficient-scorers' ? 'active' : ''}`}
            >
              Most Eff. Scorers
            </button>
            <button
              onClick={() => handleSort('most-foul-prone-players')}
              className={`sort-button ${sortCriteria === 'most-foul-prone-players' ? 'active' : ''}`}
            >
              Most Foul-Prone
            </button>
            <button
              onClick={() => handleSort('most-minutes-played')}
              className={`sort-button ${sortCriteria === 'most-minutes-played' ? 'active' : ''}`}
            >
              Most Minutes
            </button>
            <button
              onClick={() => handleSort('most-double-doubles')}
              className={`sort-button ${sortCriteria === 'most-double-doubles' ? 'active' : ''}`}
            >
              Most Double-Doubles
            </button>
            <button
              onClick={() => handleSort('most-triple-doubles')}
              className={`sort-button ${sortCriteria === 'most-triple-doubles' ? 'active' : ''}`}
            >
              Most Triple-Doubles
            </button>
          </div>
        </div>
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        {players.map((player) => (
          <PlayerCard key={player.id} player={player} />
        ))}
      </div>
      <style jsx>{`
        .sort-button {
          display: inline-block;
          padding: 0.5rem 1rem;
          margin: 0.5rem;
          border: 2px solid;
          border-image-slice: 1;
          border-width: 2px;
          border-image-source: linear-gradient(to right, #4f46e5, #9333ea);
          color: black;
          background-color: white;
          text-align: center;
          border-radius: 0.375rem;
          transition: all 0.3s ease-in-out;
          white-space: nowrap;
          box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .sort-button:hover {
          cursor: pointer;
          transform: scale(1.05);
        }

        .sort-button.active {
          background-image: linear-gradient(to right, #4f46e5, #9333ea);
          color: white;
        }

        .sort-container {
          overflow-x: auto;
          white-space: nowrap;
          max-width: 100%;
        }

        .container {
          max-width: 100%;
          overflow-x: hidden;
        }
      `}</style>
    </div>
  );
};

export default PlayersPage;
