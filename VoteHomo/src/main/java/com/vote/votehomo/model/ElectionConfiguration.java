package com.vote.votehomo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
public class ElectionConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
