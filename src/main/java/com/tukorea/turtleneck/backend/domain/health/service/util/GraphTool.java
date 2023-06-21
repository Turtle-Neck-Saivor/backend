package com.tukorea.turtleneck.backend.domain.health.service.util;

import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GraphTool {
    public static long calculatePortion(List<HealthInfo> infoList) {
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
    public static void addHealthInfo(Map<WeekOfDay, List<HealthInfo>> map, WeekOfDay day, HealthInfo healthInfo) {
        List<HealthInfo> list = map.get(day);
        list.add(healthInfo);
    }

    public static long calculateAverage(List<HealthInfo> infoList) {
        if(infoList.isEmpty()){
            return 0;
        }
        double totalGreen = 0, totalYellow = 0, totalRed = 0;
        for (HealthInfo info : infoList) {
            totalGreen += info.getGreenCnt();
            totalYellow += info.getYellowCnt();
            totalRed += info.getRedCnt();
        }
        double userData = (10 * totalGreen + 27 * totalYellow + 60 * totalRed) /  97.0;
//        double result = (1.0 / userData) * 100;
        return (long) userData;
    }
}
