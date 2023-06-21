package com.tukorea.turtleneck.backend.domain.health.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeekGraphInfo {
    private Long mon;
    private Long tue;
    private Long wed;
    private Long thu;
    private Long fri;
    private Long sat;
    private Long sun;
}
