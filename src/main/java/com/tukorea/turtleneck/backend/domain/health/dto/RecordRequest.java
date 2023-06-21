package com.tukorea.turtleneck.backend.domain.health.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordRequest {
    @NotNull
    private String nickname;
    private Long redCnt;
    private Long yellowCnt;
    private Long greenCnt;
}
