package com.delivery.domain.member.dto;

import com.delivery.domain.member.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동으로 만들어줌
@AllArgsConstructor // 필드를 모두다 매개변수라는 생성자를 만들어준다?
@ToString // dto 객체가 가지고 있는 필드값을 출력할때 쓰는 toString 롬복 ㅇㅇ

// 회원 정보에 대한 정보들을 필드로 선언하고 사용
public class MemberDTO {
//    @NotEmpty(message = "아이디 쳐라")
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberNickname;
    private String memberAddress;
    private String memberPoint;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDTO.setMemberNickname(memberEntity.getMemberNickname());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        memberDTO.setMemberPoint(memberEntity.getMemberPoint());
        return memberDTO;
    }
}
