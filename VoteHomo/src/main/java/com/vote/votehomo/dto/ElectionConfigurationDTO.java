package com.vote.votehomo.dto;

import lombok.Data;

@Data
public class ElectionConfigurationDTO {
    private Long id;
    private int candidateNumber;
    private int voterNumber;
    private Long alpha;
    private Long beta;
    private Long p;
    private Long a;

    // private String publicKey;
    // private String privateKey;
}

