'use client';

import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';

interface TeamStats {
  teamId: number;
  teamName: string;
  w: number;
  l: number;
  wPct: number;
  pts: number;
  // Add more fields as needed
}

const TeamStatsPage: React.FC = () => {
  const [teamStats, setTeamStats] = useState<TeamStats | null>(null);
  const router = useRouter();
  const { teamId } = router.query;

  const fetchTeamStats = async () => {
    try {
      const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/nba/team-stats/${teamId}`);
      const data = await response.json();
      setTeamStats(data);
    } catch (error) {
      console.error('Failed to fetch team stats:', error);
    }
  };

  useEffect(() => {
    if (teamId) {
      fetchTeamStats();
    }
  }, [teamId]);

  if (!teamStats) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-6">{teamStats.teamName} Stats</h1>
      <div className="grid grid-cols-2 gap-4">
        <div className="border rounded-lg p-4">
          <h2 className="text-xl font-semibold">Wins</h2>
          <p>{teamStats.w}</p>
        </div>
        <div className="border rounded-lg p-4">
          <h2 className="text-xl font-semibold">Losses</h2>
          <p>{teamStats.l}</p>
        </div>
        <div className="border rounded-lg p-4">
          <h2 className="text-xl font-semibold">Win Percentage</h2>
          <p>{teamStats.wPct}</p>
        </div>
        <div className="border rounded-lg p-4">
          <h2 className="text-xl font-semibold">Points per Game</h2>
          <p>{teamStats.pts}</p>
        </div>
        {/* Add more stats as needed */}
      </div>
    </div>
  );
};

export default TeamStatsPage;
