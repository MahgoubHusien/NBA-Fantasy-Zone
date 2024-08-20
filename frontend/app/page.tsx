"use client";

import React from 'react';
import Link from 'next/link';
import Head from 'next/head';
import Image from 'next/image';


export default function Home() {
  return (
    <div className="container mx-auto p-4 bg-[#f9f9f9]">
      <Head>
        <link rel="icon" href="/favicon.ico" sizes="48x48" />
        <title>NBA Fantasy Zone</title>
        <meta name="description" content="Your ultimate destination for NBA stats, fantasy insights, and more" />
      </Head>      
      <div className="header text-center mb-12">
        <h1 className="text-5xl font-extrabold text-[#333333] mb-4">Welcome to NBA Fantasy Zone!</h1>
        <p className="text-lg text-gray-600">Your ultimate destination for NBA stats, fantasy insights, and more</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        <OptimizedLink href="/players" imgSrc="/home/players.jpg" alt="Players" title="Players" description="Check all of the NBA players" />
        <OptimizedLink href="/teams" imgSrc="/home/teams.jpg" alt="Teams" title="View Teams" description="Explore all NBA teams and their stats" />
        <OptimizedLink href="/games" imgSrc="/home/games.jpg" alt="Games" title="Latest Games" description="Stay updated with the latest game scores and highlights" />
        <OptimizedLink href="/standings" imgSrc="/home/standings.jpg" alt="Standings" title="Standings" description="View the current NBA standings" />
        <OptimizedLink href="/league-leaders" imgSrc="/home/leagueleaders.jpg" alt="League Leaders" title="League Leaders" description="Discover the top-performing players making headlines" />
        <OptimizedLink href="/contact" imgSrc="/home/contact.jpg" alt="Contact" title="Contact" description="Send your feedback, requests, or inquiries" />
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

type OptimizedLinkProps = {
  href: string;
  imgSrc: string;
  alt: string;
  title: string;
  description: string;
};

const OptimizedLink: React.FC<OptimizedLinkProps> = React.memo(({ href, imgSrc, alt, title, description }) => (
  <Link href={href}>
    <div className="border-4 border-[#333333] rounded-lg overflow-hidden h-96 flex flex-col justify-between hover:bg-[#f1f1f1] transition duration-300 cursor-pointer">
      <Image
        src={imgSrc}
        alt={alt}
        width={500}
        height={300}
        className="w-full h-48 object-cover"
      />
      <div className="p-6 text-center bg-white flex-grow flex flex-col justify-center">
        <span className="text-3xl font-bold text-[#00BFA6] hover:text-[#009a8b]">{title}</span>
        <p className="text-gray-600 mt-2">{description}</p>
      </div>
    </div>
  </Link>
));

OptimizedLink.displayName = "OptimizedLink";

