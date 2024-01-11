package com.vote.votehomo.service;

import com.vote.votehomo.dto.CandidateDTO;
import com.vote.votehomo.dto.VoteDTO;
import com.vote.votehomo.dto.ElectionConfigurationDTO;
import com.vote.votehomo.model.Candidate;
import com.vote.votehomo.model.Vote;
import com.vote.votehomo.repository.CandidateRepository;
import com.vote.votehomo.repository.VoteRepository;
import com.vote.votehomo.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {

    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final Mapper mapper;
    private final ElectionConfigurationService electionConfigurationService;

    @Autowired
    public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository, Mapper mapper, ElectionConfigurationService electionConfigurationService) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.mapper = mapper;
        this.electionConfigurationService = electionConfigurationService;
    }

    public void castVote(Long voteId, Long candidateId) {
        ElectionConfigurationDTO config = electionConfigurationService.getElectionConfiguration(voteId);

        if (config == null) {
            throw new IllegalStateException("Election configuration not found.");
        }

        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new IllegalStateException("Candidate not found."));
        CandidateDTO candidateDTO = mapper.candidateToCandidateDTO(candidate);

        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setElectionConfigurationDTO(config);
        VoteDTO encryptedVote = encryptVote(voteDTO, config, candidateDTO);
        Vote vote = mapper.voteDTOToVote(encryptedVote);
        voteRepository.save(vote);
    }

    private VoteDTO encryptVote(VoteDTO voteDTO, ElectionConfigurationDTO config, CandidateDTO candidateDTO) {
        Long voteValue = convertVoteToLong(candidateDTO);
        Long x = (voteValue * config.getAlpha()) % config.getP();
        Long y = (voteValue * config.getBeta()) % config.getP();

        voteDTO.setX(x);
        voteDTO.setY(y);
        return voteDTO;
    }

    private Long convertVoteToLong(CandidateDTO candidateDTO) {
        return candidateDTO.getId();
    }
}
