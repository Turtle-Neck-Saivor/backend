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

    private Integer redCnt;

    private Integer yellowCnt;

    private Integer greenCnt;

    private Long shoulderAngle;

    private Long headAngle;

    private Long neckAngle;

    private Long distanceMonitor;

    @Builder
    public HealthInfo(MemberEntity memberEntity, Integer redCnt, Integer yellowCnt, Integer greenCnt, Long shoulderAngle, Long headAngle, Long neckAngle, Long distanceMonitor){
        this.memberEntity = memberEntity;
        this.redCnt = redCnt;
        this.yellowCnt = yellowCnt;
        this.greenCnt = greenCnt;
        this.isActive = true;
        this.shoulderAngle = shoulderAngle;
        this.headAngle = headAngle;
        this.neckAngle = neckAngle;
        this.distanceMonitor = distanceMonitor;
    }
}
