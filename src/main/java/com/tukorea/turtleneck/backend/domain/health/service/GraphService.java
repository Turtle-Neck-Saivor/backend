package com.tukorea.turtleneck.backend.domain.health.service;


import com.tukorea.turtleneck.backend.domain.health.dao.HealthRepository;
import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.response.*;
import com.tukorea.turtleneck.backend.domain.health.service.util.GraphTool;
import com.tukorea.turtleneck.backend.domain.health.service.util.WeekOfDay;
import com.tukorea.turtleneck.backend.domain.member.dao.MemberRepository;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GraphService {
    private final HealthRepository healthRepository;
    private final MemberRepository memberRepository;

    public DayGraphInfo getDayGraphInfo(LocalDate date, String nickname){
        try{
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);
        List<HealthInfo> infoList = healthRepository.findByDay(memberEntity, date);
        long portion = GraphTool.calculatePortion(infoList);
        return DayGraphInfo.builder().portion(portion).build();
        }  catch (Exception e) {
            e.printStackTrace();  
        }
        return null;
    }

    public WeekGraphInfo getWeekGraphInfo(LocalDate date, String nickname) {
    try {
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);
        
        LocalDateTime startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay();
        LocalDateTime endOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).atTime(23, 59, 59);

        List<HealthInfo> infoList = healthRepository.findByWeek(memberEntity, startOfWeek, endOfWeek);

        if (infoList == null) {
            infoList = new ArrayList<>();
        }

        Map<WeekOfDay, List<HealthInfo>> map = new EnumMap<>(WeekOfDay.class);
        for (WeekOfDay day : WeekOfDay.values()) {
            map.put(day, new ArrayList<>());
        }
        
        for (HealthInfo info : infoList) {
            WeekOfDay day = WeekOfDay.valueOf(info.getCreatedAt().getDayOfWeek().name());
            GraphTool.addHealthInfo(map, day, info);
        }
        
        return new WeekGraphInfo(map);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

public MonthGraphInfo getMonthGraphInfo(LocalDate date, String nickname) {
    MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
            .orElseThrow(NotFoundMemberException::new);
    List<DayInfo> infoList = new ArrayList<>();
    for (int i = 1; i <= date.lengthOfMonth(); i++) {
        LocalDate nowDate = date.withDayOfMonth(i);
        LocalDateTime startOfDay = nowDate.atStartOfDay();
        LocalDateTime endOfDay = nowDate.atTime(23, 59, 59);
        
        // Use the new findByDateTimeRange method
        List<HealthInfo> healthInfos = healthRepository.findByDateTimeRange(memberEntity, startOfDay, endOfDay);
        
        if (healthInfos == null || healthInfos.isEmpty()) {
            infoList.add(DayInfo.builder().x(i).y(0L).build());
        } else {
            double average = GraphTool.calculateAverage(healthInfos);
            infoList.add(DayInfo.builder().x(i).y((long) average).build());
        }
    }
    return MonthGraphInfo.builder().infoList(infoList).build();
}


    public YearGraphInfo getYearGraphInfo(LocalDate date, String nickname){
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);
        List<HealthInfo> infoList = healthRepository.findByMemberEntityAndYear(memberEntity, date.getYear());
        Map<Month, List<HealthInfo>> monthlyHealthInfos = Arrays.stream(Month.values())
                .collect(Collectors.toMap(
                        month -> month,
                        month -> Optional.of(infoList.stream()
                                .filter(info -> info.getCreatedAt().getMonth() == month)
                                .collect(Collectors.toList())).orElse(new ArrayList<>())
                ));
        Map<Month, Double> monthlySlopes = new HashMap<>();
        for (Map.Entry<Month, List<HealthInfo>> entry : monthlyHealthInfos.entrySet()) {
            SimpleRegression regression = new SimpleRegression();
            for (HealthInfo info : entry.getValue()) {
                double userData = GraphTool.calculateAverage(List.of(info));
                regression.addData(info.getCreatedAt().getDayOfMonth(), userData);
            }
            monthlySlopes.put(entry.getKey(), regression.getSlope());
        }
        return new YearGraphInfo(monthlySlopes);
    }
}
