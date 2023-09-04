package com.tukorea.turtleneck.backend.domain.member.service;

import com.tukorea.turtleneck.backend.domain.member.dao.MemberRepository;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.domain.member.dto.request.MemberCreateRequest;
import com.tukorea.turtleneck.backend.domain.member.dto.request.MemberLogInRequest;
import com.tukorea.turtleneck.backend.domain.member.dto.mapper.MemberMapper;
import com.tukorea.turtleneck.backend.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;
    private final MemberMapper mapper;

    public MemberEntity register(MemberCreateRequest request) {
        return repository.save(mapper.toEntity(request));
    }

    public String simpleLogin(MemberLogInRequest request) {
        MemberEntity memberEntity = repository.findMemberEntityByEmailId(request.getEmailId())
                .orElseThrow(NotFoundMemberException::new);
        return memberEntity.getNickname();
    }
}
