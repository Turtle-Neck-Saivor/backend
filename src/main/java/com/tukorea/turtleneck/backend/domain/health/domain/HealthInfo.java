package com.tukorea.turtleneck.backend.domain.health.domain;


import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    private Long redCnt;

    private Long yellowCnt;

    private Long greenCnt;

    private LocalDate date;

    @Builder
    public HealthInfo(MemberEntity memberEntity, Long redCnt, Long yellowCnt, Long greenCnt, LocalDate date){
        this.memberEntity = memberEntity;
        this.redCnt = redCnt;
        this.yellowCnt = yellowCnt;
        this.greenCnt = greenCnt;
        this.date = date;
        this.isActive = true;
    }
}
