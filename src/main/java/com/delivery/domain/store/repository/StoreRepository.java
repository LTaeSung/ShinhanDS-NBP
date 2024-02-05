package com.delivery.domain.store.repository;

import com.delivery.domain.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<StoreEntity> findAll();

    Optional<StoreEntity> findById(Long id);

    Optional<StoreEntity> findByOwnerEntity_Id(Long aLong);

//    Optional<StoreEntity> findByStoreEntity_Id(Long aLong);

    // 대소문자 구별하지 않고 검색
    List<StoreEntity> findByNameContainingIgnoreCase(String name);

}
