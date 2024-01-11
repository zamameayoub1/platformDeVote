package com.backserver.backserver.admin.Dto;

import lombok.Data;

@Data
public class VoteRequestDTO {
    private Long optionId;
    private Long voteId;
}
