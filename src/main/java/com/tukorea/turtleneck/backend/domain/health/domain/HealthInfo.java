package com.tukorea.turtleneck.backend.domain.health.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "health")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthInfo {
    @Id
    private Long id;
    private String nickname;

    private Long redCnt;

    private Long yellowCnt;

    private Long totalCnt;

    @Builder
    public HealthInfo(String nickname, Long redCnt, Long yellowCnt, Long totalCnt){
        this.nickname = nickname;
        this.redCnt = redCnt;
        this.yellowCnt = yellowCnt;
        this.totalCnt = totalCnt;
    }
}
