package com.tukorea.turtleneck.backend.domain.member.controller;

import com.tukorea.turtleneck.backend.domain.member.dto.MemberInfo;
import com.tukorea.turtleneck.backend.domain.member.dto.MemberCreateRequest;
import com.tukorea.turtleneck.backend.domain.member.dto.MemberLogInRequest;
import com.tukorea.turtleneck.backend.domain.member.dto.MemberMapper;
import com.tukorea.turtleneck.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class memberController {

    private final MemberService service;

    private final MemberMapper mapper;


    @PostMapping
    public ResponseEntity<MemberInfo> register(@Valid @RequestBody MemberCreateRequest request) {

        return ResponseEntity.status((HttpStatus.CREATED)).body(mapper.toInfo(service.register(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> simpleLogin(@Valid @RequestBody MemberLogInRequest request) {

        return ResponseEntity.status((HttpStatus.CREATED)).body(service.simpleLogin(request));
    }

//    @GetMapping("/photo")photo
}
