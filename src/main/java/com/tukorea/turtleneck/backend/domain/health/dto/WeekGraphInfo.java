package com.tukorea.turtleneck.backend.domain.health.dto;


import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.service.util.GraphTool;
import com.tukorea.turtleneck.backend.domain.health.service.util.WeekOfDay;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WeekGraphInfo {
    private Long mon;
    private Long tue;
    private Long wed;
    private Long thu;
    private Long fri;
    private Long sat;
    private Long sun;

    public WeekGraphInfo(Map<WeekOfDay, List<HealthInfo>> map) {
        this.mon = GraphTool.calculatePortion(map.get(WeekOfDay.MONDAY));
        this.tue = GraphTool.calculatePortion(map.get(WeekOfDay.TUESDAY));
        this.wed = GraphTool.calculatePortion(map.get(WeekOfDay.WEDNESDAY));
        this.thu = GraphTool.calculatePortion(map.get(WeekOfDay.THURSDAY));
        this.fri = GraphTool.calculatePortion(map.get(WeekOfDay.FRIDAY));
        this.sat = GraphTool.calculatePortion(map.get(WeekOfDay.SATURDAY));
        this.sun = GraphTool.calculatePortion(map.get(WeekOfDay.SUNDAY));
    }
}
