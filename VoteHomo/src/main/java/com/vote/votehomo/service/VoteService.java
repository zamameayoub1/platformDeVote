package com.vote.votehomo.service;

import com.vote.votehomo.dto.ElectionConfigurationDTO;
import com.vote.votehomo.dto.VoteDTO;
import com.vote.votehomo.model.Candidate;
import com.vote.votehomo.model.ElectionConfiguration;
import com.vote.votehomo.model.Vote;
import com.vote.votehomo.repository.CandidateRepository;
import com.vote.votehomo.repository.ElectionConfigurationRepository;
import com.vote.votehomo.repository.VoteRepository;
import com.vote.votehomo.mapper.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final Mapper mapper;
    private final ElectionConfigurationService electionConfigurationService;
    private final CandidateRepository candidateRepository;
    private final ElectionConfigurationRepository electionConfigurationRepository;
    @Autowired
    public VoteService(VoteRepository voteRepository, Mapper mapper, ElectionConfigurationService electionConfigurationService, CandidateRepository candidateRepository, ElectionConfigurationRepository electionConfigurationRepository) {
        this.voteRepository = voteRepository;
        this.mapper = mapper;
        this.electionConfigurationService = electionConfigurationService;
        this.candidateRepository = candidateRepository;
        this.electionConfigurationRepository = electionConfigurationRepository;
    }

    @Transactional
    public VoteDTO createVote(List<Long> candidateIds, int voterNumber) {
        ElectionConfigurationDTO configDTO = electionConfigurationService.createElectionConfiguration(candidateIds, voterNumber);
        ElectionConfiguration config = mapper.electionConfigurationDTOToElectionConfiguration(configDTO);
        config = electionConfigurationRepository.save(config);
        List<Candidate> candidates = candidateRepository.findAllById(candidateIds);
        if (candidates.size() != candidateIds.size()) {
            throw new RuntimeException("One or more candidates not found for the provided IDs.");
        }
        Vote vote = new Vote();
        vote.setElectionConfiguration(config);
        vote.setCandidates(candidates);
        vote = voteRepository.save(vote);
        for (Candidate candidate : candidates) {
            if (candidate.getVotesId() == null) {
                candidate.setVotesId(new ArrayList<>());
            }
            candidate.getVotesId().add(vote.getId());
            candidateRepository.save(candidate);
        }

        return mapper.voteToVoteDTO(vote);
    }




    public List<VoteDTO> getAllVotes() {
        List<Vote> votes = voteRepository.findAll();
        return votes.stream()
                .map(mapper::voteToVoteDTO)
                .collect(Collectors.toList());
    }

}
