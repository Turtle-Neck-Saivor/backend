package com.tukorea.turtleneck.backend.domain.health.service;


import com.tukorea.turtleneck.backend.domain.health.dao.HealthRepository;
import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.*;
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
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GraphService {
    private final HealthRepository healthRepository;
    private final MemberRepository memberRepository;
    private final GraphTool tools;

    public DayGraphInfo getDayGraphInfo(LocalDate date, String nickname){
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);
        List<HealthInfo> infoList = healthRepository.findByDay(memberEntity, date);
        long portion = GraphTool.calculatePortion(infoList);
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
                case MONDAY: GraphTool.addHealthInfo(map, WeekOfDay.MONDAY, info); break;
                case TUESDAY: GraphTool.addHealthInfo(map, WeekOfDay.TUESDAY, info); break;
                case WEDNESDAY: GraphTool.addHealthInfo(map, WeekOfDay.WEDNESDAY, info); break;
                case THURSDAY: GraphTool.addHealthInfo(map, WeekOfDay.THURSDAY, info); break;
                case FRIDAY: GraphTool.addHealthInfo(map, WeekOfDay.FRIDAY, info); break;
                case SATURDAY: GraphTool.addHealthInfo(map, WeekOfDay.SATURDAY, info); break;
                case SUNDAY: GraphTool.addHealthInfo(map, WeekOfDay.SUNDAY, info); break;
            }
        }
        return new WeekGraphInfo(map);
    }

    public MonthGraphInfo getMonthGraphInfo(LocalDate date, String nickname){
        MemberEntity memberEntity = memberRepository.findMemberEntityByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);
        List<DayInfo> infoList = new ArrayList<>();
        for(int i = 1; i <= date.lengthOfMonth(); i++){
            LocalDate nowDate = date.withDayOfMonth(i);
            double average = GraphTool.calculateAverage(healthRepository.findByDay(memberEntity, nowDate));
            infoList.add(DayInfo.builder().x(i).y((long) average).build());
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
                                .filter(info -> info.getDate().getMonth() == month)
                                .collect(Collectors.toList())).orElse(new ArrayList<>())
                ));
        Map<Month, Double> monthlySlopes = new HashMap<>();
        for (Map.Entry<Month, List<HealthInfo>> entry : monthlyHealthInfos.entrySet()) {
            SimpleRegression regression = new SimpleRegression();
            for (HealthInfo info : entry.getValue()) {
                double userData = GraphTool.calculateAverage(List.of(info));
                regression.addData(info.getDate().getDayOfMonth(), userData);
            }
            monthlySlopes.put(entry.getKey(), regression.getSlope());
        }
        return new YearGraphInfo(monthlySlopes);
    }
}
