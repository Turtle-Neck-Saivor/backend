package com.tukorea.turtleneck.backend.domain.member.domain;


import com.tukorea.turtleneck.backend.global.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "email", unique = true)
    private String emailId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "turtleneck_status")
    private String turtleNeckStatus;

    @Column(name = "turtleneck_photo")
    private String turtleNeckPhoto;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "age")
    private Integer age;

    @Builder
    public MemberEntity(String emailId, String password, String nickname, String turtleNeckStatus, String turtleNeckPhoto, Sex sex, Integer age) {
        this.emailId = emailId;
        this.password = password;
        this.nickname = nickname;
        this.turtleNeckStatus = turtleNeckStatus;
        this.turtleNeckPhoto = turtleNeckPhoto;
        this.isActive = true;
        this.sex = sex;
        this.age = age;
    }

}
