package com.tukorea.turtleneck.backend.domain.health.domain;


import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.global.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "health")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MemberEntity memberEntity;

    private int redCnt;

    private int yellowCnt;

    private int greenCnt;
    private double normalizedRedCnt;

    private double normalizedYellowCnt;

    private double normalizedGreenCnt;
    private double shoulderAngle;

    private double headAngle;

    private double neckAngle;

    private double distanceMonitor;

    @Builder
    public HealthInfo(MemberEntity memberEntity, int redCnt, int yellowCnt, int greenCnt, double shoulderAngle, double headAngle, double neckAngle, double distanceMonitor){
        int totalCnt = redCnt + yellowCnt + greenCnt;
        this.memberEntity = memberEntity;
        this.redCnt = redCnt;
        this.yellowCnt = yellowCnt;
        this.greenCnt = greenCnt;
        this.isActive = true;
        this.shoulderAngle = shoulderAngle;
        this.headAngle = headAngle;
        this.neckAngle = neckAngle;
        this.distanceMonitor = distanceMonitor;
        this.normalizedGreenCnt = (double) greenCnt / totalCnt;
        this.normalizedRedCnt = (double) redCnt / totalCnt;
        this.normalizedYellowCnt = (double) yellowCnt / totalCnt;
    }
}
