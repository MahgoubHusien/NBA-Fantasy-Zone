'use client';

import React, { useEffect, useState, useCallback } from 'react';
import { useParams } from 'next/navigation';


type PlayerProfile = {
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
  rank: number;
  photoUrl?: string;
};

const formatPercentage = (value: number) => (value * 100).toFixed(2);
const formatDecimal = (value: number) => value.toFixed(2);
const formatHeight = (height: string) => {
  const [feet, inches] = height.split('-');
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
    { name: 'PFPG', value: pfpg },
  ];
  return stats.sort((a, b) => b.value - a.value).slice(0, 5);
};

const PlayerProfilePage = () => {
  const { id } = useParams();
  const [player, setPlayer] = useState<PlayerProfile | null>(null);

  const fetchPlayerData = useCallback(async () => {
    if (!id) return;

    try {
      const [commonPlayerInfo, currentPlayerStats, playerFantasyStats] = await Promise.all([
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/commonPlayerInfo/${id}`).then((res) => res.json()),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/currentPlayerStats/${id}`).then((res) => res.json()),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/playerFantasyStats/${id}`).then((res) => res.json()),
      ]);

      const playerData = { ...commonPlayerInfo, ...currentPlayerStats, ...playerFantasyStats };
      const playerId = playerData.playerId;

      const [
        rankData,
        fg3PctRankData,
        dd2RankData,
        td3RankData,
        wPctRankData,
        wRankData,
        lRankData,
      ] = await Promise.all([
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/leagueLeader/${playerId}`).then((res) => res.json()),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/top-fg3pct-rank`).then((res) => res.json()),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/top-dd2-rank`).then((res) => res.json()),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/top-td3-rank`).then((res) => res.json()),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/top-wpct-rank`).then((res) => res.json()),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/top-w-rank`).then((res) => res.json()),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/top-l-rank`).then((res) => res.json()),
      ]);

      const rank = rankData.rank || null;
      const fg3PctRank = fg3PctRankData.findIndex((p: PlayerProfile) => p.playerId === playerId) + 1;
      const dd2Rank = dd2RankData.findIndex((p: PlayerProfile) => p.playerId === playerId) + 1;
      const td3Rank = td3RankData.findIndex((p: PlayerProfile) => p.playerId === playerId) + 1;
      const wPctRank = wPctRankData.findIndex((p: PlayerProfile) => p.playerId === playerId) + 1;
      const wRank = wRankData.findIndex((p: PlayerProfile) => p.playerId === playerId) + 1;
      const lRank = lRankData.findIndex((p: PlayerProfile) => p.playerId === playerId) + 1;

      setPlayer({ ...playerData, rank, fg3PctRank, dd2Rank, td3Rank, wPctRank, wRank, lRank });
    } catch (error) {
      console.error('Error fetching player:', error);
    }
  }, [id]);

  useEffect(() => {
    fetchPlayerData();
  }, [fetchPlayerData]);

  if (!player) {
    return <p>Loading...</p>;
  }

  const topFiveStats = getTopFiveStats(player.ppg, player.apg, player.rpg, player.spg, player.topg, player.bpg, player.pfpg);

  return (
    <div className="container mx-auto p-10 bg-[#f9f9f9]">
      <div className="bg-white rounded-lg shadow-lg p-6 flex flex-wrap items-center justify-between space-x-6 border-2 border-[#333333]">
        <div className="flex flex-col items-center justify-center flex-shrink-0 ml-2">
          <img
            src={player.photoUrl || '/placeholder.png'}
            alt={`${player.firstName} ${player.lastName}`}
            className="w-40 h-40 object-cover shadow-lg rounded-lg"
          />
        </div>
        <div className="flex flex-col justify-center flex-grow space-y-2 relative left-12">
          <h1 className="text-3xl font-bold text-[#333333]">
            {player.firstName}
            <br /> {player.lastName}
          </h1>
          <div className="text-m text-[#333333]-600">
            <p>{player.position} | {player.teamName}</p>
            <p>Jersey: {player.jersey}</p>
            <p>Height: {formatHeight(player.height)}</p>
            <p>Weight: {formatWeight(player.weight)}</p>
            <p>Age: {player.age}</p>
            <p>NBA Rank: {player.rank}</p>
          </div>
        </div>

        <div className="flex flex-wrap space-x-4 w-full sm:w-auto flex-col sm:flex-row">
          <div className="p-1 rounded-lg shadow-lg max-w-xs flex-1 mr-2">
            <div className="bg-white p-4 rounded-lg h-full">
              <h2 className="text-2xl font-bold mb-4 text-[#333333] text-center text-[#333333]">Top 5 Stats</h2>
              <div className="overflow-x-auto">
                <table className="min-w-full divide-y divide-[#333333]-200 table-auto">
                  <thead>
                    <tr>
                      <th className="px-4 py-2 text-left text-sm font-medium text-[#333333] uppercase tracking-wider">Stat</th>
                      <th className="px-4 py-2 text-left text-sm font-medium text-[#333333] uppercase tracking-wider">Value</th>
                    </tr>
                  </thead>
                  <tbody>
                    {topFiveStats.map(stat => (
                      <tr key={stat.name}>
                        <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">{stat.name}</td>
                        <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">{stat.value}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div className="p-1 rounded-lg shadow-lg max-w-sm flex-1 mt-4 sm:mt-0 sm:ml-4">
            <div className="bg-white p-4 rounded-lg h-full">
              <h2 className="text-2xl font-bold mb-4 text-[#333333] text-center text-[#333333]">Fantasy Stats</h2>
              <div className="overflow-hidden">
                <table className="min-w-full divide-y divide-[#333333]-200 table-fixed">
                  <thead>
                    <tr>
                      <th className="px-4 py-2 text-left text-sm font-medium text-[#333333] uppercase tracking-wider">Stat</th>
                      <th className="px-4 py-2 text-left text-sm font-medium text-[#333333] uppercase tracking-wider">Value</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">NBA Fantasy Pts:</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">{player.nbaFantasyPts}</td>
                    </tr>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">Avg FanDuel Pts (Last 5)</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">{player.fanDuelPtsLast5}</td>
                    </tr>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">Avg NBA Fantasy Pts (Last 5)</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">{player.nbaFantasyPtsLast5}</td>
                    </tr>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">Avg FanDuel Pts (Season)</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">{player.fanDuelPtsSeason}</td>
                    </tr>
                    <tr>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">Avg NBA Fantasy Pts (Season)</td>
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-[#333333]">{player.nbaFantasyPtsSeason}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="mt-6 bg-[#00BFA6] border border-[#00BFA6] border-2 rounded-lg shadow-lg">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-2xl font-bold mb-4 text-[#333333]">Game Stats</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              <p><span className="font-semibold text-[#333333]">GP:</span> {player.gp}</p>
              <p><span className="font-semibold text-[#333333]">W:</span> {player.w}</p>
              <p><span className="font-semibold text-[#333333]">L:</span> {player.l}</p>
              <p><span className="font-semibold text-[#333333]">MIN:</span> {formatDecimal(player.min)}</p>
              <p><span className="font-semibold text-[#333333]">Win %:</span> {formatPercentage(player.w / player.gp)}</p>
              <p><span className="font-semibold text-[#333333]">Plus/Minus:</span> {player.plusMinus}</p>
            </div>
          </div>
        </div>

        <div className="mt-6 bg-[#00BFA6] border border-[#00BFA6] border-2 rounded-lg shadow-lg">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-2xl font-bold mb-4 text-[#333333]">Offensive Stats</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              <p><span className="font-semibold text-[#333333]">PTS:</span> {player.pts}</p>
              <p><span className="font-semibold text-[#333333]">PPG:</span> {player.ppg}</p>
              <p><span className="font-semibold text-[#333333]">AST:</span> {player.ast}</p>
              <p><span className="font-semibold text-[#333333]">APG:</span> {player.apg}</p>
              <p><span className="font-semibold text-[#333333]">REB:</span> {player.reb}</p>
              <p><span className="font-semibold text-[#333333]">RPG:</span> {player.rpg}</p>
              <p><span className="font-semibold text-[#333333]">OREB:</span> {player.oreb}</p>
              <p><span className="font-semibold text-[#333333]">DREB:</span> {player.dreb}</p>
              <p><span className="font-semibold text-[#333333]">FGM:</span> {player.fgm}</p>
              <p><span className="font-semibold text-[#333333]">FGA:</span> {player.fga}</p>
              <p><span className="font-semibold text-[#333333]">FG%:</span> {formatPercentage(player.fgPct)}</p>
              <p><span className="font-semibold text-[#333333]">3PM:</span> {player.fg3m}</p>
              <p><span className="font-semibold text-[#333333]">3PA:</span> {player.fg3a}</p>
              <p><span className="font-semibold text-[#333333]">3P%:</span> {formatPercentage(player.fg3m / player.fg3a)}</p>
              <p><span className="font-semibold text-[#333333]">FTM:</span> {player.ftm}</p>
              <p><span className="font-semibold text-[#333333]">FTA:</span> {player.fta}</p>
              <p><span className="font-semibold text-[#333333]">FT%:</span> {formatPercentage(player.ftPct)}</p>
              <p><span className="font-semibold text-[#333333]">Double-Doubles:</span> {player.dd2}</p>
              <p><span className="font-semibold text-[#333333]">Triple-Doubles:</span> {player.td3}</p>
            </div>
          </div>
        </div>

        <div className="mt-6 bg-[#00BFA6] border border-[#00BFA6] border-2 rounded-lg shadow-lg">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-2xl font-bold mb-4 text-[#333333]">Defensive Stats</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              <p><span className="font-semibold text-[#333333]">STL:</span> {player.stl}</p>
              <p><span className="font-semibold text-[#333333]">SPG:</span> {player.spg}</p>
              <p><span className="font-semibold text-[#333333]">BLK:</span> {player.blk}</p>
              <p><span className="font-semibold text-[#333333]">BPG:</span> {player.bpg}</p>
              <p><span className="font-semibold text-[#333333]">BLKA:</span> {player.blka}</p>
              <p><span className="font-semibold text-[#333333]">PF:</span> {player.pf}</p>
              <p><span className="font-semibold text-[#333333]">PFPG:</span> {player.pfpg}</p>
              <p><span className="font-semibold text-[#333333]">PFD:</span> {player.pfd}</p>
              <p><span className="font-semibold text-[#333333]">TOV:</span> {player.tov}</p>
              <p><span className="font-semibold text-[#333333]">TOVPG:</span> {player.topg}</p>
            </div>
          </div>
        </div>
        <div className="mt-6 bg-[#00BFA6] border border-[#00BFA6] border-2 rounded-lg shadow-lg">
              <div className="bg-white p-4 rounded-lg">
                <h3 className="text-2xl font-bold mb-4 text-[#333333]">Fantasy</h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                  <p><span className="font-semibold text-[#333333]">FanDuel Pts (Last 5):</span> {player.fanDuelPtsLast5}</p>
                  <p><span className="font-semibold text-[#333333]">NBA Fantasy Pts (Last 5):</span> {player.nbaFantasyPtsLast5}</p>
                  <p><span className="font-semibold text-[#333333]">Pts (Last 5):</span> {player.ptsLast5}</p>
                  <p><span className="font-semibold text-[#333333]">Reb (Last 5):</span> {player.rebLast5}</p>
                  <p><span className="font-semibold text-[#333333]">Ast (Last 5):</span> {player.astLast5}</p>
                  <p><span className="font-semibold text-[#333333]">3PM (Last 5):</span> {player.fg3mLast5}</p>
                  <p><span className="font-semibold text-[#333333]">FT% (Last 5):</span> {player.ftPctLast5}</p>
                  <p><span className="font-semibold text-[#333333]">Stl (Last 5):</span> {player.stlLast5}</p>
                  <p><span className="font-semibold text-[#333333]">Blk (Last 5):</span> {player.blkLast5}</p>
                  <p><span className="font-semibold text-[#333333]">TOV (Last 5):</span> {player.tovLast5}</p>
                  <p><span className="font-semibold text-[#333333]">FG% (Last 5):</span> {player.fgPctLast5}</p>
                  <p><span className="font-semibold text-[#333333]">FanDuel Pts (Season):</span> {player.fanDuelPtsSeason}</p>
                  <p><span className="font-semibold text-[#333333]">NBA Fantasy Pts (Season):</span> {player.nbaFantasyPtsSeason}</p>
                  <p><span className="font-semibold text-[#333333]">Pts (Season):</span> {player.ptsSeason}</p>
                  <p><span className="font-semibold text-[#333333]">Reb (Season):</span> {player.rebSeason}</p>
                  <p><span className="font-semibold text-[#333333]">Ast (Season):</span> {player.astSeason}</p>
                  <p><span className="font-semibold text-[#333333]">3PM (Season):</span> {player.fg3mSeason}</p>
                  <p><span className="font-semibold text-[#333333]">FG% (Season):</span> {player.fgPctSeason}</p>
                  <p><span className="font-semibold text-[#333333]">TOV (Season):</span> {player.tovSeason}</p>
                </div>
              </div>
            </div>

          <div className="mt-6 bg-[#00BFA6] border border-[#00BFA6] border-2 rounded-lg shadow-lg">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-2xl font-bold mb-4 text-[#333333]">Ranks</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              <p><span className="font-semibold text-[#333333]">GP Rank:</span> {player.gpRank}</p>
              <p><span className="font-semibold text-[#333333]">Wins Rank:</span> {player.wRank}</p>
              <p><span className="font-semibold text-[#333333]">Losses Rank:</span> {player.lRank}</p>
              <p><span className="font-semibold text-[#333333]">Win % Rank:</span> {player.wPctRank}</p>
              <p><span className="font-semibold text-[#333333]">Minutes Rank:</span> {player.minRank}</p>
              <p><span className="font-semibold text-[#333333]">Plus/Minus Rank:</span> {player.plusMinusRank}</p>
              <p><span className="font-semibold text-[#333333]">FGM Rank:</span> {player.fgmRank}</p>
              <p><span className="font-semibold text-[#333333]">FGA Rank:</span> {player.fgaRank}</p>
              <p><span className="font-semibold text-[#333333]">FG% Rank:</span> {player.fgPctRank}</p>
              <p><span className="font-semibold text-[#333333]">3PM Rank:</span> {player.fg3mRank}</p>
              <p><span className="font-semibold text-[#333333]">3PA Rank:</span> {player.fg3aRank}</p>
              <p><span className="font-semibold text-[#333333]">3P% Rank:</span> {player.fg3PctRank}</p>
              <p><span className="font-semibold text-[#333333]">FTM Rank:</span> {player.ftmRank}</p>
              <p><span className="font-semibold text-[#333333]">FTA Rank:</span> {player.ftaRank}</p>
              <p><span className="font-semibold text-[#333333]">FT% Rank:</span> {player.ftPctRank}</p>
              <p><span className="font-semibold text-[#333333]">OREB Rank:</span> {player.orebRank}</p>
              <p><span className="font-semibold text-[#333333]">DREB Rank:</span> {player.drebRank}</p>
              <p><span className="font-semibold text-[#333333]">REB Rank:</span> {player.rebRank}</p>
              <p><span className="font-semibold text-[#333333]">AST Rank:</span> {player.astRank}</p>
              <p><span className="font-semibold text-[#333333]">TOV Rank:</span> {player.tovRank}</p>
              <p><span className="font-semibold text-[#333333]">STL Rank:</span> {player.stlRank}</p>
              <p><span className="font-semibold text-[#333333]">BLK Rank:</span> {player.blkRank}</p>
              <p><span className="font-semibold text-[#333333]">BLKA Rank:</span> {player.blkaRank}</p>
              <p><span className="font-semibold text-[#333333]">PF Rank:</span> {player.pfRank}</p>
              <p><span className="font-semibold text-[#333333]">PFD Rank:</span> {player.pfdRank}</p>
              <p><span className="font-semibold text-[#333333]">PTS Rank:</span> {player.ptsRank}</p>
              <p><span className="font-semibold text-[#333333]">Double-Doubles Rank:</span> {player.dd2Rank}</p>
              <p><span className="font-semibold text-[#333333]">Triple-Doubles Rank:</span> {player.td3Rank}</p>
              <p><span className="font-semibold text-[#333333]">NBA Fantasy Pts Rank:</span> {player.nbaFantasyPtsRank}</p>
            </div>
          </div>
        </div>
      </div>
    );
};

export default PlayerProfilePage;
