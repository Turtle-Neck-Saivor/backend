package com.tukorea.turtleneck.backend.domain.member.service;

import com.tukorea.turtleneck.backend.domain.member.dao.MemberRepository;
import com.tukorea.turtleneck.backend.domain.member.domain.MemberEntity;
import com.tukorea.turtleneck.backend.domain.member.dto.MemberCreateRequest;
import com.tukorea.turtleneck.backend.domain.member.dto.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;
    private final MemberMapper mapper;


    public MemberEntity register(MemberCreateRequest request) { return repository.save(mapper.toEntity(request)); }
}
