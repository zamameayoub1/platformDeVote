package com.vote.votehomo.service;

import com.vote.votehomo.dto.ElectionConfigurationDTO;
import com.vote.votehomo.math.PaillierKeyGenerator;
import com.vote.votehomo.model.ElectionConfiguration;
import com.vote.votehomo.repository.ElectionConfigurationRepository;
import com.vote.votehomo.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.Base64;
import java.util.List;

@Service
public class ElectionConfigurationService {

    private final ElectionConfigurationRepository electionConfigurationRepository;
    private final Mapper mapper;

    @Autowired
    public ElectionConfigurationService(ElectionConfigurationRepository electionConfigurationRepository, Mapper mapper) {
        this.electionConfigurationRepository = electionConfigurationRepository;
        this.mapper = mapper;
    }

    public ElectionConfigurationDTO createElectionConfiguration(List<Long> candidateIds, int voterNumber) {
        Long p = generatePrimeNumber(candidateIds, voterNumber);
        Long alpha = generateAlpha(p);
        Long a = generateRandomA(p);
        Long beta = computeBeta(alpha, a, p);

        ElectionConfigurationDTO configDTO = new ElectionConfigurationDTO();
        configDTO.setCandidateNumber(candidateIds.size());
        configDTO.setVoterNumber(voterNumber);
        configDTO.setAlpha(alpha);
        configDTO.setBeta(beta);
        configDTO.setP(p);
        configDTO.setA(a);

        // Persisting the configuration
        ElectionConfiguration config = mapper.electionConfigurationDTOToElectionConfiguration(configDTO);
        config = electionConfigurationRepository.save(config);
        return mapper.electionConfigurationToElectionConfigurationDTO(config);
    }

    private Long generatePrimeNumber(List<Long> candidateIds, int voterNumber) {
        return 5281L;
    }

    private boolean isPrime(Long number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (Long i = 5L; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }


    private Long generateAlpha(Long p) {
        Long alpha = 2L;
        while (gcd(alpha, p - 1) != 1) {
            alpha++;
        }
        return alpha;
    }

    private Long gcd(Long a, Long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }


    private Long generateRandomA(Long p) {
        Long min = 2L;
        Long max = p - 4;
        return min + (long) (Math.random() * (max - min));
    }

    private Long computeBeta(long alpha, long a, long p) {
        Long beta = 1L;
        for (Long i = 0L; i < a; i++) {
            beta = (beta * alpha) % p;
        }
        return beta;
    }
    public ElectionConfigurationDTO getElectionConfiguration(Long id) {
        return electionConfigurationRepository.findById(id)
                .map(mapper::electionConfigurationToElectionConfigurationDTO)
                .orElseThrow(() -> new RuntimeException("Election configuration not found."));
    }

    private String convertKeyToString(java.security.Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
