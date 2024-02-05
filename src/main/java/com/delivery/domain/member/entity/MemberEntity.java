package com.delivery.domain.member.entity;

import com.delivery.domain.member.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true) // unique 제약조건 추가
    private String memberEmail; //member_email로 들어감

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String memberPhone;

    @Column
    private String memberNickname;

    @Column
    private String memberAddress;

    @Column
    private String memberPoint;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberNickname(memberDTO.getMemberNickname());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
        memberEntity.setMemberPoint(memberDTO.getMemberPoint());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());  // 그래서 여기에 id 값을 셋 해둔거임 update 쿼리 쓰려고
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberNickname(memberDTO.getMemberNickname());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
        memberEntity.setMemberPoint(memberDTO.getMemberPoint());
        return memberEntity;
    }

}
