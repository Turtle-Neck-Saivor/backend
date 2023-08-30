package com.tukorea.turtleneck.backend.domain.health.service;

import com.tukorea.turtleneck.backend.domain.health.dao.HealthRepository;
import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.request.RecordRequest;
import com.tukorea.turtleneck.backend.domain.member.dao.MemberRepository;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final HealthRepository healthRepository;
    private final MemberRepository memberRepository;
    public HealthInfo recordInfo(RecordRequest request){
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(request.getNickname())
                .orElseThrow(NotFoundMemberException::new);
        HealthInfo info = HealthInfo.builder()
                .memberEntity(memberEntity)
                .redCnt(request.getRedCnt())
                .yellowCnt(request.getYellowCnt())
                .greenCnt(request.getGreenCnt())
                .shoulderAngle(request.getShoulderAngle())
                .headAngle(request.getHeadAngle())
                .distanceMonitor(request.getDistanceMonitor())
                .build();
        return healthRepository.save(info);
    }
}
