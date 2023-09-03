package com.tukorea.turtleneck.backend.domain.health.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RecordInfo {
    @NotNull
    private String nickname;
    private Integer redCnt;
    private Integer yellowCnt;
    private Integer greenCnt;
    private Long shoulderAngle;
    private Long headAngle;
    private Long distanceMonitor;
}
