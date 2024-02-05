package com.delivery.domain.menu.repository;

import com.delivery.domain.menu.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

    List<MenuEntity> findAllByStoreEntity_Id(Long storeId);


}
