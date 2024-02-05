package com.delivery.domain.owner.dto;

import com.delivery.domain.owner.entity.OwnerEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동으로 만들어줌
@AllArgsConstructor // 필드를 모두다 매개변수라는 생성자를 만들어준다?
@ToString // dto 객체가 가지고 있는 필드값을 출력할때 쓰는 toString 롬복 ㅇㅇ

// 회원 정보에 대한 정보들을 필드로 선언하고 사용
public class OwnerDTO {
//    @NotEmpty(message = "아이디 쳐라")
    private Long id;
    private String ownerEmail;
    private String ownerPassword;
    private String ownerName;
    private String ownerPhone;
    private String ownerNickname;
    private String ownerAddress;

    public static OwnerDTO toMemberDTO(OwnerEntity ownerEntity) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(ownerEntity.getId());
        ownerDTO.setOwnerEmail(ownerEntity.getOwnerEmail());
        ownerDTO.setOwnerPassword(ownerEntity.getOwnerPassword());
        ownerDTO.setOwnerName(ownerEntity.getOwnerName());
        ownerDTO.setOwnerPhone(ownerEntity.getOwnerPhone());
        ownerDTO.setOwnerNickname(ownerEntity.getOwnerNickname());
        ownerDTO.setOwnerAddress(ownerEntity.getOwnerAddress());
        return ownerDTO;
    }
}
