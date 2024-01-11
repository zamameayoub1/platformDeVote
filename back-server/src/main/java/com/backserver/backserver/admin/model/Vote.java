package com.backserver.backserver.admin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Option> options = new ArrayList<>();


}
