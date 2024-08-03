// app/players/[id]/page.tsx
'use client';

import React, { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';

type PlayerProfile = {
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
  fanDuelPtsLast5: number;
  nbaFantasyPtsLast5: number;
  ptsLast5: number;
  rebLast5: number;
  astLast5: number;
  fg3mLast5: number;
  ftPctLast5: number;
  stlLast5: number;
  blkLast5: number;
  tovLast5: number;
  fgPctLast5: number;
  fanDuelPtsSeason: number;
  nbaFantasyPtsSeason: number;
  ptsSeason: number;
  rebSeason: number;
  astSeason: number;
  fg3mSeason: number;
  fgPctSeason: number;
  tovSeason: number;
  photoUrl?: string;
};

const PlayerProfilePage = () => {
  const { id } = useParams();
  const [player, setPlayer] = useState<PlayerProfile | null>(null);

  useEffect(() => {
    const fetchPlayer = async () => {
      if (!id) return;

      try {
        const response1 = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/commonPlayerInfo/${id}`);
        const commonPlayerInfo = await response1.json();

        const response2 = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/currentPlayerStats/${id}`);
        const currentPlayerStats = await response2.json();

        const response3 = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/playerFantasyStats/${id}`);
        const playerFantasyStats = await response3.json();

        const playerData = { ...commonPlayerInfo, ...currentPlayerStats, ...playerFantasyStats };
        setPlayer(playerData);
      } catch (error) {
        console.error('Error fetching player:', error);
      }
    };

    fetchPlayer();
  }, [id]);

  if (!player) {
    return <p>Loading...</p>;
  }

  return (
    <div className="container mx-auto p-4">
      <div className="flex flex-col md:flex-row md:space-x-6">
        <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg flex flex-col items-center">
          <img src={player.photoUrl || '/placeholder.png'} alt={`${player.firstName} ${player.lastName}`} className="w-full md:w-1/3 object-cover rounded-lg" />
          <div className="mt-4 text-center bg-white p-4 rounded-lg shadow-md w-full">
            <h1 className="text-4xl font-bold">{player.firstName} {player.lastName}</h1>
            <p className="text-xl text-gray-600">{player.position} | {player.teamName}</p>
            <p className="text-lg text-gray-600">Jersey: {player.jersey}</p>
            <p className="text-lg text-gray-600">Height: {player.height}</p>
            <p className="text-lg text-gray-600">Weight: {player.weight}</p>
            <p className="text-lg text-gray-600">Age: {player.age}</p>
          </div>
        </div>
        <div className="flex-1 mt-6 md:mt-0">
          <div className="bg-white p-6 rounded-lg shadow-lg mb-6">
            <h2 className="text-2xl font-bold mb-4">Player Stats</h2>

            <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg">
              <div className="bg-white p-4 rounded-lg">
                <h3 className="text-xl font-bold mb-2">Key Stats</h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                  <p><span className="font-semibold">PPG:</span> {player.ppg}</p>
                  <p><span className="font-semibold">APG:</span> {player.apg}</p>
                  <p><span className="font-semibold">RPG:</span> {player.rpg}</p>
                  <p><span className="font-semibold">SPG:</span> {player.spg}</p>
                  <p><span className="font-semibold">TOPG:</span> {player.topg}</p>
                  <p><span className="font-semibold">BPG:</span> {player.bpg}</p>
                  <p><span className="font-semibold">PFPG:</span> {player.pfpg}</p>
                </div>
              </div>
            </div>

            <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg mt-4">
              <div className="bg-white p-4 rounded-lg">
                <h3 className="text-xl font-bold mb-2">Game Stats</h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                  <p><span className="font-semibold">GP:</span> {player.gp}</p>
                  <p><span className="font-semibold">W:</span> {player.w}</p>
                  <p><span className="font-semibold">L:</span> {player.l}</p>
                  <p><span className="font-semibold">Win %:</span> {player.wPct}</p>
                  <p><span className="font-semibold">MIN:</span> {player.min}</p>
                </div>
              </div>
            </div>

            <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg mt-4">
              <div className="bg-white p-4 rounded-lg">
                <h3 className="text-xl font-bold mb-2">Offensive Stats</h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                  <p><span className="font-semibold">PTS:</span> {player.pts}</p>
                  <p><span className="font-semibold">REB:</span> {player.reb}</p>
                  <p><span className="font-semibold">OREB:</span> {player.oreb}</p>
                  <p><span className="font-semibold">DREB:</span> {player.dreb}</p>
                  <p><span className="font-semibold">AST:</span> {player.ast}</p>
                  <p><span className="font-semibold">FGM:</span> {player.fgm}</p>
                  <p><span className="font-semibold">FGA:</span> {player.fga}</p>
                  <p><span className="font-semibold">FG%:</span> {player.fgPct}</p>
                  <p><span className="font-semibold">3PM:</span> {player.fg3m}</p>
                  <p><span className="font-semibold">3PA:</span> {player.fg3a}</p>
                  <p><span className="font-semibold">3P%:</span> {player.fg3Pct}</p>
                  <p><span className="font-semibold">FTM:</span> {player.ftm}</p>
                  <p><span className="font-semibold">FTA:</span> {player.fta}</p>
                  <p><span className="font-semibold">FT%:</span> {player.ftPct}</p>
                </div>
              </div>
            </div>

            <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg mt-4">
              <div className="bg-white p-4 rounded-lg">
                <h3 className="text-xl font-bold mb-2">Defensive Stats</h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                  <p><span className="font-semibold">STL:</span> {player.stl}</p>
                  <p><span className="font-semibold">BLK:</span> {player.blk}</p>
                  <p><span className="font-semibold">BLKA:</span> {player.blka}</p>
                  <p><span className="font-semibold">PF:</span> {player.pf}</p>
                  <p><span className="font-semibold">PFD:</span> {player.pfd}</p>
                  <p><span className="font-semibold">Plus/Minus:</span> {player.plusMinus}</p>
                  <p><span className="font-semibold">NBA Fantasy Pts:</span> {player.nbaFantasyPts}</p>
                </div>
              </div>
            </div>

            <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg mt-4">
              <div className="bg-white p-4 rounded-lg">
                <h3 className="text-xl font-bold mb-2">Fantasy Stats</h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                  <p><span className="font-semibold">FanDuel Pts (Last 5):</span> {player.fanDuelPtsLast5}</p>
                  <p><span className="font-semibold">NBA Fantasy Pts (Last 5):</span> {player.nbaFantasyPtsLast5}</p>
                  <p><span className="font-semibold">Pts (Last 5):</span> {player.ptsLast5}</p>
                  <p><span className="font-semibold">Reb (Last 5):</span> {player.rebLast5}</p>
                  <p><span className="font-semibold">Ast (Last 5):</span> {player.astLast5}</p>
                  <p><span className="font-semibold">3PM (Last 5):</span> {player.fg3mLast5}</p>
                  <p><span className="font-semibold">FT% (Last 5):</span> {player.ftPctLast5}</p>
                  <p><span className="font-semibold">Stl (Last 5):</span> {player.stlLast5}</p>
                  <p><span className="font-semibold">Blk (Last 5):</span> {player.blkLast5}</p>
                  <p><span className="font-semibold">TOV (Last 5):</span> {player.tovLast5}</p>
                  <p><span className="font-semibold">FG% (Last 5):</span> {player.fgPctLast5}</p>
                  <p><span className="font-semibold">FanDuel Pts (Season):</span> {player.fanDuelPtsSeason}</p>
                  <p><span className="font-semibold">NBA Fantasy Pts (Season):</span> {player.nbaFantasyPtsSeason}</p>
                  <p><span className="font-semibold">Pts (Season):</span> {player.ptsSeason}</p>
                  <p><span className="font-semibold">Reb (Season):</span> {player.rebSeason}</p>
                  <p><span className="font-semibold">Ast (Season):</span> {player.astSeason}</p>
                  <p><span className="font-semibold">3PM (Season):</span> {player.fg3mSeason}</p>
                  <p><span className="font-semibold">FG% (Season):</span> {player.fgPctSeason}</p>
                  <p><span className="font-semibold">TOV (Season):</span> {player.tovSeason}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PlayerProfilePage;
