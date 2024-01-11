package com.vote.votehomo.dto;

import lombok.Data;

@Data
public class VoteForaParticipantRequestDTO {
    private Long voteId;
    private Long candidateId;
}
