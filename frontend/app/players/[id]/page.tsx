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
  dd2: number;
  td3: number;
  gpRank: number;
  wRank: number;
  lRank: number;
  minRank: number;
  fgmRank: number;
  fgaRank: number;
  fgPctRank: number;
  fg3mRank: number;
  fg3aRank: number;
  fg3PctRank: number;
  ftmRank: number;
  ftaRank: number;
  ftPctRank: number;
  orebRank: number;
  drebRank: number;
  rebRank: number;
  astRank: number;
  tovRank: number;
  stlRank: number;
  blkRank: number;
  blkaRank: number;
  pfRank: number;
  pfdRank: number;
  ptsRank: number;
  plusMinusRank: number;
  nbaFantasyPtsRank: number;
  dd2Rank: number;
  td3Rank: number;
  wPctRank: number;
  photoUrl?: string;
};

const formatPercentage = (value: number) => (value * 100).toFixed(2);
const formatDecimal = (value: number) => value.toFixed(2);
const formatHeight = (height: string) => {
  const feet = Math.floor(parseInt(height) / 12);
  const inches = parseInt(height) % 12;
  return `${feet}' ${inches}"`;
};
const formatWeight = (weight: string) => `${weight} lbs`;

const getTopFiveStats = (ppg: number, apg: number, rpg: number, spg: number, topg: number, bpg: number, pfpg: number) => {
  const stats = [
    { name: 'PPG', value: ppg },
    { name: 'APG', value: apg },
    { name: 'RPG', value: rpg },
    { name: 'SPG', value: spg },
    { name: 'TOPG', value: topg },
    { name: 'BPG', value: bpg },
    { name: 'PFPG', value: pfpg }
  ];
  return stats.sort((a, b) => b.value - a.value).slice(0, 5);
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

    const fetchPlayerRanks = async () => {
      if (!id) return;

      try {
        const fg3PctRankResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/top-fg3pct-rank`);
        const fg3PctRankData = await fg3PctRankResponse.json();
        const fg3PctRank = fg3PctRankData.findIndex((p: PlayerProfile) => p.id === Number(id)) + 1;

        const dd2RankResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/top-dd2-rank`);
        const dd2RankData = await dd2RankResponse.json();
        const dd2Rank = dd2RankData.findIndex((p: PlayerProfile) => p.id === Number(id)) + 1;

        const td3RankResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/top-td3-rank`);
        const td3RankData = await td3RankResponse.json();
        const td3Rank = td3RankData.findIndex((p: PlayerProfile) => p.id === Number(id)) + 1;

        const wPctRankResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/top-wpct-rank`);
        const wPctRankData = await wPctRankResponse.json();
        const wPctRank = wPctRankData.findIndex((p: PlayerProfile) => p.id === Number(id)) + 1;

        const wRankResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/top-w-rank`);
        const wRankData = await wRankResponse.json();
        const wRank = wRankData.findIndex((p: PlayerProfile) => p.id === Number(id)) + 1;

        const lRankResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/top-l-rank`);
        const lRankData = await lRankResponse.json();
        const lRank = lRankData.findIndex((p: PlayerProfile) => p.id === Number(id)) + 1;

        setPlayer(prevPlayer => prevPlayer ? {
          ...prevPlayer,
          fg3PctRank,
          dd2Rank,
          td3Rank,
          wPctRank,
          wRank,
          lRank
        } : null);
      } catch (error) {
        console.error('Error fetching player ranks:', error);
      }
    };

    fetchPlayer();
    fetchPlayerRanks();
  }, [id]);

  if (!player) {
    return <p>Loading...</p>;
  }

  const topFiveStats = getTopFiveStats(player.ppg, player.apg, player.rpg, player.spg, player.topg, player.bpg, player.pfpg);

  return (
    <div className="container mx-auto p-4">
      <div className="bg-white rounded-lg shadow-lg p-6 flex flex-wrap items-center justify-between space-x-6">
        <div className="flex flex-col items-center justify-center flex-shrink-0">
          <img
            src={player.photoUrl || '/placeholder.png'}
            alt={`${player.firstName} ${player.lastName}`}
            className="w-48 h-48 object-cover rounded-full"
          />
        </div>
        <div className="flex flex-col justify-center flex-grow space-y-2">
          <h1 className="text-4xl font-bold">{player.firstName} {player.lastName}</h1>
          <div className="text-lg text-gray-600">
            <p>{player.position} | {player.teamName}</p>
            <p>Jersey: {player.jersey}</p>
            <p>Height: {formatHeight(player.height)}</p>
            <p>Weight: {formatWeight(player.weight)}</p>
            <p>Age: {player.age}</p>
          </div>
        </div>
        <div className="flex flex-wrap space-x-4 w-full sm:w-auto">
          <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg max-w-xs">
            <div className="bg-white p-4 rounded-lg h-full">
              <h2 className="text-2xl font-bold mb-4 text-center">Top 5 Stats</h2>
              <div className="overflow-x-auto">
                <table className="min-w-full divide-y divide-gray-200 table-auto">
                  <thead>
                    <tr>
                      <th className="px-4 py-2 text-left text-sm font-medium text-gray-500 uppercase tracking-wider">Stat</th>
                      <th className="px-4 py-2 text-left text-sm font-medium text-gray-500 uppercase tracking-wider">Value</th>
                    </tr>
                  </thead>
                  <tbody>
                    {topFiveStats.map(stat => (
                      <tr key={stat.name}>
                        <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-700">{stat.name}</td>
                        <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-900">{stat.value}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg max-w-xs mt-4 sm:mt-0">
            <div className="bg-white p-4 rounded-lg h-full">
              <h2 className="text-2xl font-bold mb-4 text-center">Fantasy Stats</h2>
              <div className="overflow-hidden">
                <table className="min-w-full divide-y divide-gray-200 table-fixed">
                  <thead>
                    <tr>
                      <th className="px-4 py-2 text-left text-sm font-medium text-gray-500 uppercase tracking-wider">Stat</th>
                      <th className="px-4 py-2 text-left text-sm font-medium text-gray-500 uppercase tracking-wider">Value</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-700">NBA Fantasy Pts:</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-900">{player.nbaFantasyPts}</td>
                    </tr>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-700">Avg FanDuel Pts (Last 5)</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-900">{player.fanDuelPtsLast5}</td>
                    </tr>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-700">Avg NBA Fantasy Pts (Last 5)</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-900">{player.nbaFantasyPtsLast5}</td>
                    </tr>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-700">Avg FanDuel Pts (Season)</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-900">{player.fanDuelPtsSeason}</td>
                    </tr>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-700">Avg NBA Fantasy Pts (Season)</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-900">{player.nbaFantasyPtsSeason}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
        <div className="mt-6 bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-2xl font-bold mb-4">Game Stats</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              <p><span className="font-semibold">GP:</span> {player.gp}</p>
              <p><span className="font-semibold">W:</span> {player.w}</p>
              <p><span className="font-semibold">L:</span> {player.l}</p>
              <p><span className="font-semibold">MIN:</span> {formatDecimal(player.min)}</p>
              <p><span className="font-semibold">Win %:</span> {formatPercentage(player.w / player.gp)}</p>
              <p><span className="font-semibold">Plus/Minus:</span> {player.plusMinus}</p>
            </div>
          </div>
        </div>

        <div className="mt-6 bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-2xl font-bold mb-4">Offensive Stats</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              <p><span className="font-semibold">PTS:</span> {player.pts}</p>
              <p><span className="font-semibold">PPG:</span> {player.ppg}</p>
              <p><span className="font-semibold">AST:</span> {player.ast}</p>
              <p><span className="font-semibold">APG:</span> {player.apg}</p>
              <p><span className="font-semibold">REB:</span> {player.reb}</p>
              <p><span className="font-semibold">RPG:</span> {player.rpg}</p>
              <p><span className="font-semibold">OREB:</span> {player.oreb}</p>
              <p><span className="font-semibold">DREB:</span> {player.dreb}</p>
              <p><span className="font-semibold">FGM:</span> {player.fgm}</p>
              <p><span className="font-semibold">FGA:</span> {player.fga}</p>
              <p><span className="font-semibold">FG%:</span> {formatPercentage(player.fgPct)}</p>
              <p><span className="font-semibold">3PM:</span> {player.fg3m}</p>
              <p><span className="font-semibold">3PA:</span> {player.fg3a}</p>
              <p><span className="font-semibold">3P%:</span> {formatPercentage(player.fg3m / player.fg3a)}</p>
              <p><span className="font-semibold">FTM:</span> {player.ftm}</p>
              <p><span className="font-semibold">FTA:</span> {player.fta}</p>
              <p><span className="font-semibold">FT%:</span> {formatPercentage(player.ftPct)}</p>
              <p><span className="font-semibold">Double-Doubles:</span> {player.dd2}</p>
              <p><span className="font-semibold">Triple-Doubles:</span> {player.td3}</p>
            </div>
          </div>
        </div>

        <div className="mt-6 bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-2xl font-bold mb-4">Defensive Stats</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              <p><span className="font-semibold">STL:</span> {player.stl}</p>
              <p><span className="font-semibold">SPG:</span> {player.spg}</p>
              <p><span className="font-semibold">BLK:</span> {player.blk}</p>
              <p><span className="font-semibold">BPG:</span> {player.bpg}</p>
              <p><span className="font-semibold">BLKA:</span> {player.blka}</p>
              <p><span className="font-semibold">PF:</span> {player.pf}</p>
              <p><span className="font-semibold">PFPG:</span> {player.pfpg}</p>
              <p><span className="font-semibold">PFD:</span> {player.pfd}</p>
              <p><span className="font-semibold">TOV:</span> {player.tov}</p>
              <p><span className="font-semibold">TOVPG:</span> {player.topg}</p>
            </div>
          </div>
        </div>
        <div className="bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg mt-4">
              <div className="bg-white p-4 rounded-lg">
                <h3 className="text-2xl font-bold mb-4">Fantasy</h3>
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

        <div className="mt-6 bg-gradient-to-r from-blue-500 to-purple-500 p-1 rounded-lg shadow-lg">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-2xl font-bold mb-4">Ranks</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              <p><span className="font-semibold">GP Rank:</span> {player.gpRank}</p>
              <p><span className="font-semibold">Wins Rank:</span> {player.wRank}</p>
              <p><span className="font-semibold">Losses Rank:</span> {player.lRank}</p>
              <p><span className="font-semibold">Win % Rank:</span> {player.wPctRank}</p>
              <p><span className="font-semibold">Minutes Rank:</span> {player.minRank}</p>
              <p><span className="font-semibold">Plus/Minus Rank:</span> {player.plusMinusRank}</p>
              <p><span className="font-semibold">FGM Rank:</span> {player.fgmRank}</p>
              <p><span className="font-semibold">FGA Rank:</span> {player.fgaRank}</p>
              <p><span className="font-semibold">FG% Rank:</span> {player.fgPctRank}</p>
              <p><span className="font-semibold">3PM Rank:</span> {player.fg3mRank}</p>
              <p><span className="font-semibold">3PA Rank:</span> {player.fg3aRank}</p>
              <p><span className="font-semibold">3P% Rank:</span> {player.fg3PctRank}</p>
              <p><span className="font-semibold">FTM Rank:</span> {player.ftmRank}</p>
              <p><span className="font-semibold">FTA Rank:</span> {player.ftaRank}</p>
              <p><span className="font-semibold">FT% Rank:</span> {player.ftPctRank}</p>
              <p><span className="font-semibold">OREB Rank:</span> {player.orebRank}</p>
              <p><span className="font-semibold">DREB Rank:</span> {player.drebRank}</p>
              <p><span className="font-semibold">REB Rank:</span> {player.rebRank}</p>
              <p><span className="font-semibold">AST Rank:</span> {player.astRank}</p>
              <p><span className="font-semibold">TOV Rank:</span> {player.tovRank}</p>
              <p><span className="font-semibold">STL Rank:</span> {player.stlRank}</p>
              <p><span className="font-semibold">BLK Rank:</span> {player.blkRank}</p>
              <p><span className="font-semibold">BLKA Rank:</span> {player.blkaRank}</p>
              <p><span className="font-semibold">PF Rank:</span> {player.pfRank}</p>
              <p><span className="font-semibold">PFD Rank:</span> {player.pfdRank}</p>
              <p><span className="font-semibold">PTS Rank:</span> {player.ptsRank}</p>
              <p><span className="font-semibold">Double-Doubles Rank:</span> {player.dd2Rank}</p>
              <p><span className="font-semibold">Triple-Doubles Rank:</span> {player.td3Rank}</p>
              <p><span className="font-semibold">NBA Fantasy Pts Rank:</span> {player.nbaFantasyPtsRank}</p>
            </div>
          </div>
        </div>
      </div>
    );
  };

  export default PlayerProfilePage;
