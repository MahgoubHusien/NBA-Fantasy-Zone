// app/players/page.tsx
'use client';

import React, { useEffect, useState } from 'react';
import PlayerCard from '@/components/player-card';

type CommonPlayerInfo = {
  id: number;
  firstName: string;
  lastName: string;
  teamAbbreviation: string;
  ppg: number;
  apg: number;
  rpg: number;
  photoUrl?: string;
};

const PlayersPage = () => {
  const [players, setPlayers] = useState<CommonPlayerInfo[]>([]);
  const [visiblePlayers, setVisiblePlayers] = useState<CommonPlayerInfo[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchPlayers = async () => {
      try {
        const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/commonPlayerInfo`);
        if (!response.ok) {
          throw new Error(`Error: ${response.statusText}`);
        }
        const data = await response.json();
        console.log('Fetched players:', data); // Debug log
        setPlayers(data);
        setVisiblePlayers(data.slice(0, 15)); // Show the first 15 players initially
      } catch (error: any) {
        console.error('Failed to fetch players:', error);
        setError('Failed to fetch players. Please try again later.');
      } finally {
        setLoading(false);
      }
    };

    fetchPlayers();
  }, []);

  const showMorePlayers = () => {
    setVisiblePlayers(players.slice(0, visiblePlayers.length + 15));
  };

  return (
    <div className="container mx-auto p-4">
      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p className="text-red-500">{error}</p>
      ) : (
        <>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
            {visiblePlayers.length > 0 ? (
              visiblePlayers.map((player, index) => (
                <PlayerCard key={index} player={player} />
              ))
            ) : (
              <p>No players found.</p>
            )}
          </div>
          {visiblePlayers.length < players.length && (
            <div className="flex justify-center mt-4">
              <button
                onClick={showMorePlayers}
                className="bg-gradient-to-r from-blue-500 to-purple-500 text-white py-2 px-4 rounded shadow-lg transition transform hover:scale-105"
              >
                Show More
              </button>
            </div>
          )}
        </>
      )}
    </div>
  );
};

export default PlayersPage;
