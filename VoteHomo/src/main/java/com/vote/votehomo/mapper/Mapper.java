package com.vote.votehomo.mapper;

import com.vote.votehomo.dto.*;
import com.vote.votehomo.model.*;
import com.vote.votehomo.repository.VoteRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    private final VoteRepository voteRepository;

    public Mapper(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public VoteDTO voteToVoteDTO(Vote vote) {
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setId(vote.getId());
        voteDTO.setCandidate(vote.getCandidates().stream()
                .map(candidate -> {
                    CandidateDTO candidateDTO = new CandidateDTO();
                    candidateDTO.setId(candidate.getId());
                    candidateDTO.setName(candidate.getName());
                    return candidateDTO;
                })
                .collect(Collectors.toList()));
        voteDTO.setX(vote.getX());
        voteDTO.setY(vote.getY());
        voteDTO.setElectionConfigurationDTO(electionConfigurationToElectionConfigurationDTO(vote.getElectionConfiguration()));
        return voteDTO;
    }


    public Vote voteDTOToVote(VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setId(voteDTO.getId());
        vote.setCandidates(voteDTO.getCandidate().stream().map(this::candidateDTOToCandidate).collect(Collectors.toList()));
        vote.setX(voteDTO.getX());
        vote.setY(voteDTO.getY());
        vote.setElectionConfiguration(electionConfigurationDTOToElectionConfiguration(voteDTO.getElectionConfigurationDTO()));
        return vote;
    }

    public CandidateDTO candidateToCandidateDTO(Candidate candidate) {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setId(candidate.getId());
        candidateDTO.setName(candidate.getName());
        candidateDTO.setVotes(candidate.getVotesId() != null
                ? candidate.getVotesId()
                : Collections.emptyList());

        return candidateDTO;
    }






    public Candidate candidateDTOToCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate();
        candidate.setId(candidateDTO.getId());
        candidate.setName(candidateDTO.getName());
        return candidate;
    }
    public VoterDTO voterToVoterDTO(Voter voter) {
        VoterDTO voterDTO = new VoterDTO();
        voterDTO.setId(voter.getId());
        voterDTO.setName(voter.getName());
        return voterDTO;
    }

    public Voter voterDTOToVoter(VoterDTO voterDTO) {
        Voter voter = new Voter();
        voter.setId(voterDTO.getId());
        voter.setName(voterDTO.getName());
        return voter;
    }
    public ResultDTO resultToResultDTO(Result result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(result.getId());
        resultDTO.setCandidate(candidateToCandidateDTO(result.getCandidate()));
        resultDTO.setVoteCount(result.getVoteCount());
        resultDTO.setVotePercentage(result.getVotePercentage());
        return resultDTO;
    }

    public Result resultDTOToResult(ResultDTO resultDTO) {
        Result result = new Result();
        result.setId(resultDTO.getId());
        result.setCandidate(candidateDTOToCandidate(resultDTO.getCandidate()));
        result.setVoteCount(resultDTO.getVoteCount());
        result.setVotePercentage(resultDTO.getVotePercentage());
        return result;
    }

    public ElectionConfigurationDTO electionConfigurationToElectionConfigurationDTO(ElectionConfiguration config) {
        ElectionConfigurationDTO configDTO = new ElectionConfigurationDTO();
        configDTO.setId(config.getId());
        configDTO.setCandidateNumber(config.getCandidateNumber());
        configDTO.setVoterNumber(config.getVoterNumber());
        configDTO.setAlpha(config.getAlpha());
        configDTO.setBeta(config.getBeta());
        configDTO.setP(config.getP());
        configDTO.setA(config.getA());
        // configDTO.setPrivateKey(config.getPrivateKey());
        //configDTO.setPublicKey(config.getPublicKey());
        return configDTO;
    }

    public ElectionConfiguration electionConfigurationDTOToElectionConfiguration(ElectionConfigurationDTO configDTO) {
        ElectionConfiguration config = new ElectionConfiguration();
        config.setId(configDTO.getId());
        config.setCandidateNumber(configDTO.getCandidateNumber());
        config.setVoterNumber(configDTO.getVoterNumber());
        config.setAlpha(configDTO.getAlpha());
        config.setBeta(configDTO.getBeta());
        config.setP(configDTO.getP());
        config.setA(configDTO.getA());
        //config.setPublicKey(configDTO.getPublicKey());
        //config.setPublicKey(configDTO.getPublicKey());
        return config;
    }
}
