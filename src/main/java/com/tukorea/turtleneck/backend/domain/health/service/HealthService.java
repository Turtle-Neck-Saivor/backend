package com.tukorea.turtleneck.backend.domain.health.service;

import com.tukorea.turtleneck.backend.domain.health.dao.HealthRepository;
import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.InfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final HealthRepository repository;
    public HealthInfo recordInfo(InfoRequest request){
        HealthInfo info = HealthInfo.builder()
                .nickname(request.getNickname())
                .redCnt(request.getRedCnt())
                .yellowCnt(request.getYellowCnt())
                .totalCnt(request.getTotalCnt())
                .build();
        return repository.save(info);
    }
}
