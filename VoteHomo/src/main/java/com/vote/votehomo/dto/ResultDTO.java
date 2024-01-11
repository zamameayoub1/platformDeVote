package com.vote.votehomo.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private Long id;
    private CandidateDTO candidate;
    private int voteCount;
    private float votePercentage;
}
