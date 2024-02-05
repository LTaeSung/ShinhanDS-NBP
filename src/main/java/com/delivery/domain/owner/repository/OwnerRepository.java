package com.delivery.domain.owner.repository;

import com.delivery.domain.owner.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {

    // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    // 인터페이스는 추상 메서드
    Optional<OwnerEntity> findByOwnerEmail(String memberEmail);
    //Optional : null 방지
}
