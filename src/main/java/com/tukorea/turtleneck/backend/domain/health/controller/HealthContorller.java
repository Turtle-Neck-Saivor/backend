package com.tukorea.turtleneck.backend.domain.health.controller;

import com.tukorea.turtleneck.backend.domain.health.domain.HealthInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.RecordRequest;
import com.tukorea.turtleneck.backend.domain.health.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/health")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class HealthContorller {
    private final HealthService service;
    @PostMapping
    public ResponseEntity<HealthInfo> recordHealth(@RequestBody RecordRequest request){
        HealthInfo info = service.recordInfo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(info);
    }
}
