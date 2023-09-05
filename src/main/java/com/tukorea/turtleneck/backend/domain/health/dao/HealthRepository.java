package com.tukorea.turtleneck.backend.domain.health.dao;

import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HealthRepository extends JpaRepository<HealthInfo, Long> {
    @Query("SELECT info FROM HealthInfo info WHERE info.memberEntity = :memberEntity " +
            "AND DAYOFWEEK(info.createdAt) = DAYOFWEEK(:date) " +
            "AND info.isActive = true")
    List<HealthInfo> findByDay(
            @Param("memberEntity") MemberEntity memberEntity,
            @Param("date") LocalDate date
    );

    @Query("SELECT info FROM HealthInfo info WHERE info.memberEntity = :memberEntity " +
    "AND info.createdAt >= :startOfWeek " +
    "AND info.createdAt <= :endOfWeek " +
            "AND info.isActive = true")
    List<HealthInfo> findByWeek(
            @Param("memberEntity") MemberEntity memberEntity,
            @Param("startOfWeek") LocalDateTime startOfWeek,
            @Param("endOfWeek") LocalDateTime endOfWeek
    );

    @Query("SELECT info FROM HealthInfo info WHERE info.memberEntity = :member " +
            "AND YEAR(info.createdAt) = :year " +
            "AND info.isActive = true")
    List<HealthInfo> findByMemberEntityAndYear(@Param("member") MemberEntity member, @Param("year") int year);

    @Query("SELECT h FROM HealthInfo h WHERE h.memberEntity = :memberEntity AND h.createdAt BETWEEN :startOfDay AND :endOfDay")
        List<HealthInfo> findByDateTimeRange(
        @Param("memberEntity") MemberEntity memberEntity,
        @Param("startOfDay") LocalDateTime startOfDay,
        @Param("endOfDay") LocalDateTime endOfDay
        );
}
