'use client';

import React, { useState, useEffect, useCallback } from 'react';
import PlayerCard from '@/components/player-card';

interface Player {
  id: number;
  playerId: number;
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
}

const sortOptions = [
  { label: 'Default', value: 'commonPlayerInfo' },
  { label: 'Top Fantasy Points', value: 'top-fantasy-points' },
  { label: 'Top PPG', value: 'top-ppg' },
  { label: 'Top APG', value: 'top-apg' },
  { label: 'Top RPG', value: 'top-rpg' },
  { label: 'Top Points', value: 'top-total-points' },
  { label: 'Top Assists', value: 'top-total-assists' },
  { label: 'Top Rebounds', value: 'top-total-rebounds' },
  { label: 'Top Steals', value: 'top-total-steals' },
  { label: 'Top Blocks', value: 'top-total-blocks' },
  { label: 'Top FT%', value: 'top-free-throw-shooters' },
  { label: 'Top 3P%', value: 'top-three-point-shooters' },
  { label: 'Top Off. Rebounds', value: 'top-offensive-rebounders' },
  { label: 'Top Def. Rebounds', value: 'top-defensive-rebounders' },
  { label: 'Most Efficient', value: 'most-efficient-players' },
  { label: 'Most Eff. Scorers', value: 'most-efficient-scorers' },
  { label: 'Most Foul-Prone', value: 'most-foul-prone-players' },
  { label: 'Most Minutes', value: 'most-minutes-played' },
  { label: 'Most Double-Doubles', value: 'most-double-doubles' },
  { label: 'Most Triple-Doubles', value: 'most-triple-doubles' },
];

const PlayersPage: React.FC = () => {
  const [players, setPlayers] = useState<Player[]>([]);
  const [sortCriteria, setSortCriteria] = useState<string>('commonPlayerInfo');

  const fetchPlayers = useCallback(async (criteria: string) => {
    try {
      const url = `${process.env.NEXT_PUBLIC_API_URL}/api/players/${criteria}`;
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error(`Error fetching players: ${response.statusText}`);
      }
      const data = await response.json();
      setPlayers(Array.isArray(data) ? data : []);
    } catch (error) {
      console.error('Failed to fetch players:', error);
      setPlayers([]); 
    }
  }, []);

  useEffect(() => {
    fetchPlayers(sortCriteria);
  }, [sortCriteria, fetchPlayers]);

  const handleSortChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSortCriteria(event.target.value);
  };

  return (
    <div className="container mx-auto p-4 bg-[#f9f9f9]">
      <div className="flex items-center justify-between mb-4 w-full bg-white p-2 rounded-lg shadow-md">
        <h1 className="text-xl font-semibold text-[#333333]">Players</h1>
        <div>
          <label htmlFor="sort" className="mr-2 text-sm font-semibold text-[#333333]">Sort by:</label>
          <select
            id="sort"
            value={sortCriteria}
            onChange={handleSortChange}
            className="p-2 border rounded text-[#333333]"
          >
            {sortOptions.map((option) => (
              <option key={option.value} value={option.value}>
                {option.label}
              </option>
            ))}
          </select>
        </div>
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        {players.map((player) => (
          <PlayerCard key={player.id} player={player} />
        ))}
      </div>
    </div>
  );
};

export default PlayersPage;
