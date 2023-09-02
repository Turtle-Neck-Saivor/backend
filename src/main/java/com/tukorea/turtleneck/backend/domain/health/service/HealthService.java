package com.tukorea.turtleneck.backend.domain.health.service;

import com.tukorea.turtleneck.backend.domain.health.dao.HealthRepository;
import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.request.RecordInfo;
import com.tukorea.turtleneck.backend.domain.member.dao.MemberRepository;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final HealthRepository healthRepository;
    private final MemberRepository memberRepository;

    private HealthInfo recordSingleInfo(RecordInfo info){
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(info.getNickname())
                .orElseThrow(NotFoundMemberException::new);
        HealthInfo singleInfo = HealthInfo.builder()
                .memberEntity(memberEntity)
                .redCnt(info.getRedCnt())
                .yellowCnt(info.getYellowCnt())
                .greenCnt(info.getGreenCnt())
                .shoulderAngle(info.getShoulderAngle())
                .headAngle(info.getHeadAngle())
                .distanceMonitor(info.getDistanceMonitor())
                .build();
        return healthRepository.save(singleInfo);
    }

    public void recordInfo(List<RecordInfo> infos){
        for(RecordInfo info : infos){
            recordSingleInfo(info);
        }
    }
}
