package com.tukorea.turtleneck.backend.domain.health.dto.request;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordRequest {
    private List<RecordInfo> infos;
}
