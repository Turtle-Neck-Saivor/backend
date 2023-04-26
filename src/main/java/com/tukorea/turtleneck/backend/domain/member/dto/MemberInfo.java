package com.tukorea.turtleneck.backend.domain.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberInfo {
    private Long memberId;
    private String emailId;

}
