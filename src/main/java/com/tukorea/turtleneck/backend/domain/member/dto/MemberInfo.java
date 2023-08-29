package com.tukorea.turtleneck.backend.domain.member.dto;

import com.tukorea.turtleneck.backend.domain.member.domain.Sex;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberInfo {
    private String emailId;

    private String password;

    private String nickname;

    private String turtleNeckStatus;

    private String turtleNeckPhoto;
    private Sex sex;
    private Integer age;
}
