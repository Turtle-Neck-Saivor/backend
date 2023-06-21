package com.tukorea.turtleneck.backend.domain.health.dao;

import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HealthRepository extends JpaRepository<HealthInfo, Long> {
    @Query("SELECT info FROM HealthInfo info WHERE info.memberEntity = :memberEntity " +
            "AND info.date = :date " +
            "AND info.isActive = true")
    List<HealthInfo> findByMemberEntityAndDate(
            @Param("memberEntity") MemberEntity memberEntity,
            @Param("date") LocalDate date
    );
}
