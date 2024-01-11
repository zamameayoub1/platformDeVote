package com.vote.votehomo.controller;

import com.vote.votehomo.mapper.Mapper;
import com.vote.votehomo.model.Candidate;
import com.vote.votehomo.model.ElectionConfiguration;
import com.vote.votehomo.model.Vote;
import com.vote.votehomo.repository.CandidateRepository;
import com.vote.votehomo.repository.ElectionConfigurationRepository;
import com.vote.votehomo.repository.VoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.vote.votehomo.service.*;
import com.vote.votehomo.dto.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/voting")
public class VotingController {

    @Autowired
    private CandidateService candidateService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteAggregationAndDecryptionService voteAggregationAndDecryptionService;
    @Autowired
    private VotingService votingService;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ElectionConfigurationRepository electionConfigurationRepository;

    private final VoteRepository voteRepository;
    private final Mapper mapper;
    private final ElectionConfigurationService electionConfigurationService;

    public VotingController(VoteRepository voteRepository, Mapper mapper, ElectionConfigurationService electionConfigurationService) {
        this.voteRepository = voteRepository;
        this.mapper = mapper;
        this.electionConfigurationService = electionConfigurationService;
    }

    @Operation(summary = "Create a new candidate")
    @PostMapping("/candidates")
    public ResponseEntity<?> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        try {
            CandidateDTO createdCandidate = candidateService.createCandidate(candidateDTO);
            return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating candidate: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Create a new vote")
    @PostMapping("/votes")
    public ResponseEntity<?> createVote(@RequestBody CreateVoteRequest createVoteRequest) {
        try {
            VoteDTO savedVoteDTO = voteService.createVote(createVoteRequest.getCandidateIds(), createVoteRequest.getVoterNumber());
            return new ResponseEntity<>(savedVoteDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating vote: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Cast a vote for a candidate")
    @PostMapping("/castVote")
    public ResponseEntity<?> castVoteForCandidate(@RequestBody VoteForaParticipantRequestDTO voteRequestDTO) {
        try {
            if (voteRequestDTO == null || voteRequestDTO.getVoteId() == null || voteRequestDTO.getCandidateId() == null) {
                throw new IllegalArgumentException("Invalid request data");
            }

            votingService.castVote(voteRequestDTO.getVoteId(), voteRequestDTO.getCandidateId());
            return new ResponseEntity<>("Vote successfully cast.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error in casting vote: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get the aggregated and decrypted results")
    @GetMapping("/results")
    public ResponseEntity<?> getCandidateResults() {
        try {
            Map<Long, Integer> results = voteAggregationAndDecryptionService.processVotesAndDecrypt();
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching results: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get all votes")
    @GetMapping("/votes")
    public ResponseEntity<?> getAllVotes() {
        try {
            List<VoteDTO> votes = voteService.getAllVotes();
            return new ResponseEntity<>(votes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving votes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
