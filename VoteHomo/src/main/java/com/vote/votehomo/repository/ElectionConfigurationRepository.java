package com.vote.votehomo.repository;


import com.vote.votehomo.model.ElectionConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionConfigurationRepository extends JpaRepository<ElectionConfiguration, Long> {
}