package com.vote.votehomo.service;

import com.vote.votehomo.model.Candidate;
import com.vote.votehomo.model.Vote;
import com.vote.votehomo.repository.CandidateRepository;
import com.vote.votehomo.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteAggregationAndDecryptionService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public Map<Long, Integer> processVotesAndDecrypt() {
        List<Vote> votes = voteRepository.findAll();
        List<Long> decryptedVotes = decryptVotes(votes);
        return processDecryptedResult(decryptedVotes);
    }

    private List<Long> decryptVotes(List<Vote> votes) {
        List<Long> decryptedResults = new ArrayList<>();
        for (Vote vote : votes) {
            Long decryptedResult = decryptVote(vote);
            decryptedResults.add(decryptedResult);
        }
        return decryptedResults;
    }

    private Long decryptVote(Vote vote) {
        long multiplicateEncryptionVote1 = 1;
        long multiplicateEncryptionVote2 = 1;
        for (int i = 0; i < vote.getElectionConfiguration().getVoterNumber(); i++) {
            multiplicateEncryptionVote1 = (multiplicateEncryptionVote1 * vote.getEncryptionVote1()[i]) % vote.getElectionConfiguration().getP();
            multiplicateEncryptionVote2 = (multiplicateEncryptionVote2 * vote.getEncryptionVote2()[i]) % vote.getElectionConfiguration().getP();
        }
        long message = multiplicateEncryptionVote2;
        for (int i = 0; i < vote.getElectionConfiguration().getP() - 1 - vote.getElectionConfiguration().getA(); i++) {
            message = (message * multiplicateEncryptionVote1) % vote.getElectionConfiguration().getP();
        }

        return message;
    }

    private Map<Long, Integer> processDecryptedResult(List<Long> decryptedResults) {
        List<Candidate> candidates = candidateRepository.findAll();
        Map<Long, Integer> voteCounts = new HashMap<>();
        for (Long decryptedResult : decryptedResults) {
            Candidate candidate = getCandidateByDecryptedResult(decryptedResult, candidates);
            if (candidate != null) {
                Long candidateId = candidate.getId();
                int currentCount = voteCounts.getOrDefault(candidateId, 0);
                voteCounts.put(candidateId, currentCount + 1);
            }
        }
        return voteCounts;
    }

    private Candidate getCandidateByDecryptedResult(Long decryptedResult, List<Candidate> candidates) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equals(decryptedResult)) {
                return candidate;
            }
        }
        return null;
    }
}
