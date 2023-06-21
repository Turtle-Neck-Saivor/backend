package com.tukorea.turtleneck.backend.domain.health.dto;

import com.tukorea.turtleneck.backend.domain.health.service.util.GraphTool;
import lombok.Builder;
import lombok.Data;

import java.time.Month;
import java.util.Map;

@Data
public class YearGraphInfo {
    private String jan;
    private String feb;
    private String mar;
    private String apr;
    private String may;
    private String jun;
    private String jul;
    private String aug;
    private String sept;
    private String oct;
    private String nov;
    private String dec;

    public YearGraphInfo(Map<Month, Double> monthlySlopes) {
        this.jan = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.JANUARY));
        this.feb = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.FEBRUARY));
        this.mar = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.MARCH));
        this.apr = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.APRIL));
        this.may = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.MAY));
        this.jun = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.JUNE));
        this.jul = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.JULY));
        this.aug = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.AUGUST));
        this.sept = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.SEPTEMBER));
        this.oct = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.OCTOBER));
        this.nov = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.NOVEMBER));
        this.dec = GraphTool.calculateMonthlyStatus(monthlySlopes.get(Month.DECEMBER));
    }
}
