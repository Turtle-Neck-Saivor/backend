package com.tukorea.turtleneck.backend.domain.member.domain;


import com.tukorea.turtleneck.backend.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberid;

    // nullabe = false로 해볼까?
//    @NotBlank
    @Column(name = "email")
    private String emailId;

//    @NotBlank
    @Column(name = "password")
    private String password;

//    @NotBlank
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "turtleneck_status")
    private String turtleNeckStatus;

    @Column(name = "turtleneck_photo")
    private String turtleNeckPhoto;

    @Builder
    public MemberEntity(String emailId, String password, String nickname, String turtleNeckStatus, String turtleNeckPhoto) {
        this.emailId = emailId;
        this.password = password;
        this.nickname = nickname;
        this.turtleNeckStatus = turtleNeckStatus;
        this.turtleNeckPhoto = turtleNeckPhoto;
    }

    public void updateStatus(MemberStatus memberStatus) { this.turtleNeckStatus = turtleNeckStatus; }
    public void updatePhoto(MemberPhoto memberPhoto) { this.turtleNeckPhoto = turtleNeckPhoto; }
}
