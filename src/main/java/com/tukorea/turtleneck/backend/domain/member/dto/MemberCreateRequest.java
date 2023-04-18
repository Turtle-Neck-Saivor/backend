package com.tukorea.turtleneck.backend.domain.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateRequest {

    @NotBlank
    private String emailId;

    private String password;

    private String nickname;

    private String turtleNeckStatus;

    private String turtleNeckPhoto;


}
