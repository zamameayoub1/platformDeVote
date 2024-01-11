package com.backserver.backserver.admin.service;

import com.backserver.backserver.admin.Dto.OptionDTO;
import com.backserver.backserver.admin.model.Option;
import com.backserver.backserver.admin.model.Vote;
import com.backserver.backserver.admin.repo.OptionRepository;
import com.backserver.backserver.admin.repo.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OptionService {

    private final OptionRepository optionRepository;
    private final OptionMapper optionMapper;
    private final VoteRepository voteRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository, OptionMapper optionMapper, VoteRepository voteRepository) {
        this.optionRepository = optionRepository;
        this.optionMapper = optionMapper;
        this.voteRepository = voteRepository;
    }

    public OptionDTO getOptionById(Long optionId) {
        Optional<Option> option = optionRepository.findById(optionId);
        if (option.isPresent()) {
            return optionMapper.mapToDTO(option.get()); // Use instance to call method
        } else {
            throw new NotFoundException("Option not found");
        }
    }

    public OptionDTO createOption(OptionDTO optionDTO) {
        // Find the associated Vote entity
        Vote vote = voteRepository.findById(optionDTO.getVoteId())
                .orElseThrow(() -> new NotFoundException("Vote not found"));

        Option option = optionMapper.mapToEntity(optionDTO, vote); // Associate with Vote
        Option savedOption = optionRepository.save(option);
        return optionMapper.mapToDTO(savedOption);
    }

    public OptionDTO updateOption(Long optionId, OptionDTO updatedOptionDTO) {
        Optional<Option> existingOption = optionRepository.findById(optionId);
        if (existingOption.isPresent()) {
            Option updatedOption = optionRepository.save(existingOption.get());
            return optionMapper.mapToDTO(updatedOption); // Use instance to call method
        } else {
            // Handle not found scenario
            throw new NotFoundException("Option not found");
        }
    }

    public void deleteOption(Long optionId) {
        optionRepository.deleteById(optionId);
    }
}
