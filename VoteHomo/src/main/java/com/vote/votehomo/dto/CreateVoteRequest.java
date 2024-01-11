package com.vote.votehomo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateVoteRequest {
    private List<Long> candidateIds;
    private int voterNumber;
}
