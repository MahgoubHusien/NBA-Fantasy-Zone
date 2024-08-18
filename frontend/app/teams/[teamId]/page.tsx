'use client';

import React, { useEffect, useState, useCallback} from 'react';
import { useParams } from 'next/navigation';
import Image from 'next/image';

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

  const fetchTeamData = useCallback(async () => {
    try {
      const [statsResponse, metricsResponse, standingsResponse, playersResponse] = await Promise.all([
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/team-stats/${teamId}`),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/team-estimated-metrics/${teamId}`),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/standings/${teamId}`),
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/team/${teamId}`),
      ]);

      const [statsData, metricsData, standingsData, playersData] = await Promise.all([
        statsResponse.json(),
        metricsResponse.json(),
        standingsResponse.json(),
        playersResponse.json(),
      ]);

      setTeamStats(statsData);
      setTeamMetrics(metricsData);
      setStanding(standingsData);
      setPlayers(playersData);
    } catch (error) {
      console.error('Failed to fetch team data:', error);
    }
  }, [teamId]);

  useEffect(() => {
    if (teamId) {
      fetchTeamData();
    }
  }, [teamId, fetchTeamData]);

  if (!teamStats || !teamMetrics || !standing || !players) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mx-auto p-4 max-w-4xl bg-[#f9f9f9]">
      <div className="bg-white p-6 rounded-lg shadow-lg mb-6 border-solid2">
        <div className="flex items-center justify-center mb-4 text-[#333333]">
        <Image
          src={standing.logoUrl || '/placeholder.png'}
          alt={`${standing.teamName} Logo`}
          width={160} 
          height={160} 
          className="w-40 h-40 mr-5 rounded-full"
        />          
        <div>
            <h1 className="text-4xl font-extrabold text-[#333333] mb-2">{standing.teamCity} {standing.teamName}</h1>
            <p className="text-xl text-[#333333]">{standing.conference} Conference</p>
            <p className="text-xl text-[#333333]">Record: {standing.wins} - {standing.losses}</p>
            <p className="text-xl text-[#333333]">Conference Record: {standing.conferenceRecord}</p>
            <p className="text-xl text-[#333333]">Division: {standing.division} ({standing.divisionRecord})</p>
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
              <table className="min-w-full divide-y divide-gray-200">
                <thead>
                  <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Stat</th>
                    <th className="px-4 py-3 text-right text-xs font-medium text-[#333333] uppercase tracking-wider">Value</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Field Goals Made</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalsMade}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Field Goals Attempted</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalsAttempted}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Field Goal Percentage</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalPct}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Three Pointers Made</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointersMade}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Three Pointers Attempted</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointersAttempted}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Three Point Percentage</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointPct}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Free Throws Made</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowsMade}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Free Throws Attempted</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowsAttempted}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Free Throw Percentage</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowPct}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Offensive Rebounds</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.offensiveRebounds}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Defensive Rebounds</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.defensiveRebounds}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Total Rebounds</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.totalRebounds}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Assists</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.assists}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Turnovers</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.turnovers}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Steals</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.steals}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Blocks</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.blocks}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Personal Fouls</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.personalFouls}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Personal Fouls Drawn</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.personalFoulsDrawn}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Points</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.points}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Plus Minus</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.plusMinus}</td></tr>
                </tbody>
              </table>
            </div>
          </div>

          <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
            <div className="overflow-x-auto">
              <h2 className="text-3xl font-bold text-center mb-4 text-[#333333]">NBA Ranks</h2>
              <table className="min-w-full divide-y divide-gray-200">
                <thead>
                  <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Rank Category</th>
                    <th className="px-4 py-3 text-right text-xs font-medium text-[#333333] uppercase tracking-wider">Rank</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Games Played Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.gpRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Wins Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.winRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Losses Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.lossRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Win Percentage Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.winPctRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Minutes Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.minRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Field Goals Made Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalsMadeRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Field Goals Attempted Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalsAttemptedRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Field Goal Percentage Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.fieldGoalPctRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Three Pointers Made Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointersMadeRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Three Pointers Attempted Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointersAttemptedRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Three Point Percentage Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.threePointPctRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Free Throws Made Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowsMadeRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Free Throws Attempted Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowsMadeRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Free Throw Percentage Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.freeThrowPctRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Offensive Rebounds Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.offensiveReboundsRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Defensive Rebounds Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.defensiveReboundsRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Total Rebounds Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.totalReboundsRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Assists Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.assistsRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Turnovers Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.turnoversRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Steals Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.stealsRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Blocks Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.blocksRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Blocks Against Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.blocksAgainstRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Personal Fouls Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.personalFoulsRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Personal Fouls Drawn Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.personalFoulsDrawnRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Points Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.pointsRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Plus Minus Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.plusMinusRank}</td></tr>
                </tbody>
              </table>
            </div>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
            <div className="overflow-x-auto">
              <h2 className="text-3xl font-bold text-center mb-4 text-[#333333]">NBA Stats</h2>
              <table className="min-w-full divide-y divide-gray-200">
                <thead>
                  <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Metric</th>
                    <th className="px-4 py-3 text-right text-xs font-medium text-[#333333] uppercase tracking-wider">Value</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Games Played</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.gp}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Wins</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.wins}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Losses</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.losses}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Win Percentage</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamStats.winPct}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Record</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.record}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Home Record</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.home}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Road Record</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.road}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Last 10 Games</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.lastTen}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Longest Win Streak</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.longWinStreak}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Longest Loss Streak</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.longLossStreak}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Current Streak</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.currentStreak}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Conference Games Back</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.conferenceGamesBack}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Clinched Conference Title</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.clinchedConferenceTitle ? 'True' : 'False'}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Clinched Playoff Birth</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.clinchedPlayoffBirth ? 'True' : 'False'}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Clinched Play-In</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.clinchedPlayin ? 'True' : 'False'}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Eliminated from Conference</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.eliminatedConference ? 'True' : 'False'}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Points Per Game</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.pointsPerGame}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Opponent Points Per Game</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.opponentPointsPerGame}</td></tr>
                  <tr><td className="py-2 px-4 whitespace-nowrap text-sm font-medium text-[#333333]">Difference Points Per Game</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{standing.differencePointsPerGame}</td></tr>
                </tbody>
              </table>
            </div>
          </div>

          <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
            <div className="overflow-x-auto">
              <h2 className="text-3xl font-bold text-center mb-4 text-[#333333]">Team Estimated Metrics</h2>
              <table className="min-w-full divide-y divide-gray-200">
                <thead>
                  <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-[#333333] uppercase tracking-wider">Metric</th>
                    <th className="px-4 py-3 text-right text-xs font-medium text-[#333333] uppercase tracking-wider">Value</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Offensive Rating</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedOffensiveRating}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Defensive Rating</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedDefensiveRating}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Net Rating</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedNetRating}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Pace</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedPace}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Assist Ratio</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedAssistRatio}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Offensive Rebound Percentage</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedOffensiveReboundPct}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Defensive Rebound Percentage</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedDefensiveReboundPct}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Rebound Percentage</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedReboundPct}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Turnover Percentage</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedTurnoverPct}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Offensive Rating Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedOffensiveRatingRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Defensive Rating Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedDefensiveRatingRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Net Rating Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedNetRatingRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Assist Ratio Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedAssistRatioRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Offensive Rebound Percentage Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedOffensiveReboundPctRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Defensive Rebound Percentage Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedDefensiveReboundPctRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Rebound Percentage Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedReboundPctRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Turnover Percentage Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedTurnoverPctRank}</td></tr>
                  <tr><td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">Estimated Pace Rank</td><td className="px-4 py-4 whitespace-nowrap text-sm text-[#333333] text-right">{teamMetrics.estimatedPaceRank}</td></tr>
                </tbody>
              </table>
              </div>
          </div>
        </div>
        ) : (
          <div>
            <div className="bg-white p-6 rounded-lg shadow-lg mb-8 border border-solid">
              <h2 className="text-3xl font-bold text-center mb-4 text-[#333333]">Team Roster</h2>
              <div className="overflow-x-auto scrollbar-thin scrollbar-thumb-rounded scrollbar-thumb-gray-400">
                <table className="min-w-full divide-y divide-gray-200">
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
                        <td className="px-4 py-4 whitespace-nowrap text-sm font-medium text-[#333333]">{player.firstName} {player.lastName}</td>
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
    </div>
  );
};

export default TeamStatsPage;
