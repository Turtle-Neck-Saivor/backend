package com.tukorea.turtleneck.backend.domain.member.dto.request;

import com.tukorea.turtleneck.backend.domain.member.domain.Sex;
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
    private Sex sex;
    private Integer age;
}
