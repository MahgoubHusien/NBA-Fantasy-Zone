"use client";

import React, { useState, useCallback } from 'react';
import emailjs from '@emailjs/browser';

const ContactPage: React.FC = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [subject, setSubject] = useState('');
  const [message, setMessage] = useState('');
  const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState('');

  const resetForm = () => {
    setName('');
    setEmail('');
    setSubject('');
    setMessage('');
  };

  const handleSubmit = useCallback(async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!name || !email || !subject || !message) {
      setError('All fields are required.');
      return;
    }

    const templateParams = {
      name,
      email,
      subject,
      message,
    };

    try {
      const result = await emailjs.send(
        process.env.SERVICE_ID as string,
        process.env.TEMPLATE_ID as string,  
        templateParams,
        process.env.PUBLIC_KEY as string
      );

      if (result.status === 200) {
        setSubmitted(true);
        setError('');
        resetForm();
      } else {
        setError('Something went wrong. Please try again.');
      }
    } catch (err) {
      setError('Failed to send your message. Please check your internet connection and try again.');
      console.error('Failed to send email:', err);
    }
  }, [name, email, subject, message]);

  return (
    <div className="container mx-auto p-4 bg-[#f9f9f9]">
      <div className="title-container bg-white p-6 rounded-lg shadow-lg mb-8">
        <h1 className="text-4xl font-extrabold text-center text-[#333333]">Contact</h1>
        <p className="text-center text-gray-700 mb-8">
          We value your feedback! Please send us your reviews, requests, criticisms, or any other thoughts you may have. We are always looking to improve and appreciate your input.
        </p>
        {submitted ? (
          <div className="bg-green-100 text-green-700 p-4 rounded-lg text-center">
            <p>Your message has been sent successfully! Thank you for reaching out.</p>
          </div>
        ) : (
          <form onSubmit={handleSubmit}>
            {error && (
              <div className="bg-red-100 text-red-700 p-4 rounded-lg text-center mb-4">
                <p>{error}</p>
              </div>
            )}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700">Name</label>
                <input
                  type="text"
                  className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-[#00BFA6] focus:border-[#00BFA6]"
                  placeholder="Your Name"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  required
                />
              </div>
              <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700">Email</label>
                <input
                  type="email"
                  className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-[#00BFA6] focus:border-[#00BFA6]"
                  placeholder="Your Email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>
            </div>
            <div className="mb-4">
              <label className="block text-sm font-medium text-gray-700">Subject</label>
              <input
                type="text"
                className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-[#00BFA6] focus:border-[#00BFA6]"
                placeholder="Subject"
                value={subject}
                onChange={(e) => setSubject(e.target.value)}
                required
              />
            </div>
            <div className="mb-4">
              <label className="block text-sm font-medium text-gray-700">Message</label>
              <textarea
                className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-[#00BFA6] focus:border-[#00BFA6]"
                rows={6}
                placeholder="Your Message"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                required
              ></textarea>
            </div>
            <div className="text-center">
              <button
                type="submit"
                className="px-6 py-3 bg-[#00BFA6] text-white font-semibold rounded-md shadow-sm hover:bg-[#009a8b] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-[#00BFA6]"
              >
                Send Message
              </button>
            </div>
          </form>
        )}
      </div>
    </div>
  );
};

export default ContactPage;
