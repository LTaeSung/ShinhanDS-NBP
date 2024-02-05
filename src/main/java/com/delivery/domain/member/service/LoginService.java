package com.delivery.domain.member.service;

import com.delivery.domain.member.entity.MemberEntity;
import com.delivery.domain.member.repository.MemberRepository;

public class LoginService {

    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity Login(String memberEmail, String memberPassword){

        return memberRepository.findByMemberEmail(memberEmail)
                .filter(m -> m.getMemberPassword().equals(memberPassword))
                .orElse(null);
    }
}
