package com.example.memberapi.member.service;

import static com.example.memberapi.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.example.memberapi.exception.CustomException;
import com.example.memberapi.jwt.JwtTokenProvider;
import com.example.memberapi.member.dto.LoginDto.LoginRequestDto;
import com.example.memberapi.member.dto.SignUpDto.SignUpRequestDto;
import com.example.memberapi.member.entity.Member;
import com.example.memberapi.member.repository.MemberRepository;
import com.example.memberapi.member.type.MemberRole;
import com.example.memberapi.member.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {


    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long registerMember(SignUpRequestDto requestDto) {
        Member newMember = Member.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .status(MemberStatus.ACTIVE)
                .roles(MemberRole.ROLE_USER)
                .build();

        memberRepository.save(newMember);

        return newMember.getId();
    }


    public String login(LoginRequestDto requestDto) {
        Member loginMember = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        return jwtTokenProvider.createToken(loginMember.getEmail(), loginMember.getRoles());
    }
}



