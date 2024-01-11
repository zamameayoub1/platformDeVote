package com.backserver.backserver.admin.Dto;

import lombok.Data;

import java.util.List;

@Data
public class VoteDTO {
    private Long id;
    private String question;
    private List<OptionDTO> options;
}
