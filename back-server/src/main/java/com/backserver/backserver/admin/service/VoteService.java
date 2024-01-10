package com.backserver.backserver.admin.service;

import com.backserver.backserver.admin.Dto.OptionDTO;
import com.backserver.backserver.admin.Dto.VoteDTO;
import com.backserver.backserver.admin.model.Option;
import com.backserver.backserver.admin.model.Vote;
import com.backserver.backserver.admin.repo.OptionRepository;
import com.backserver.backserver.admin.repo.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final OptionRepository optionRepository;
    private final VoteMapper voteMapper;
    private final OptionMapper optionMapper;

    @Autowired
    public VoteService(VoteRepository voteRepository, OptionRepository optionRepository, VoteMapper voteMapper, OptionMapper optionMapper) {
        this.voteRepository = voteRepository;
        this.optionRepository = optionRepository;
        this.voteMapper = voteMapper;
        this.optionMapper = optionMapper;
    }

    public List<VoteDTO> getAllVotes() {
        List<Vote> votes = voteRepository.findAll();
        return votes.stream().map(voteMapper::mapToDTO).collect(Collectors.toList());
    }

    public Optional<VoteDTO> getVoteById(Long voteId) {
        Optional<Vote> vote = voteRepository.findById(voteId);
        return vote.map(voteMapper::mapToDTO);
    }


    public VoteDTO createVote(VoteDTO voteDTO) {
        Vote vote = new Vote();

        vote.setQuestion(voteDTO.getQuestion());
        List<Option> newOptions = new ArrayList<>();

        for (OptionDTO optionDTO : voteDTO.getOptions()) {
            Option option = new Option();
            option.setOptionText(optionDTO.getOptionText());
            option.setVote(vote);
            newOptions.add(option);
        }

        vote.setOptions(newOptions);
        Vote savedVote = voteRepository.save(vote);
        return voteMapper.mapToDTO(savedVote);
    }

    public void voteForOption(Long voteId, Long optionId) {
        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new NotFoundException("Vote not found"));

        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new NotFoundException("Option not found"));
        option.setVoteCount(option.getVoteCount() + 1);
        optionRepository.save(option);
    }

    public VoteDTO updateVote(Long voteId, VoteDTO updatedVoteDTO) {
        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new NotFoundException("Vote not found"));

        vote.setQuestion(updatedVoteDTO.getQuestion());

        Vote updatedVote = voteRepository.save(vote);
        return voteMapper.mapToDTO(updatedVote);
    }

    public void deleteVote(Long voteId) {
        voteRepository.deleteById(voteId);
    }
}
