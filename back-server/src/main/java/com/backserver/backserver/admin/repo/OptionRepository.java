package com.backserver.backserver.admin.repo;

import com.backserver.backserver.admin.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
