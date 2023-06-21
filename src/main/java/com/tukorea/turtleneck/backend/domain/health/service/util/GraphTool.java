package com.tukorea.turtleneck.backend.domain.health.service.util;

import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;

import java.util.List;
import java.util.Map;

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
}
