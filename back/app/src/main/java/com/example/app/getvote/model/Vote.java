package com.example.app.getvote.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vote")
    private Long vote;
}
