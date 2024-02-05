package com.delivery.domain.storefile.repository;

import com.delivery.domain.storefile.entity.StoreFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreFileRepository extends JpaRepository<StoreFileEntity,Long> {
    Optional<StoreFileEntity> findByStoreEntity_Id(Long storeId);
}