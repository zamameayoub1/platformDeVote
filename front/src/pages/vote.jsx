import React, { useState } from 'react';
import { Link } from "react-router-dom";
import {
    Input,
    Checkbox,
    Button,
    Typography,
  } from "@material-tailwind/react";

export function Vote() {
    const [selectedCandidate, setSelectedCandidate] = useState('');

    const candidates = [
      { id: 'candidate-1', name: 'Candidate 1' },
      { id: 'candidate-2', name: 'Candidate 2' },
      { id: 'candidate-3', name: 'Candidate 3' },
    ];
  
    const handleSubmit = (e) => {
      e.preventDefault();
      alert(`You have voted for: ${selectedCandidate}`);
    };
  
    return (
        <section className="m-8 flex gap-4">
        <div className="w-full lg:w-3/5 mt-24">
        <div className="voting-form-container">
        <div className="max-w-4xl mx-auto p-8">
          <Typography variant="h3" className="mb-6 text-center font-bold">
            Vote for Your Candidate
          </Typography>
          <form onSubmit={handleSubmit}>
            <div className="mb-4 flex flex-col items-center">
            {candidates.map((candidate) => (
                <label key={candidate.id} className="block mb-2">
                <input
                    type="radio"
                    name="candidate"
                    value={candidate.name}
                    onChange={(e) => setSelectedCandidate(e.target.value)}
                    className="form-radio h-5 w-5 text-gray-600 mr-2"
                />
                <span className="text-gray-700 dark:text-gray-300">{candidate.name}</span>
                </label>
            ))}
            </div>

            <div className="text-center">
            <Link to="/">
                <Button className="mt-6" fullWidth>
                    Submit Vote
                </Button>
            </Link>
                
            </div>
          </form>
        </div>
      </div>
  
        </div>
        <div className="w-2/5 h-full hidden lg:block">
          <img
            src="/img/pattern.png"
            className="h-full w-full object-cover rounded-3xl"
          />
        </div>
  
      </section>
    );
}

export default Vote;
