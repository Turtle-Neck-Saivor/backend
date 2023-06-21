package com.tukorea.turtleneck.backend.domain.health.service;


import com.tukorea.turtleneck.backend.domain.health.dao.HealthRepository;
import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.DayGraphInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.WeekGraphInfo;
import com.tukorea.turtleneck.backend.domain.member.dao.MemberRepository;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GraphService {
    private final HealthRepository healthRepository;
    private final MemberRepository memberRepository;

    public DayGraphInfo getDayGraphInfo(LocalDate date, String nickname){
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);
        List<HealthInfo> infoList = healthRepository.findByDay(memberEntity, date);
        long portion = calculatePortion(infoList);
        return DayGraphInfo.builder().portion(portion).build();
    }

    public WeekGraphInfo getWeekGraphInfo(LocalDate date, String nickname) {
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        List<HealthInfo> infoList = healthRepository.findByWeek(memberEntity, startOfWeek, endOfWeek);
        if (infoList == null) {
            infoList = new ArrayList<>();
        }
        Map<WeekOfDay, List<HealthInfo>> map = new EnumMap<>(WeekOfDay.class);
        for (WeekOfDay day : WeekOfDay.values()) {
            map.put(day, new ArrayList<>());
        }
        for(HealthInfo info : infoList) {
            switch (info.getDate().getDayOfWeek()) {
                case MONDAY:
                    addHealthInfo(map, WeekOfDay.MONDAY, info);
                    break;
                case TUESDAY:
                    addHealthInfo(map, WeekOfDay.TUESDAY, info);
                    break;
                case WEDNESDAY:
                    addHealthInfo(map, WeekOfDay.WEDNESDAY, info);
                    break;
                case THURSDAY:
                    addHealthInfo(map, WeekOfDay.THURSDAY, info);
                    break;
                case FRIDAY:
                    addHealthInfo(map, WeekOfDay.FRIDAY, info);
                    break;
                case SATURDAY:
                    addHealthInfo(map, WeekOfDay.SATURDAY, info);
                    break;
                case SUNDAY:
                    addHealthInfo(map, WeekOfDay.SUNDAY, info);
                    break;
            }
        }
        return WeekGraphInfo.builder()
                .mon(calculatePortion(map.get(WeekOfDay.MONDAY)))
                .tue(calculatePortion(map.get(WeekOfDay.TUESDAY)))
                .wed(calculatePortion(map.get(WeekOfDay.WEDNESDAY)))
                .thu(calculatePortion(map.get(WeekOfDay.THURSDAY)))
                .fri(calculatePortion(map.get(WeekOfDay.FRIDAY)))
                .sat(calculatePortion(map.get(WeekOfDay.SATURDAY)))
                .sun(calculatePortion(map.get(WeekOfDay.SUNDAY)))
                .build();
    }

    private long calculatePortion(List<HealthInfo> infoList) {
        if(infoList.isEmpty()){
            return 0;
        }
        double redCnt = 0.0;
        double yellowCnt = 0.0;
        double greenCnt = 0.0;
        for(HealthInfo info : infoList){
            redCnt = redCnt + info.getRedCnt();
            yellowCnt = yellowCnt + info.getYellowCnt();
            greenCnt = greenCnt + info.getGreenCnt();
        }
        double portion =  ((redCnt + yellowCnt) / (redCnt + yellowCnt + greenCnt)) * 100;
        return (long) portion;
    }

    private enum WeekOfDay {
        MONDAY, TUESDAY, THURSDAY, WEDNESDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private void addHealthInfo(Map<WeekOfDay, List<HealthInfo>> map, WeekOfDay day, HealthInfo healthInfo) {
        List<HealthInfo> list = map.get(day);
        list.add(healthInfo);
    }
}
