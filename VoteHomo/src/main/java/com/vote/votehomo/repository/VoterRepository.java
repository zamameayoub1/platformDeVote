package com.vote.votehomo.repository;

import com.vote.votehomo.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}