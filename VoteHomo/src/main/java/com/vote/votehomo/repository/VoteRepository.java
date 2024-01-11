package com.vote.votehomo.repository;

import com.vote.votehomo.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByElectionConfigurationId(Long electionConfigId);
}
