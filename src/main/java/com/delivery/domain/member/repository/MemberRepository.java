package com.delivery.domain.member.repository;

import com.delivery.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    // 인터페이스는 추상 메서드
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    //Optional : null 방지


    //1번 게시물의 전체 댓글



}
