package com.tukorea.turtleneck.backend.domain.member.dao;

import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Query("SELECT m FROM MemberEntity m WHERE m.emailId = :emailId " +
            "AND m.isActive = true")
    Optional<MemberEntity> findMemberEntityByEmailId(String emailId);
}
