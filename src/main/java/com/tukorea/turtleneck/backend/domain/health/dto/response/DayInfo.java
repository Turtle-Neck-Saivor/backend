package com.tukorea.turtleneck.backend.domain.health.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayInfo {
    private int x;
    private long y;
}
