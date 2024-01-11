package com.vote.votehomo.service;

import com.vote.votehomo.dto.CandidateDTO;
import com.vote.votehomo.model.Candidate;
import com.vote.votehomo.repository.CandidateRepository;
import com.vote.votehomo.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final Mapper mapper;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, Mapper mapper) {
        this.candidateRepository = candidateRepository;
        this.mapper = mapper;
    }

    public CandidateDTO createCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = mapper.candidateDTOToCandidate(candidateDTO);
        candidate = candidateRepository.save(candidate);
        return mapper.candidateToCandidateDTO(candidate);
    }

    public CandidateDTO updateCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = mapper.candidateDTOToCandidate(candidateDTO);
        candidate = candidateRepository.save(candidate);
        return mapper.candidateToCandidateDTO(candidate);
    }

    public CandidateDTO getCandidate(Long id) {
        return candidateRepository.findById(id)
                .map(mapper::candidateToCandidateDTO)
                .orElseThrow(() -> new RuntimeException("Candidate not found."));
    }

    public List<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(mapper::candidateToCandidateDTO)
                .collect(Collectors.toList());
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
