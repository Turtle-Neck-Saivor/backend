package com.tukorea.turtleneck.backend.domain.health.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordRequest {
    @NotNull
    private String nickname;
    private Integer redCnt;
    private Integer yellowCnt;
    private Integer greenCnt;
    private Long shoulderAngle;
    private Long headAngle;
    private Long distanceMonitor;
}
