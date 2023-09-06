package com.tukorea.turtleneck.backend.domain.health.controller;

import com.tukorea.turtleneck.backend.domain.health.dto.request.RecordInfo;
import com.tukorea.turtleneck.backend.domain.health.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/health")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://cowabugi.com", allowedHeaders = "*")
public class HealthContorller {
    private final HealthService service;
    @PostMapping
    public ResponseEntity<Boolean> recordHealth(@RequestBody List<RecordInfo> request){
        service.recordInfo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }
}
