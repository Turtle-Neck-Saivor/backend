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
                .sex(request.getSex())
                .age(request.getAge())
                .build();
    }

    public MemberInfo toInfo(MemberEntity entity) {
        return MemberInfo.builder()
                .emailId(entity.getEmailId())
                .password(entity.getPassword())
                .nickname(entity.getNickname())
                .turtleNeckPhoto(entity.getTurtleNeckPhoto())
                .turtleNeckStatus(entity.getTurtleNeckStatus())
                .sex(entity.getSex())
                .age(entity.getAge())
                .build();
    }
}
