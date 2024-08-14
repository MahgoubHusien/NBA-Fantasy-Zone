'use client';

import React, { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';

interface TeamStats {
  assists: number;
  assistsRank: number;
  blocks: number;
  blocksRank: number;
  blocksAgainst: number;
  blocksAgainstRank: number;
  defensiveRebounds: number;
  defensiveReboundsRank: number;
  fieldGoalPct: number;
  fieldGoalPctRank: number;
  fieldGoalsAttempted: number;
  fieldGoalsAttemptedRank: number;
  fieldGoalsMade: number;
  fieldGoalsMadeRank: number;
  freeThrowPct: number;
  freeThrowPctRank: number;
  freeThrowsAttempted: number;
  freeThrowsAttemptedRank: number;
  freeThrowsMade: number;
  freeThrowsMadeRank: number;
  gp: number;
  gpRank: number;
  logoUrl: string;
  lossRank: number;
  losses: number;
  minRank: number;
  minutes: number;
  offensiveRebounds: number;
  offensiveReboundsRank: number;
  personalFouls: number;
  personalFoulsDrawn: number;
  personalFoulsDrawnRank: number;
  personalFoulsRank: number;
  plusMinus: number;
  plusMinusRank: number;
  points: number;
  pointsRank: number;
  steals: number;
  stealsRank: number;
  teamId: number;
  teamName: string;
  threePointPct: number;
  threePointPctRank: number;
  threePointersAttempted: number;
  threePointersAttemptedRank: number;
  threePointersMade: number;
  threePointersMadeRank: number;
  totalRebounds: number;
  totalReboundsRank: number;
  turnovers: number;
  turnoversRank: number;
  winPct: number;
  winPctRank: number;
  winRank: number;
  wins: number;
  coachName: string;
}


interface TeamEstimatedMetrics {
  teamId: number;
  teamName: string;
  gp: number;
  wins: number;
  losses: number;
  winPct: number;
  min: number;
  estimatedOffensiveRating: number;
  estimatedDefensiveRating: number;
  estimatedNetRating: number;
  estimatedPace: number;
  estimatedAssistRatio: number;
  estimatedOffensiveReboundPct: number;
  estimatedDefensiveReboundPct: number;
  estimatedReboundPct: number;
  estimatedTurnoverPct: number;
  estimatedOffensiveRatingRank: number;
  estimatedDefensiveRatingRank: number;
  estimatedNetRatingRank: number;
  estimatedAssistRatioRank: number;
  estimatedOffensiveReboundPctRank: number;
  estimatedDefensiveReboundPctRank: number;
  estimatedReboundPctRank: number;
  estimatedTurnoverPctRank: number;
  estimatedPaceRank: number;
}

interface Standings {
  teamId: number;
  leagueId: string;
  seasonId: string;
  teamCity: string;
  teamName: string;
  teamSlug: string;
  conference: string;
  conferenceRecord: string;
  playoffRank: number;
  clinchIndicator: string;
  division: string;
  divisionRecord: string;
  divisionRank: number;
  wins: number;
  losses: number;
  winPct: number;
  leagueRank: number;
  record: string;
  home: string;
  road: string;
  lastTen: string;
  longWinStreak: number;
  longLossStreak: number;
  currentStreak: string;
  conferenceGamesBack: number;
  clinchedConferenceTitle: number;
  clinchedPlayoffBirth: number;
  clinchedPlayin: number;
  eliminatedConference: number;
  pointsPerGame: number;
  opponentPointsPerGame: number;
  differencePointsPerGame: number;
  logoUrl: string;
}

interface Player {
  id: number;
  playerId: number;
  teamAbbreviation: string;
  teamId: number;
  ppg: number;
  apg: number;
  rpg: number;
  spg: number;
  topg: number;
  bpg: number;
  pfpg: number;
  firstName: string;
  lastName: string;
  weight: number;
  teamName: string;
  jersey: number;
  position: string;
  height: string;
}

const TeamStatsPage: React.FC = () => {
  const { teamId } = useParams();
  const [teamStats, setTeamStats] = useState<TeamStats | null>(null);
  const [teamMetrics, setTeamMetrics] = useState<TeamEstimatedMetrics | null>(null);
  const [standing, setStanding] = useState<Standings | null>(null);
  const [players, setPlayers] = useState<Player[]>([]);
  const [activeTab, setActiveTab] = useState<'stats' | 'roster'>('stats');

  useEffect(() => {
    const fetchTeamData = async () => {
      try {
        const statsResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/team-stats/${teamId}`);
        const statsData: TeamStats = await statsResponse.json();
        setTeamStats(statsData);

        const metricsResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/team-estimated-metrics/${teamId}`);
        const metricsData: TeamEstimatedMetrics = await metricsResponse.json();
        setTeamMetrics(metricsData);

        const standingsResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/standings/${teamId}`);
        const standingsData: Standings = await standingsResponse.json();
        setStanding(standingsData);

        const playersResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/team/${teamId}`);
        const playersData = await playersResponse.json();
        setPlayers(playersData);

      } catch (error) {
        console.error('Failed to fetch team data:', error);
      }
    };

    if (teamId) {
      fetchTeamData();
    }
  }, [teamId]);

  if (!teamStats || !teamMetrics || !standing || !players) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mx-auto p-4 max-w-4xl bg-[#f9f9f9]">
      <div className="bg-white p-6 rounded-lg shadow-lg mb-6 border-solid2">
        <div className="flex items-center justify-center mb-4 text-[#333333]">
          <img src={standing.logoUrl || '/placeholder.png'} alt={`${standing.teamName} Logo`} className="w-40 h-40 mr-5 rounded-full" />
          <div>
            <h1 className="text-4xl font-extrabold text-[#333333] mb-2">{standing.teamCity} {standing.teamName}</h1>
            <p className="text-xl text-[#333333]">{standing.conference} Conference</p>
            <p className="text-xl text-[#333333]">Record: {standing.wins} - {standing.losses}</p>
            <p className="text-xl text-[#333333]">Conference Record: {standing.conferenceRecord}</p>
            <p className="text-xl text-[#333333]">Division: {standing.division} ( {standing.divisionRecord})</p>
            <p className="text-xl text-[#333333]">Coach: {teamStats.coachName}</p>
            <p className="text-xl text-[#333333]">
              Current Streak: {standing.currentStreak.startsWith('-') 
                ? `${standing.currentStreak.substring(1)} Losing Streak` 
                : `${standing.currentStreak} Winning Streak`}
            </p>
          </div>
        </div>
      </div>
      <div className="flex justify-center mb-4">
        <button
          className={`px-4 py-2 font-bold text-lg ${activeTab === 'stats' ? 'text-[#00BFA6] border-b-4 border-[#00BFA6]' : 'text-[#333333]'}`}
          onClick={() => setActiveTab('stats')}
        >
          Stats
        </button>
        <button
          className={`ml-8 px-4 py-2 font-bold text-lg ${activeTab === 'roster' ? 'text-[#00BFA6] border-b-4 border-[#00BFA6]' : 'text-[#333333]'}`}
          onClick={() => setActiveTab('roster')}
        >
          Roster
        </button>
      </div>

      {activeTab === 'stats' ? (
        <div>
          <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
            <div className="overflow-x-auto">
              <h2 className="text-3xl font-bold text-center mb-4 text-[#333333]">Team Stats</h2>
              <table className="min-w-full divide-y divide-gray-200" style={{ minWidth: '900px' }}>
                <thead>
                  <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Stat</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Value</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  <tr>
                    <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Field Goals Made</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalsMade}</td>
                  </tr>
                  <tr>
                    <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Field Goals Attempted</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalsAttempted}</td>
                  </tr>
                  <tr>
                    <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Field Goal Percentage</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalPct}</td>
                  </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Three Pointers Made</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointersMade}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Three Pointers Attempted</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointersAttempted}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Three Point Percentage</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointPct}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Free Throws Made</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowsMade}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Free Throws Attempted</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowsAttempted}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Free Throw Percentage</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowPct}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Offensive Rebounds</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.offensiveRebounds}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Defensive Rebounds</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.defensiveRebounds}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Total Rebounds</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.totalRebounds}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Assists</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.assists}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Turnovers</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.turnovers}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Steals</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.steals}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Blocks</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.blocks}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Personal Fouls</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.personalFouls}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Personal Fouls Drawn</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.personalFoulsDrawn}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Points</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.points}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Plus Minus</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.plusMinus}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>

          <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
            <div className="overflow-x-auto">
              <h2 className="text-3xl font-bold text-center mb-4 text-[#333333]">NBA Ranks</h2>
              <table className="min-w-full divide-y divide-gray-200" style={{ minWidth: '900px' }}>
                <thead>
                  <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Rank Category</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Rank</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  <tr>
                    <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Games Played Rank</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.gpRank}</td>
                  </tr>
                  <tr>
                    <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Wins Rank</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.winRank}</td>
                  </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Losses Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.lossRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Win Percentage Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.winPctRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Minutes Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.minRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Field Goals Made Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalsMadeRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Field Goals Attempted Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalsAttemptedRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Field Goal Percentage Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalPctRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Three Pointers Made Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointersMadeRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Three Pointers Attempted Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointersAttemptedRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Three Point Percentage Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointPctRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Free Throws Made Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowsMadeRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Free Throws Attempted Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowsMadeRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Free Throw Percentage Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowPctRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Offensive Rebounds Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.offensiveReboundsRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Defensive Rebounds Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.defensiveReboundsRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Total Rebounds Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.totalReboundsRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Assists Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.assistsRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Turnovers Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.turnoversRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Steals Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.stealsRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Blocks Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.blocksRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Blocks Against Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.blocksAgainstRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Personal Fouls Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.personalFoulsRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Personal Fouls Drawn Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.personalFoulsDrawnRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Points Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.pointsRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Plus Minus Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.plusMinusRank}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
            <h2 className="text-3xl font-bold text-center mb-4 text-[#333333]">NBA Stats</h2>
            <div className="overflow-x-auto">
              <table className="min-w-full divide-y divide-gray-200" style={{ minWidth: '900px' }}>
                <thead>
                  <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Metric</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Value</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Games Played</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.gp}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Wins</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.wins}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Losses</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.losses}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Win Percentage</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.winPct}</td>
                  </tr>
                  {/* <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">League Rank</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.leagueRank}</td>
                  </tr> */}
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Record</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.record}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Home Record</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.home}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Road Record</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.road}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Last 10 Games</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.lastTen}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Longest Win Streak</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.longWinStreak}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Longest Loss Streak</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.longLossStreak}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Current Streak</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.currentStreak}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Conference Games Back</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.conferenceGamesBack}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Clinched Conference Title</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.clinchedConferenceTitle ? 'True' : 'False'}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Clinched Playoff Birth</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.clinchedPlayoffBirth ? 'True' : 'False'}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Clinched Play-In</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.clinchedPlayin ? 'True' : 'False'}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Eliminated from Conference</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.eliminatedConference ? 'True' : 'False'}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Points Per Game</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.pointsPerGame}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Opponent Points Per Game</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.opponentPointsPerGame}</td>
                  </tr>
                  <tr>
                    <td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-gray-900">Difference Points Per Game</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.differencePointsPerGame}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
            <div className="overflow-x-auto">
              <h2 className="text-3xl font-bold text-center mb-4 text-[#333333]">Team Estimated Metrics</h2>
              <table className="min-w-full divide-y divide-gray-200" style={{ minWidth: '900px' }}>
                <thead>
                  <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Metric</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Value</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  <tr>
                    <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Offensive Rating</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedOffensiveRating}</td>
                  </tr>
                  <tr>
                    <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Defensive Rating</td>
                    <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedDefensiveRating}</td>
                  </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Net Rating</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedNetRating}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Pace</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedPace}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Assist Ratio</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedAssistRatio}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Offensive Rebound Percentage</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedOffensiveReboundPct}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Defensive Rebound Percentage</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedDefensiveReboundPct}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Rebound Percentage</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedReboundPct}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Turnover Percentage</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedTurnoverPct}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Offensive Rating Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedOffensiveRatingRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Defensive Rating Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedDefensiveRatingRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Net Rating Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedNetRatingRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Assist Ratio Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedAssistRatioRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Offensive Rebound Percentage Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedOffensiveReboundPctRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Defensive Rebound Percentage Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedDefensiveReboundPctRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Rebound Percentage Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedReboundPctRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Turnover Percentage Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedTurnoverPctRank}</td>
                </tr>
                <tr>
                  <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-gray-900">Estimated Pace Rank</td>
                  <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedPaceRank}</td>
                </tr>
                </tbody>
              </table>
              </div>
          </div>
        </div>
        ) : (
          <div>
            <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
              <h2 className="text-3xl font-bold text-center mb-4 text-[#333333] text-[#333333]">Team Roster</h2>
              <div className="overflow-x-auto scrollbar-thin scrollbar-thumb-rounded scrollbar-thumb-gray-400">
                <table className="min-w-full divide-y divide-gray-200" style={{ minWidth: '900px' }}>
                  <thead className="bg-gray-50">
                    <tr>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Player</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Position</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Height</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Weight</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Jersey</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">PPG</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">APG</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">RPG</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">SPG</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">BPG</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">TOPG</th>
                      <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">PFPG</th>
                    </tr>
                  </thead>
                  <tbody className="bg-white divide-y divide-gray-200">
                    {players.map((player) => (
                      <tr key={player.playerId}>
                        <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">
                          {player.firstName} {player.lastName}
                        </td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.position}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.height}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.weight}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.jersey}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.ppg}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.apg}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.rpg}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.spg}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.bpg}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.topg}</td>
                        <td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333]">{player.pfpg}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        )}

      

      <style jsx>{`
        .container {
          padding: 2rem;
          max-width: 64rem;
          margin: auto;
        }
        .bg-white {
          background-color: white;
          padding: 1.5rem;
          border-radius: 0.5rem;
          box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
          margin-bottom: 1.5rem;
        }
        .border-solid{
          border: 2px solid #00BFA6;
        }
        .border-solid2{
          border: 2px solid #333333;
        }
        .overflow-x-auto {
          overflow-x: auto;
        }
        table {
          width: 100%;
          min-width: 900px;
          border-collapse: collapse;
        }
        th, td {
          padding: 1rem;
          font-size: 0.875rem;
          text-align: left;
        }
        th {
          font-weight: 500;
          color: #6B7280; 
          text-transform: uppercase;
          letter-spacing: 0.05em;
        }
        td {
          color: #6B7280; 
        }
        tbody {
          background-color: white;
          border-top: 1px solid #E5E7EB; 
        }
      `}</style>
    </div>
  );
};

export default TeamStatsPage;
