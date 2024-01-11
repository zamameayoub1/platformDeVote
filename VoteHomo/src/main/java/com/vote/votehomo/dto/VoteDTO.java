package com.vote.votehomo.dto;

import com.vote.votehomo.model.ElectionConfiguration;
import lombok.Data;

import java.util.List;

@Data
public class VoteDTO {
    private Long id;
    private List<CandidateDTO> candidate;
    private long x;
    private long y;
    private ElectionConfigurationDTO electionConfigurationDTO;
}

