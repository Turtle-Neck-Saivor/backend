package com.tukorea.turtleneck.backend.domain.member.dao;

import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
