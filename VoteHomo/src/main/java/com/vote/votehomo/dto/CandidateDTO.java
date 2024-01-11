package com.vote.votehomo.dto;


import com.vote.votehomo.model.Vote;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class CandidateDTO {
    private Long id;
    private String name;
    private List<Long> votes;

}