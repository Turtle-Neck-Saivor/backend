package com.tukorea.turtleneck.backend.domain.health.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RecordInfo {
    @NotNull
    private String nickname;
    private int redCnt;
    private int yellowCnt;
    private int greenCnt;
    private Long shoulderAngle;
    private Long headAngle;
    private Long neckAngle;
    private Long distanceMonitor;
}
