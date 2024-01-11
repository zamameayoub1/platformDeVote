package com.backserver.backserver.admin.service;

import com.backserver.backserver.admin.Dto.OptionDTO;
import com.backserver.backserver.admin.Dto.VoteDTO;
import com.backserver.backserver.admin.model.Option;
import com.backserver.backserver.admin.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoteMapper {
    private final OptionMapper optionMapper;

    @Autowired
    public VoteMapper(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }

    public VoteDTO mapToDTO(Vote vote) {
        VoteDTO dto = new VoteDTO();
        dto.setId(vote.getId());
        dto.setQuestion(vote.getQuestion());
        List<OptionDTO> optionDTOs = vote.getOptions().stream()
                .map(optionMapper::mapToDTO)
                .collect(Collectors.toList());
        dto.setOptions(optionDTOs);

        return dto;
    }

    public Vote mapToEntity(VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setId(voteDTO.getId());
        vote.setQuestion(voteDTO.getQuestion());
        List<Option> options = voteDTO.getOptions().stream()
                .map(optionDTO -> optionMapper.mapToEntity(optionDTO, vote))
                .collect(Collectors.toList());
        vote.setOptions(options);
        return vote;
    }
}
