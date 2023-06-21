package com.tukorea.turtleneck.backend.domain.health.dao;

import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<HealthInfo, Long> {
}
