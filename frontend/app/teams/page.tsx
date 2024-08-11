'use client';

import React, { useState, useEffect } from 'react';
import TeamCard from '@/components/team-card';

interface Team {
  teamId: number;
  teamName: string;
  teamSlug: string;
  teamLogoUrl?: string; // Placeholder for the team's logo
}

const TeamsPage: React.FC = () => {
  const [teams, setTeams] = useState<Team[]>([]);

  const fetchTeams = async () => {
    try {
      const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/nba/standings`);
      const data = await response.json();
      setTeams(data);
    } catch (error) {
      console.error('Failed to fetch teams:', error);
    }
  };

  useEffect(() => {
    fetchTeams();
  }, []);

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-6">NBA Teams</h1>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        {teams.map((team) => (
          <TeamCard
            key={team.teamId}
            teamId={team.teamId}
            teamName={team.teamName}
            teamSlug={team.teamSlug}
            teamLogoUrl={team.teamLogoUrl}
          />
        ))}
      </div>
    </div>
  );
};

export default TeamsPage;
