package com.tukorea.turtleneck.backend.domain.member.service;

import com.tukorea.turtleneck.backend.domain.member.dao.MemberRepository;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.domain.member.dto.request.MemberCreateRequest;
import com.tukorea.turtleneck.backend.domain.member.dto.request.MemberLogInRequest;
import com.tukorea.turtleneck.backend.domain.member.dto.mapper.MemberMapper;
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

    public Boolean simpleLogin(MemberLogInRequest request) {
        return repository.findMemberEntityByEmailId(request.getEmailId()).isPresent();
    }
}
