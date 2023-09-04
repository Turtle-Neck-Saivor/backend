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
            "AND DAYOFWEEK(info.createdAt) = :date " +
            "AND info.isActive = true")
    List<HealthInfo> findByDay(
            @Param("memberEntity") MemberEntity memberEntity,
            @Param("date") LocalDate date
    );

    @Query("SELECT info FROM HealthInfo info WHERE info.memberEntity = :memberEntity " +
            "AND DAYOFWEEK(info.createdAt) >= :startOfWeek " +
            "AND DAYOFWEEK(info.createdAt) <= :endOfWeek " +
            "AND info.isActive = true")
    List<HealthInfo> findByWeek(
            @Param("memberEntity") MemberEntity memberEntity,
            @Param("startOfWeek") LocalDate startOfWeek,
            @Param("endOfWeek") LocalDate endOfWeek
    );

    @Query("SELECT info FROM HealthInfo info WHERE info.memberEntity = :member " +
            "AND YEAR(info.createdAt) = :year " +
            "AND info.isActive = true")
    List<HealthInfo> findByMemberEntityAndYear(@Param("member") MemberEntity member, @Param("year") int year);
}
