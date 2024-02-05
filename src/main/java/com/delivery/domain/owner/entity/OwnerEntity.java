package com.delivery.domain.owner.entity;

import com.delivery.domain.owner.dto.OwnerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "owner")
public class OwnerEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true) // unique 제약조건 추가
    private String ownerEmail; //member_email로 들어감

    @Column
    private String ownerPassword;

    @Column
    private String ownerName;

    @Column
    private String ownerPhone;

    @Column
    private String ownerNickname;

    @Column
    private String ownerAddress;

    public static OwnerEntity toMemberEntity(OwnerDTO ownerDTO) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setOwnerEmail(ownerDTO.getOwnerEmail());
        ownerEntity.setOwnerPassword(ownerDTO.getOwnerPassword());
        ownerEntity.setOwnerName(ownerDTO.getOwnerName());
        ownerEntity.setOwnerPhone(ownerDTO.getOwnerPhone());
        ownerEntity.setOwnerNickname(ownerDTO.getOwnerNickname());
        ownerEntity.setOwnerAddress(ownerDTO.getOwnerAddress());
        return ownerEntity;
    }

    public static OwnerEntity toUpdateMemberEntity(OwnerDTO ownerDTO) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(ownerDTO.getId());  // 그래서 여기에 id 값을 셋 해둔거임 update 쿼리 쓰려고
        ownerEntity.setOwnerEmail(ownerDTO.getOwnerEmail());
        ownerEntity.setOwnerPassword(ownerDTO.getOwnerPassword());
        ownerEntity.setOwnerName(ownerDTO.getOwnerName());
        ownerEntity.setOwnerPhone(ownerDTO.getOwnerPhone());
        ownerEntity.setOwnerNickname(ownerDTO.getOwnerNickname());
        ownerEntity.setOwnerAddress(ownerDTO.getOwnerAddress());
        return ownerEntity;
    }

}
