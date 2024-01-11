package com.vote.votehomo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElectionConfiguration electionConfiguration;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vote_candidate",
            joinColumns = @JoinColumn(name = "vote_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id")
    )
    private List<Candidate> candidates;

    private long x;
    private long y;
    private long[] encryptionVote1;
    private long[] encryptionVote2;

}
