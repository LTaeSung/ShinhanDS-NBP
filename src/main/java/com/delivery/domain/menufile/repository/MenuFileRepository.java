package com.delivery.domain.menufile.repository;

import com.delivery.domain.menufile.entity.MenuFileEntity;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuFileRepository extends JpaRepository<MenuFileEntity,Long> {
    MenuFileEntity findByMenuEntity_Id(Long id);
    List<MenuFileEntity> findAllByMenuEntity_id(Long id);
}