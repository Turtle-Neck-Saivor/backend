package com.tukorea.turtleneck.backend.domain.health.service;


import com.tukorea.turtleneck.backend.domain.health.dao.HealthRepository;
import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.DayGraphInfo;
import com.tukorea.turtleneck.backend.domain.member.dao.MemberRepository;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GraphService {
    private final HealthRepository healthRepository;
    private final MemberRepository memberRepository;

    public DayGraphInfo makeDayGraph(LocalDate date, String nickname){
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);
        List<HealthInfo> infoList = healthRepository.findByMemberEntityAndDate(memberEntity, date);
        double redCnt = 0.0;
        double yellowCnt = 0.0;
        double greenCnt = 0.0;
        for(HealthInfo info : infoList){
            redCnt = redCnt + info.getRedCnt();
            yellowCnt = yellowCnt + info.getYellowCnt();
            greenCnt = greenCnt + info.getGreenCnt();
        }
        double portion =  ((redCnt + yellowCnt) / (redCnt + yellowCnt + greenCnt)) * 100;
        return DayGraphInfo.builder().portion(((long) portion)).build();
    }
}
