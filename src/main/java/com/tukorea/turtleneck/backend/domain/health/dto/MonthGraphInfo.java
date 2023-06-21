package com.tukorea.turtleneck.backend.domain.health.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MonthGraphInfo {
    private List<DayInfo> infoList;
}
