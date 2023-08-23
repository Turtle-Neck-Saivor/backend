package com.tukorea.turtleneck.backend.domain.health.controller;


import com.tukorea.turtleneck.backend.domain.health.dto.DayGraphInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.MonthGraphInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.WeekGraphInfo;
import com.tukorea.turtleneck.backend.domain.health.dto.YearGraphInfo;
import com.tukorea.turtleneck.backend.domain.health.service.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/health/graphs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://cowabugi.com", allowedHeaders = "*")
public class GraphController {

    private final GraphService graphService;

    @GetMapping("/day")
    public ResponseEntity<DayGraphInfo> getDayGraph(
            @RequestParam String nickname,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok().body(graphService.getDayGraphInfo(date, nickname));
    }

    @GetMapping("/week")
    public ResponseEntity<WeekGraphInfo> getWeekGraph(
            @RequestParam String nickname,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok().body(graphService.getWeekGraphInfo(date, nickname));
    }

    @GetMapping("/month")
    public ResponseEntity<MonthGraphInfo> getMonthGraph(
            @RequestParam String nickname,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok().body(graphService.getMonthGraphInfo(date, nickname));
    }

    @GetMapping("/year")
    public ResponseEntity<YearGraphInfo> getYearGraph(
            @RequestParam String nickname,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok().body(graphService.getYearGraphInfo(date, nickname));
    }
}
