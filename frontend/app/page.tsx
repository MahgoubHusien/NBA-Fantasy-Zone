"use client";

import React from 'react';
import Link from 'next/link';

export default function Home() {
  return (
    <div className="container mx-auto p-4 bg-[#f9f9f9]">
      <div className="header text-center mb-12">
        <h1 className="text-5xl font-extrabold text-[#333333] mb-4">Welcome to NBA Fantasy Zone!</h1>
        <p className="text-lg text-gray-600">Your ultimate destination for NBA stats, fantasy insights, and more</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        <Link href="/players">
          <div className="border-4 border-[#333333] rounded-lg overflow-hidden h-96 flex flex-col justify-between hover:bg-[#f1f1f1] transition duration-300 cursor-pointer">
            <img
              src="/home/players.jpg"
              alt="Players"
              className="w-full h-48 object-cover"
            />
            <div className="p-6 text-center bg-white flex-grow flex flex-col justify-center">
              <span className="text-3xl font-bold text-[#00BFA6] hover:text-[#009a8b]">Players</span>
              <p className="text-gray-600 mt-2">Check all of the NBA players</p>
            </div>
          </div>
        </Link>

        <Link href="/teams">
          <div className="border-4 border-[#333333] rounded-lg overflow-hidden h-96 flex flex-col justify-between hover:bg-[#f1f1f1] transition duration-300 cursor-pointer">
            <img
              src="/home/teams.jpg"
              alt="Teams"
              className="w-full h-48 object-cover"
            />
            <div className="p-6 text-center bg-white flex-grow flex flex-col justify-center">
              <span className="text-3xl font-bold text-[#00BFA6] hover:text-[#009a8b]">View Teams</span>
              <p className="text-gray-600 mt-2">Explore all NBA teams and their stats</p>
            </div>
          </div>
        </Link>

        <Link href="/games">
          <div className="border-4 border-[#333333] rounded-lg overflow-hidden h-96 flex flex-col justify-between hover:bg-[#f1f1f1] transition duration-300 cursor-pointer">
            <img
              src="/home/games.jpg"
              alt="Games"
              className="w-full h-48 object-cover"
            />
            <div className="p-6 text-center bg-white flex-grow flex flex-col justify-center">
              <span className="text-3xl font-bold text-[#00BFA6] hover:text-[#009a8b]">Latest Games</span>
              <p className="text-gray-600 mt-2">Stay updated with the latest game scores and highlights</p>
            </div>
          </div>
        </Link>

        <Link href="/standings">
          <div className="border-4 border-[#333333] rounded-lg overflow-hidden h-96 flex flex-col justify-between hover:bg-[#f1f1f1] transition duration-300 cursor-pointer">
            <img
              src="/home/standings.jpg"
              alt="Standings"
              className="w-full h-48 object-cover"
            />
            <div className="p-6 text-center bg-white flex-grow flex flex-col justify-center">
              <span className="text-3xl font-bold text-[#00BFA6] hover:text-[#009a8b]">Standings</span>
              <p className="text-gray-600 mt-2">View the current NBA standings</p>
            </div>
          </div>
        </Link>

        <Link href="/league-leaders">
          <div className="border-4 border-[#333333] rounded-lg overflow-hidden h-96 flex flex-col justify-between hover:bg-[#f1f1f1] transition duration-300 cursor-pointer">
            <img
              src="/home/leagueleaders.jpg"
              alt="League Leaders"
              className="w-full h-48 object-cover"
            />
            <div className="p-6 text-center bg-white flex-grow flex flex-col justify-center">
              <span className="text-3xl font-bold text-[#00BFA6] hover:text-[#009a8b]">League Leaders</span>
              <p className="text-gray-600 mt-2">Discover the top-performing players making headlines</p>
            </div>
          </div>
        </Link>

        <Link href="/contact">
          <div className="border-4 border-[#333333] rounded-lg overflow-hidden h-96 flex flex-col justify-between hover:bg-[#f1f1f1] transition duration-300 cursor-pointer">
            <img
              src="/home/contact.jpg"
              alt="Contact"
              className="w-full h-48 object-cover"
            />
            <div className="p-6 text-center bg-white flex-grow flex flex-col justify-center">
              <span className="text-3xl font-bold text-[#00BFA6] hover:text-[#009a8b]">Contact Us</span>
              <p className="text-gray-600 mt-2">Send us your feedback, requests, or inquiries</p>
            </div>
          </div>
        </Link>

      </div>

      <style jsx>{`
        .header {
          background-color: #ffffff;
          padding: 2rem;
          border-radius: 1rem;
          box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }
        img {
          max-width: 100%;
          height: auto;
        }
      `}</style>
    </div>
  );
}
