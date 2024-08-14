'use client';

import React, { useEffect, useState } from 'react';
import Link from 'next/link';

interface Team {
  teamId: number;
  teamCity: string;
  teamName: string;
  teamSlug: string;
  logoUrl: string;
}

const TeamsPage: React.FC = () => {
  const [teams, setTeams] = useState<Team[]>([]);

  useEffect(() => {
    const fetchTeams = async () => {
      try {
        const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/standings`);
        const data = await response.json();
        const sortedTeams = data.sort((a: Team, b: Team) => a.teamCity.localeCompare(b.teamCity));
        setTeams(sortedTeams);
      } catch (error) {
        console.error('Failed to fetch teams:', error);
      }
    };

    fetchTeams();
  }, []);

  return (
    <div className="container mx-auto p-4 bg-[#f9f9f9]">
      <div className="title-container bg-white p-4 rounded-lg shadow-lg mb-8">
        <h1 className="text-4xl font-extrabold text-center text-[#333333]">Teams</h1>
      </div>
      <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-8">
        {teams.map((team) => (
          <Link key={team.teamId} href={`/teams/${team.teamId}`}>
            <div className="team-card relative cursor-pointer overflow-hidden rounded-lg shadow-lg">
              <img
                src={team.logoUrl || '/placeholder.png'}
                alt={team.teamName}
                className="w-full h-48 object-cover transition-transform transform hover:scale-105"
              />
              <div className="team-name absolute inset-0 bg-black bg-opacity-50 flex items-center justify-center text-white text-xl font-bold opacity-0 hover:opacity-100 transition-opacity">
                <span>{team.teamCity} {team.teamName}</span>
              </div>
            </div>
          </Link>
        ))}
      </div>

      <style jsx>{`
        .team-card {
          transition: all 0.3s ease;
        }
        .team-card:hover .team-name {
          opacity: 1;
        }
        .team-name {
          display: flex;
          align-items: center;
          justify-content: center;
          text-align: center;
        }
        .title-container {
          background-color: white;
          padding: 1rem;
          border-radius: 1rem;
          box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
          text-align: center;
        }
      `}</style>
    </div>
  );
};

export default TeamsPage;
