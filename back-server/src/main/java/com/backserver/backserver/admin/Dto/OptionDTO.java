package com.backserver.backserver.admin.Dto;

import com.backserver.backserver.admin.model.Vote;
import lombok.Data;

@Data
public class OptionDTO {
    private Long id;
    private String optionText;
    private int voteCount;
    private Long voteId;
}
