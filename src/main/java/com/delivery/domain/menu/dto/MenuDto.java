package com.delivery.domain.menu.dto;

import com.delivery.domain.menu.entity.MenuEntity;
import com.delivery.domain.menufile.entity.MenuFileEntity;
import com.delivery.domain.store.entity.StoreEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuDto {
    private Long id;

    private String name;

    @JsonProperty("dummy_store_id")
    private Long storeId;

    private int price;

    private String content;

    private String category;

    private MenuFileEntity menuFileEntity;


    public static MenuDto toMenuDto(MenuEntity menuEntity, Optional<StoreEntity> storeEntity, MenuFileEntity menuFileEntity){
        return new MenuDto(
                menuEntity.getId(),
                menuEntity.getName(),
                storeEntity.get().getId(),
                menuEntity.getPrice(),
                menuEntity.getContent(),
                menuEntity.getCategory(),
                menuFileEntity
        );
    }
}
