package com.delivery.domain.menu.entity;

import com.delivery.domain.menu.dto.MenuDto;
import com.delivery.domain.store.entity.StoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "menu")
public class MenuEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    //식당 아이디
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity storeEntity;

    private String name;

    private int price;

    private String content;

    private String category;

    //== 엔티티 변환 메서드 ==//
    public static MenuEntity toEntity(MenuDto menuDto, StoreEntity storeEntity){
        return new MenuEntity(
                menuDto.getId(),
                storeEntity,
                menuDto.getName(),
                menuDto.getPrice(),
                menuDto.getContent(),
                menuDto.getCategory()
        );
    }
}
