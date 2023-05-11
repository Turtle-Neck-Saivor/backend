package com.tukorea.turtleneck.backend.domain.health.domain;


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
