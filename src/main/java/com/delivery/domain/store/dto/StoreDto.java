package com.delivery.domain.store.dto;

import com.delivery.domain.owner.entity.OwnerEntity;
import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.storefile.entity.StoreFileEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreDto {
    private Long id;
    private Long ownerId;
    private String name;
    private String description;
    private String category;

    private StoreFileEntity storeFileEntity;


    public static StoreDto toStoreDto(StoreEntity storeEntity, OwnerEntity ownerEntity, StoreFileEntity storeFileEntity){
        return new StoreDto(
                storeEntity.getId(),
                ownerEntity.getId(),
                storeEntity.getName(),
                storeEntity.getDescription(),
                storeEntity.getCategory(),
                storeFileEntity
        );
    }



}
