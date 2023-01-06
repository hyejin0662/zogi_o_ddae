package com.example.memberapi.member.controller;


import com.example.memberapi.member.dto.LoginDto;
import com.example.memberapi.member.dto.SignUpDto;
import com.example.memberapi.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<Long> signUp(@RequestBody @Valid SignUpDto.SignUpRequestDto request) {
        return ResponseEntity.ok(memberService.registerMember(request));
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto.LoginRequestDto requestDto) {
        return ResponseEntity.ok(memberService.login(requestDto));
    }

}
