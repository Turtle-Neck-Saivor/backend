package com.tukorea.turtleneck.backend.domain.health.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfoRequest {
    private String nickname;
    private Long redCnt;
    private Long yellowCnt;
    private Long totalCnt;
}
