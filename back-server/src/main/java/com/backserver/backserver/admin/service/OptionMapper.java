package com.backserver.backserver.admin.service;

import com.backserver.backserver.admin.Dto.OptionDTO;
import com.backserver.backserver.admin.model.Option;
import com.backserver.backserver.admin.model.Vote;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {

    public OptionDTO mapToDTO(Option option) {
        OptionDTO dto = new OptionDTO();
        dto.setId(option.getId());
        dto.setOptionText(option.getOptionText());
        dto.setVoteCount(option.getVoteCount());
        dto.setVoteId(option.getVote().getId());
        return dto;
    }

    public Option mapToEntity(OptionDTO optionDTO, Vote vote) {
        Option option = new Option();
        option.setId(optionDTO.getId());
        option.setOptionText(optionDTO.getOptionText());
        option.setVoteCount(optionDTO.getVoteCount());
        option.setVote(vote);
        return option;
    }
}