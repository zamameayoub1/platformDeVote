package com.vote.votehomo.service;

import com.vote.votehomo.dto.VoterDTO;
import com.vote.votehomo.model.Voter;
import com.vote.votehomo.repository.VoterRepository;
import com.vote.votehomo.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoterService {

    private final VoterRepository voterRepository;
    private final Mapper mapper;

    @Autowired
    public VoterService(VoterRepository voterRepository, Mapper mapper) {
        this.voterRepository = voterRepository;
        this.mapper = mapper;
    }

    @Transactional
    public VoterDTO registerVoter(VoterDTO voterDTO) {
        Voter voter = mapper.voterDTOToVoter(voterDTO);
        voter = voterRepository.save(voter);
        return mapper.voterToVoterDTO(voter);
    }

    @Transactional(readOnly = true)
    public VoterDTO getVoter(Long id) {
        return voterRepository.findById(id)
                .map(mapper::voterToVoterDTO)
                .orElseThrow(() -> new RuntimeException("Voter not found"));
    }

    @Transactional
    public VoterDTO updateVoter(VoterDTO voterDTO) {
        Voter voter = mapper.voterDTOToVoter(voterDTO);
        voter = voterRepository.save(voter);
        return mapper.voterToVoterDTO(voter);
    }
}
