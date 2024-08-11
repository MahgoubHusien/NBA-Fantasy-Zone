import React from 'react';
import Link from 'next/link';

interface TeamCardProps {
  teamId: number;
  teamName: string;
  teamSlug: string;
  teamLogoUrl?: string; // Placeholder for the team's logo
}

const TeamCard: React.FC<TeamCardProps> = ({ teamId, teamName, teamSlug, teamLogoUrl }) => {
  return (
    <Link href={`/teams/${teamId}`}>
      <div className="border rounded-lg overflow-hidden shadow-lg cursor-pointer">
        <div className="relative h-40">
          <img
            src={teamLogoUrl || '/placeholder-logo.png'}
            alt={`${teamName} logo`}
            className="w-full h-full object-cover"
          />
          <div className="absolute inset-0 bg-black bg-opacity-50 flex items-center justify-center">
            <h2 className="text-white text-xl font-bold">{teamName}</h2>
          </div>
        </div>
      </div>
    </Link>
  );
};

export default TeamCard;
