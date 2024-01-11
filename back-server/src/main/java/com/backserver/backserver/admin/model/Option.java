package com.backserver.backserver.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String OptionText;
    private int voteCount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vote_id")
    private Vote vote;
}
