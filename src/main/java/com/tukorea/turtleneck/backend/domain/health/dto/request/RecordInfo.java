package com.tukorea.turtleneck.backend.domain.health.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordInfo {
    @NotNull
    private String nickname;
    private int redCnt;
    private int yellowCnt;
    private int greenCnt;
    private double shoulderAngle;
    private double headAngle;
    private double neckAngle;
    private double distanceMonitor;
}
