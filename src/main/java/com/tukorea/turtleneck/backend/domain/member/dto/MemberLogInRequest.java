package com.tukorea.turtleneck.backend.domain.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberLogInRequest {
    @NotBlank
    private String emailId;
    @NotBlank
    private String password;
}
