// components/player-card.tsx
import React from 'react';
import Link from 'next/link';

type Player = {
  id: number;
  firstName: string;
  lastName: string;
  teamAbbreviation: string;
  ppg: number;
  apg: number;
  rpg: number;
  photoUrl?: string;
};

const PlayerCard = ({ player }: { player: Player }) => {
  return (
    <div className="bg-white shadow-md rounded-lg p-4">
      <img src={player.photoUrl || '/placeholder.png'} alt={`${player.firstName} ${player.lastName}`} className="w-full h-48 object-cover rounded-lg" />
      <div className="mt-4">
        <h2 className="text-xl font-bold">{player.firstName} {player.lastName}</h2>
        <p className="text-gray-700">Team: {player.teamAbbreviation}</p>
        <div className="mt-2">
          <p>PPG: {player.ppg}</p>
          <p>APG: {player.apg}</p>
          <p>RPG: {player.rpg}</p>
        </div>
        <Link href={`/players/${player.id}`}>
          <button className="mt-4 bg-gradient-to-r from-blue-500 to-purple-500 text-white py-2 px-4 rounded shadow-lg transition transform hover:scale-105">View Stats</button>
        </Link>
      </div>
    </div>
  );
};

export default PlayerCard;
