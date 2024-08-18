'use client';

import React, { useEffect, useState, useMemo } from 'react';
import { useParams } from 'next/navigation';
import Link from 'next/link';
import Image from 'next/image';

interface GameHeader {
    gameId: string;
    gameDateEst: string;
    gameSequence: number;
    gameStatusId: number;
    gameStatusText: string;
    homeTeamId: number;
    visitorTeamId: number;
    season: string;
    livePeriod: number;
    livePcTime: string;
    natlTvBroadcasterAbbreviation: string;
    homeTvBroadcasterAbbreviation: string;
    awayTvBroadcasterAbbreviation: string;
    livePeriodTimeBcast: string;
    arenaName: string;
    whStatus: number;
    wnbaCommissionerFlag: string;
}

interface LineScore {
    id: number;
    gameId: string;
    gameDateEst: string;
    gameSequence: number;
    teamId: number;
    teamAbbreviation: string;
    teamCityName: string;
    teamName: string;
    teamWinsLosses: string;
    ptsQtr1: number;
    ptsQtr2: number;
    ptsQtr3: number;
    ptsQtr4: number;
    ptsOt1: number;
    ptsOt2: number;
    ptsOt3: number;
    ptsOt4: number;
    ptsOt5: number;
    ptsOt6: number;
    ptsOt7: number;
    ptsOt8: number;
    ptsOt9: number;
    ptsOt10: number;
    pts: number;
    fgPct: number;
    ftPct: number;
    fg3Pct: number;
    ast: number;
    reb: number;
    tov: number;
}


interface TeamStats {
    teamId: number;
    teamName: string;
    teamAbbreviation: string;
    logoUrl: string;
    wins: number;
    losses: number;
}

interface PlayerBoxStats {
    id: number;
    gameId: string;
    playerId: number;
    playerName: string;
    teamId: number;
    min: string;
    fgm: number;
    fga: number;
    fgPct: number;
    fg3m: number;
    fg3a: number;
    fg3Pct: number;
    ftm: number;
    fta: number;
    ftPct: number;
    oreb: number;
    dreb: number;
    reb: number;
    ast: number;
    stl: number;
    blk: number;
    tov: number;
    pf: number;
    pts: number;
    plusMinus: number;
    position?: string;
    jersey?: string;
    commonPlayerId?: number; 

}

interface CommonPlayerInfo {
    id: number;
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
    weight: string;
    teamName: string;
    jersey: string;
    position: string;
    height: string;
}

const getTeamLogo = (teamId: number, teamStats: TeamStats[]) => {
  const team = teamStats.find((team) => team.teamId === teamId);
  return team ? team.logoUrl : '';
};

const getTeamAbbreviation = (teamId: number, gameId: string, lineScores: LineScore[]) => {
  const lineScore = lineScores.find((score) => score.teamId === teamId && score.gameId === gameId);
  return lineScore ? lineScore.teamAbbreviation : '';
};

const getTeamRecord = (teamId: number, gameId: string, lineScores: LineScore[]) => {
  const lineScore = lineScores.find((score) => score.teamId === teamId && score.gameId === gameId);
  return lineScore ? lineScore.teamWinsLosses : '0-0';
};

const getScoreClass = (teamScore: number, opponentScore: number) => {
  return teamScore > opponentScore
    ? 'text-4xl font-bold text-white bg-[#00BFA6] rounded-lg px-2'
    : 'text-4xl font-bold text-[#333333]';
};

// Helper function to remove leading zeros
const removeLeadingZeros = (gameId: string) => gameId.replace(/^00/, '');

const formatMinutes = (min: string | null | undefined) => {
  if (!min) {
    return '00:00';
  }

  const [whole, decimal] = min.split('.');
  return `${whole}:${decimal ? decimal.slice(0, 2) : '00'}`;
};

const calculateFg3Pct = (fg3m: number, fg3a: number) => {
  if (fg3a === 0) return '0.0';
  return (fg3m / fg3a).toFixed(3);
};

const GameStatsPage: React.FC = () => {
  const { gameId: gameIdParam } = useParams();
  const gameId = Array.isArray(gameIdParam) ? gameIdParam[0] : gameIdParam;

  const [lineScores, setLineScores] = useState<LineScore[]>([]);
  const [teamStats, setTeamStats] = useState<TeamStats[]>([]);
  const [playerBoxStats, setPlayerBoxStats] = useState<PlayerBoxStats[]>([]);
  const [gameHeader, setGameHeader] = useState<GameHeader | null>(null);
  const [activeTab, setActiveTab] = useState('stats');

  useEffect(() => {
    if (!gameId) {
      return;
    }

    const fetchGameData = async () => {
      try {

        const [lineScoreResponse, teamStatsResponse, playerBoxStatsResponse, gameHeaderResponse] = await Promise.all([
          fetch(`${process.env.NEXT_PUBLIC_API_URL}/games/linescores/${gameId}`),
          fetch(`${process.env.NEXT_PUBLIC_API_URL}/teams/team-stats`),
          fetch(`${process.env.NEXT_PUBLIC_API_URL}/games/playerboxstats/${removeLeadingZeros(gameId)}`),
          fetch(`${process.env.NEXT_PUBLIC_API_URL}/games/headers/${gameId}`),
        ]);

        const lineScoreData = await lineScoreResponse.json();
        const teamStatsData = await teamStatsResponse.json();
        const playerBoxStatsData = await playerBoxStatsResponse.json();
        const gameHeaderData = await gameHeaderResponse.json();


        setGameHeader(gameHeaderData.length > 0 ? gameHeaderData[0] : null);

        const commonPlayerInfoRequests = playerBoxStatsData.map((player: PlayerBoxStats) =>
          fetch(`${process.env.NEXT_PUBLIC_API_URL}/players/commonPlayerInfo/playerId/${player.playerId}`)
        );
        
        const commonPlayerInfoResponses = await Promise.all(commonPlayerInfoRequests);
        const commonPlayerInfos = await Promise.all(commonPlayerInfoResponses.map(res => res.json()));

        const updatedPlayerBoxStats = playerBoxStatsData.map((player: PlayerBoxStats, index: number) => ({
          ...player,
          position: commonPlayerInfos[index].position || 'N/A',
          jersey: commonPlayerInfos[index].jersey || 'N/A',
          fg3Pct: calculateFg3Pct(player.fg3m, player.fg3a),
        }));

        setLineScores(lineScoreData);
        setTeamStats(Array.isArray(teamStatsData) ? teamStatsData : []);
        setPlayerBoxStats(updatedPlayerBoxStats);
      } catch (error) {
        console.error('Error fetching game data', error);
      }
    };

    fetchGameData();
  }, [gameId]);

  if (!lineScores.length) {
    return <div>Loading...</div>;
  }

  const homeTeamScore = lineScores.find((score) => score.teamId === lineScores[0].teamId && score.gameId === gameId)?.pts || 0;
  const visitorTeamScore = lineScores.find((score) => score.teamId !== lineScores[0].teamId && score.gameId === gameId)?.pts || 0;

  const BoxScoreTable = () => {
    const maxOvertimes = useMemo(
      () =>
        Math.max(
          ...lineScores.map((score) =>
            [
              score.ptsOt1, score.ptsOt2, score.ptsOt3, score.ptsOt4, score.ptsOt5,
              score.ptsOt6, score.ptsOt7, score.ptsOt8, score.ptsOt9, score.ptsOt10
            ].reduce((acc, pts, index) => (pts > 0 ? index + 1 : acc), 0)
          )
        ),
      [lineScores]
    );

    const overtimeColumns = () => {
      return Array.from({ length: maxOvertimes }, (_, i) => (
        <th key={`ot${i + 1}`} className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
          OT{i + 1}
        </th>
      ));
    };

    const overtimeScores = (score: LineScore) => {
      return Array.from({ length: maxOvertimes }, (_, i) => {
        const ptsOt = score[`ptsOt${i + 1}` as keyof LineScore];
        return (
          <td key={`otScore${i + 1}`} className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">
            {ptsOt || 0}
          </td>
        );
      });
    };

    return (
      <div className="bg-white p-4 rounded-lg shadow-lg border border-[#333333] mb-8" style={{ maxWidth: '1080px', margin: '0 auto', marginBottom: '50px' }}>
        <div className="overflow-x-auto scrollbar-thin scrollbar-thumb-rounded scrollbar-thumb-gray-400">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Team</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Q1</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Q2</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Q3</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Q4</th>
                {maxOvertimes > 0 && overtimeColumns()}
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Total</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {lineScores.map((score, index) => (
                <tr key={index}>
                  <td className="px-4 py-3 text-center whitespace-nowrap text-sm font-medium text-gray-900">
                    {getTeamAbbreviation(score.teamId, gameId, lineScores)}
                  </td>
                  <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.ptsQtr1 || 0}</td>
                  <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.ptsQtr2 || 0}</td>
                  <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.ptsQtr3 || 0}</td>
                  <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.ptsQtr4 || 0}</td>
                  {maxOvertimes > 0 && overtimeScores(score)}
                  <td className="px-4 py-3 text-center whitespace-nowrap text-sm font-bold text-gray-900">{score.pts}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  };

  const PlayerStatsTable = ({ teamId }: { teamId: number }) => {
    const filteredPlayerStats = useMemo(
      () => playerBoxStats.filter((stats) => stats.teamId === teamId && stats.gameId === removeLeadingZeros(gameId)),
      [playerBoxStats, teamId]
    );

    if (filteredPlayerStats.length === 0) {
      return <div>No player stats available.</div>;
    }

    const team = teamStats.find((team) => team.teamId === teamId);
    const teamName = team ? team.teamName : 'Team';
    const logoUrl = team ? team.logoUrl : '';

    return (
      <div className="bg-white p-4 rounded-lg shadow-lg border border-[#333333] mb-8" style={{ maxWidth: '1080px', margin: '0 auto', marginBottom: '50px' }}>
        <div className="flex items-center mb-4">
          <img src={logoUrl} alt={`${teamName} Logo`} className="w-10 h-10 mr-4" />
          <h2 className="text-xl font-bold text-[#333333]">{teamName}</h2>
        </div>
        <div className="overflow-x-auto scrollbar-thin scrollbar-thumb-rounded scrollbar-thumb-gray-400" style={{ width: '100%' }}>
          <table className="min-w-[1200px] divide-y divide-gray-200 table-fixed">
            <thead className="bg-gray-50">
              <tr>
                <th className="w-24 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Player</th>
                <th className="w-16 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Jersey</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Pos</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Min</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">PTS</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">AST</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">REB</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">STL</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">BLK</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">FGM</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">FGA</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">FG%</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">3PM</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">3PA</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">3P%</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">FTM</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">FTA</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">FT%</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">TO</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">PF</th>
                <th className="w-12 px-2 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">+/-</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {filteredPlayerStats.map((player, index) => (
                <tr key={index}>
                  <td className="w-24 px-2 py-2 text-center whitespace-nowrap text-sm font-medium text-gray-900">
                    <Link href={`/players/${player.commonPlayerId}`}>
                      <span className="text-[#333333] hover:underline">{player.playerName}</span>
                    </Link>
                  </td>
                  <td className="w-16 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.jersey}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.position}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{formatMinutes(player.min)}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.pts}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.ast}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.reb}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.stl}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.blk}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.fgm}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.fga}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.fgPct}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.fg3m}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.fg3a}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.fg3Pct}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.ftm}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.fta}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.ftPct}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.tov}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.pf}</td>
                  <td className="w-12 px-2 py-2 text-center whitespace-nowrap text-sm text-gray-500">{player.plusMinus}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  };

  const TeamStatsComponent = ({ teamId }: { teamId: number }) => {
    const teamName = useMemo(
      () => teamStats.find((team) => team.teamId === teamId)?.teamName || '',
      [teamStats, teamId]
    );
    const logoUrl = useMemo(() => getTeamLogo(teamId, teamStats), [teamId, teamStats]);

    return (
      <div className="bg-white p-4 rounded-lg shadow-lg border border-[#333333] mb-8">
        <div className="flex items-center mb-4">
          <img src={logoUrl} alt={`${teamName} Logo`} className="w-10 h-10 mr-4" />
          <h2 className="text-xl font-bold text-[#333333]">{teamName}</h2>
        </div>
        <div className="overflow-x-auto scrollbar-thin scrollbar-thumb-rounded scrollbar-thumb-gray-400">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">PTS</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">FG%</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">FT%</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">3P%</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">AST</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">REB</th>
                <th className="px-4 py-2 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">TOV</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {lineScores
                .filter((score) => score.teamId === teamId && score.gameId === gameId)
                .map((score, index) => (
                  <tr key={index}>
                    <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.pts}</td>
                    <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.fgPct}</td>
                    <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.ftPct}</td>
                    <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.fg3Pct}</td>
                    <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.ast}</td>
                    <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.reb}</td>
                    <td className="px-4 py-3 text-center whitespace-nowrap text-sm text-gray-500">{score.tov}</td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  };

  return (
    <div className="container mx-auto p-4 bg-[#f9f9f9]">
      {/* Game Card */}
      <div className="game-card bg-white p-8 rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300 border border-[#333333] mb-8">
        <div className="flex justify-between items-center">
          <div className="flex flex-col items-center w-1/3">
            <Link href={`/teams/${lineScores[0].teamId}`}>
            <Image
              src={getTeamLogo(lineScores[0].teamId, teamStats)}
              alt={`${getTeamAbbreviation(lineScores[0].teamId, gameId, lineScores)} Logo`}
              width={96} 
              height={96} 
              className="w-24 h-24 mb-2"
            />
            </Link>
            <span className="text-3xl font-semibold text-[#333333]">{getTeamAbbreviation(lineScores[0].teamId, gameId, lineScores)}</span>
            <span className="text-sm text-gray-600">{getTeamRecord(lineScores[0].teamId, gameId, lineScores)}</span>
            <span className={getScoreClass(homeTeamScore, visitorTeamScore)}>{homeTeamScore}</span>
          </div>
          <div className="text-center text-[#333333] space-y-2 w-1/3">
            {gameHeader ? (
              <>
                <p className="text-sm">{gameHeader.gameDateEst}</p>
                <p className="text-sm">{gameHeader.arenaName}</p>
                <p className="text-2xl font-bold">{gameHeader.gameStatusText}</p>
              </>
            ) : (
              <p className="text-sm">Loading...</p>
            )}
          </div>
          <div className="flex flex-col items-center w-1/3">
            <Link href={`/teams/${lineScores[1].teamId}`}>
            <Image
              src={getTeamLogo(lineScores[1].teamId, teamStats)}
              alt={`${getTeamAbbreviation(lineScores[1].teamId, gameId, lineScores)} Logo`}
              width={96} // Add width
              height={96} // Add height
              className="w-24 h-24 mb-2"
            />
            </Link>
            <span className="text-3xl font-semibold text-[#333333]">{getTeamAbbreviation(lineScores[1].teamId, gameId, lineScores)}</span>
            <span className="text-sm text-gray-600">{getTeamRecord(lineScores[1].teamId, gameId, lineScores)}</span>
            <span className={getScoreClass(visitorTeamScore, homeTeamScore)}>{visitorTeamScore}</span>
          </div>
        </div>
      </div>

      {/* Box Score Table */}
      <div style={{ marginBottom: '30px' }}>
        <BoxScoreTable />
      </div>

      {/* Tabs */}
      <div className="flex justify-center mb-4" style={{ marginBottom: '20px' }}>
        <button
          className={`px-4 py-2 font-bold text-lg ${activeTab === 'stats' ? 'text-[#00BFA6] border-b-4 border-[#00BFA6]' : 'text-[#333333]'}`}
          onClick={() => setActiveTab('stats')}
        >
          Team Stats
        </button>
        <button
          className={`ml-8 px-4 py-2 font-bold text-lg ${activeTab === 'roster' ? 'text-[#00BFA6] border-b-4 border-[#00BFA6]' : 'text-[#333333]'}`}
          onClick={() => setActiveTab('roster')}
        >
          Player Stats
        </button>
      </div>

      {/* Tab Content */}
      {activeTab === 'stats' ? (
        <>
          <TeamStatsComponent teamId={lineScores[0].teamId} />
          <TeamStatsComponent teamId={lineScores[1].teamId} />
        </>
      ) : (
        <>
          <PlayerStatsTable teamId={lineScores[0].teamId} />
          <PlayerStatsTable teamId={lineScores[1].teamId} />
        </>
      )}

      <style jsx>{`
        .container {
          padding: 2rem;
        }
        .game-card {
          background-color: white;
          border-radius: 1rem;
          padding: 2rem;
          box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
          transition: box-shadow 0.3s ease;
          border: 2px solid #333333;
        }
        .game-card:hover {
          box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        .scrollbar-thin {
          scrollbar-width: thin;
        }
        .scrollbar-thumb-rounded {
          scrollbar-color: #a0aec0 #edf2f7;
          scrollbar-width: thin;
        }
        .scrollbar-thumb-gray-400::-webkit-scrollbar-thumb {
          background-color: #a0aec0;
        }
        .scrollbar-thumb-gray-400::-webkit-scrollbar {
          height: 12px;
        }
        .overflow-x-auto {
          overflow-x: auto;
          width: 100%;
        }
      `}</style>
    </div>
  );
};

export default GameStatsPage;