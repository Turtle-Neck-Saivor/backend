package com.tukorea.turtleneck.backend.domain.member.dto;

import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberEntity toEntity(MemberCreateRequest request) {
        return MemberEntity.builder()
                .emailId(request.getEmailId())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .turtleNeckStatus(request.getTurtleNeckStatus())
                .turtleNeckPhoto(request.getTurtleNeckPhoto())
                .build();
    }

    public MemberInfo toInfo(MemberEntity entity) {
        return MemberInfo.builder()
                .memberId(entity.getMemberid())
                .emailId(entity.getEmailId())
                .build();
    }
}
