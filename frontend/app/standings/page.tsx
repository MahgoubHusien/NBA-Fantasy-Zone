"use client";

import React, { useState, useEffect } from 'react';
import Link from 'next/link';

interface TeamStandings {
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

const StandingsPage: React.FC = () => {
  const [activeTab, setActiveTab] = useState<'east' | 'west' | 'league'>('east');
  const [easternStandings, setEasternStandings] = useState<TeamStandings[]>([]);
  const [westernStandings, setWesternStandings] = useState<TeamStandings[]>([]);
  const [leagueStandings, setLeagueStandings] = useState<TeamStandings[]>([]);

  useEffect(() => {
    const fetchStandings = async () => {
      try {
        const eastResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/standings/east`);
        const westResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/standings/west`);
        const leagueResponse = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/standings/league`);
        
        const easternData = await eastResponse.json();
        const westernData = await westResponse.json();
        const leagueData = await leagueResponse.json();

        console.log('Eastern Standings:', easternData);
        console.log('Western Standings:', westernData);
        console.log('League Standings:', leagueData);

        if (Array.isArray(easternData)) setEasternStandings(easternData);
        if (Array.isArray(westernData)) setWesternStandings(westernData);
        if (Array.isArray(leagueData)) setLeagueStandings(leagueData);
      } catch (error) {
        console.error('Failed to fetch standings:', error);
      }
    };

    fetchStandings();
  }, []);

  const renderStandingsTable = (standings: TeamStandings[]) => {
    if (!Array.isArray(standings)) {
      return <p>Error: Standings data is not available.</p>;
    }

    return (
      <div className="overflow-x-auto scrollbar-thin scrollbar-thumb-rounded scrollbar-thumb-gray-400">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Rank</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Team</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Record</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Clinch Indicator</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">GB</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Win%</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Conference Record</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Division</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Division Record</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Home</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Road</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">L10</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Streak</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Pts/G</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Opp Pts/G</th>
              <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Diff Pts/G</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
  {standings.map((team, index) => (
    <React.Fragment key={team.teamId}>
      {activeTab !== 'league' && index === 6 && (
        <tr>
          <td colSpan={15} className="relative">
            <div className="absolute top-0 w-full border-t-2 border-dashed border-gray-400"></div>
          </td>
        </tr>
      )}
      {activeTab !== 'league' && index === 10 && (
        <tr>
          <td colSpan={15} className="relative">
            <div className="absolute top-0 w-full border-t-4 border-gray-700"></div>
          </td>
        </tr>
      )}
      <tr>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm font-medium text-gray-900">{index + 1}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm font-medium text-gray-900">
          <Link href={`/teams/${team.teamId}`} className="text-[#333333]-600 hover:text-[#333333]-800 flex items-center justify-center">
            <img src={team.logoUrl} alt={`${team.teamName} Logo`} className="w-6 h-6 mr-2" />
            <span>{team.teamName}</span>
          </Link>
        </td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.wins}-{team.losses}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.clinchIndicator}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.conferenceGamesBack}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.winPct.toFixed(3)}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.conferenceRecord}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.division}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.divisionRecord}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.home}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.road}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.lastTen}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.currentStreak}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.pointsPerGame.toFixed(1)}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.opponentPointsPerGame.toFixed(1)}</td>
        <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{team.differencePointsPerGame.toFixed(1)}</td>
      </tr>
    </React.Fragment>
  ))}
</tbody>



        </table>
        
        <div className="mt-6 text-sm text-gray-600">
          <p><strong>Legend:</strong></p>
          <ul className="mt-3 mb-3 flex flex-wrap gap-4 list-none">
            <li><strong>- x:</strong> Clinched Playoff Berth</li>
            <li><strong>- o:</strong> Eliminated from Playoffs</li>
            <li><strong>- pi:</strong> Clinched Play-in Berth</li>
            <li><strong>- sw:</strong> Clinched Southwest Division</li>
            <li><strong>- se:</strong> Clinched Southeast Division</li>
            <li><strong>- e:</strong> Clinched East Conference</li>
            <li><strong>- p:</strong> Clinched Pacific Division</li>
            <li><strong>- c:</strong> Clinched Central Division</li>
            <li><strong>- w:</strong> Clinched West Conference</li>
          </ul>
        </div>
      </div>
    );
  }
    
  return (
    <div className="container mx-auto p-4 max-w-4xl">
      <div className="bg-white p-6 rounded-lg shadow-lg mb-6 border-2 border-[#333333]">
        <div className="flex flex-wrap justify-center mb-4">
          <button
            className={`px-4 py-2 m-2 font-bold text-lg ${activeTab === 'league' ? 'text-[#00BFA6] border-b-4 border-[#00BFA6]' : 'text-[#333333]-500'}`}
            onClick={() => setActiveTab('league')}
          >
            League
          </button>
          <button
            className={`px-4 py-2 m-2 font-bold text-lg ${activeTab === 'east' ? 'text-[#00BFA6] border-b-4 border-[#00BFA6]' : 'text-[#333333]-500'}`}
            onClick={() => setActiveTab('east')}
          >
            Eastern Conference
          </button>
          <button
            className={`px-4 py-2 m-2 font-bold text-lg ${activeTab === 'west' ? 'text-[#00BFA6] border-b-4 border-[#00BFA6]' : 'text-[#333333]-500'}`}
            onClick={() => setActiveTab('west')}
          >
            Western Conference
          </button>
        </div>
        {activeTab === 'league' && (
          <div className="bg-white p-6 rounded-lg shadow-lg border border-gold">
            {renderStandingsTable(leagueStandings)}
          </div>
        )}
        {activeTab === 'east' && (
          <div className="bg-white p-6 rounded-lg shadow-lg border border-gold">
            {renderStandingsTable(easternStandings)}
          </div>
        )}
        {activeTab === 'west' && (
          <div className="bg-white p-6 rounded-lg shadow-lg border border-gold">
            {renderStandingsTable(westernStandings)}
          </div>
        )}
      </div>
    </div>
  );
};


export default StandingsPage;
