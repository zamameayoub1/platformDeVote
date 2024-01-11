package com.vote.votehomo.repository;

import com.vote.votehomo.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}