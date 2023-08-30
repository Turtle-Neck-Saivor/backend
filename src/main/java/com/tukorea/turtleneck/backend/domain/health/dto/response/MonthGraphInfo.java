package com.tukorea.turtleneck.backend.domain.health.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MonthGraphInfo {
    private List<DayInfo> infoList;
}
