'use client';

import React, { useState } from 'react';
import Link from 'next/link';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import Image from 'next/image';

interface SearchResult {
    type: 'player' | 'team' | 'game';
    id: number;
    name: string;
    url: string;
}

interface Player {
    id: number;
    firstName: string;
    lastName: string;
}

interface Team {
    teamId: number;
    teamName: string;
}

interface Game {
    gameId: number;
    gameDateEst: string;
    teamAbbreviation: string;
}

const Header = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResults, setSearchResults] = useState<SearchResult[]>([]);
    const [isFocused, setIsFocused] = useState(false); 

    const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSearchTerm(event.target.value);
        if (event.target.value) {
            searchEverything(event.target.value).then(setSearchResults);
        } else {
            setSearchResults([]);
        }
    };

    const handleSearchSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        searchEverything(searchTerm).then(setSearchResults);
    };

    const handleInputFocus = () => {
        setIsFocused(true);
    };

    const handleInputBlur = () => {
        setTimeout(() => setIsFocused(false), 200);
    };

    async function searchEverything(query: string): Promise<SearchResult[]> {
        try {
            const [players, teams, games]: [Player[], Team[], Game[]] = await Promise.all([
                fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/search-players?query=${encodeURIComponent(query)}`).then(res => res.json()),
                fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/search-teams?query=${encodeURIComponent(query)}`).then(res => res.json()),
                fetch(`${process.env.NEXT_PUBLIC_API_URL}/games/search-linescores?query=${encodeURIComponent(query)}`).then(res => res.json())
            ]);

            const playerResults: SearchResult[] = players.map((player: any) => ({
                type: 'player',
                id: player.id,
                name: `${player.firstName} ${player.lastName}`,
                url: `/players/${player.id}`
            }));

            const teamResults: SearchResult[] = teams.map((team: any) => ({
                type: 'team',
                id: team.teamId,
                name: team.teamName,
                url: `/teams/${team.teamId}`
            }));

            const gameResults: SearchResult[] = games.map((game: any) => ({
                type: 'game',
                id: game.gameId,
                name: `${game.gameDateEst}: ${game.teamAbbreviation}`,
                url: `/games/${game.gameId}`
            }));

            return [...playerResults, ...teamResults, ...gameResults];
        } catch (error) {
            console.error('Error fetching search results:', error);
            return [];
        }
    }

    return (
        <div className="relative z-30 w-full border-b border-gray-200 bg-white/75 backdrop-blur-lg">
            <div className="flex h-[52px] items-center justify-between px-4">
                <div className="flex items-center space-x-4">
                    <Link href="/" className="flex flex-row space-x-3 items-center justify-center md:hidden">
                    <Image 
                        src="/major.png"  
                        alt="Logo"
                        width={48}
                        height={48}
                        className="h-12 w-12 rounded-lg" 
/>
                    </Link>
                </div>
                
                <form 
                    className="flex flex-1 justify-center relative"
                    onSubmit={handleSearchSubmit}
                >
                    <div className="relative w-full max-w-md border border-[#333333] rounded-full">
                        <input 
                            type="text"
                            placeholder="Search players, teams, games..."
                            value={searchTerm}
                            onChange={handleSearchChange}
                            onFocus={handleInputFocus}
                            onBlur={handleInputBlur}
                            className="w-full py-2 pl-10 pr-4 bg-gray-100 text-sm rounded-full border border-transparent focus:outline-none focus:bg-white focus:border-gray-300 focus:ring-0"
                        />
                        <div className="absolute left-3 top-1/2 transform -translate-y-1/2">
                            <FontAwesomeIcon icon={faSearch} className="text-gray-500 h-5 w-5" />
                        </div>

                        {isFocused && searchResults.length > 0 && (
                            <div className="absolute mt-1 w-full bg-white shadow-lg rounded-lg overflow-hidden z-50">
                                <ul>
                                    {searchResults.map(result => (
                                        <li key={result.id} className="p-2 hover:bg-gray-100 border-b border-gray-200">
                                            <Link href={result.url}>
                                                <span className="block text-sm text-gray-700">
                                                    {result.type === 'player' && `Player: ${result.name}`}
                                                    {result.type === 'team' && `Team: ${result.name}`}
                                                    {result.type === 'game' && `Game: ${result.name}`}
                                                </span>
                                            </Link>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                </form>

                <div className="hidden md:block">
                </div>
            </div>
        </div>
    );
};

export default Header;