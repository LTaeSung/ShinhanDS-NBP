package com.delivery.domain.menu.dto;

import com.delivery.domain.menu.entity.MenuEntity;
import com.delivery.domain.store.entity.StoreEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuRequestDto {
    private Long id;

    private String name;

    @JsonProperty("dummy_store_id")
    private Long storeId;

    private int price;

    private String content;

    private String category;

    private String multipartFile;

//
//    public static MenuRequestDto toMenuDto(MenuEntity dummyMenuEntity, StoreEntity storeEntity){
//        Long storeId = (storeEntity != null) ? storeEntity.getId() : null;
//
//        return new MenuRequestDto(
//                dummyMenuEntity.getId(),
//                dummyMenuEntity.getName(),
//                storeId,
//                dummyMenuEntity.getPrice(),
//                dummyMenuEntity.getContent(),
//                dummyMenuEntity.getCategory()
//        );
//    }

    // dto -> entity로 바꾸기
//    public MenuEntity toEntity(StoreEntity storeEntity) {
//        return new MenuEntity(
//                this.id,
//                storeEntity,
//                this.name,
//                this.price,
//                this.content,
//                this.category
//        );
//    }
}
