package com.example.memberapi.member.entity;

import com.example.memberapi.base.entity.BaseEntity;
import com.example.memberapi.member.type.MemberRole;
import com.example.memberapi.member.type.MemberStatus;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")


    private Long id;
    private String username;
    private String password;
    private String email;

    private boolean emailAuth;

    private String emailAuthKey;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;


    @Enumerated(EnumType.STRING)
    private MemberRole roles;

    private LocalDateTime deletedAt;





}
