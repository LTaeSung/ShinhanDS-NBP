package com.delivery.domain.orderMenu.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderMenuDto { // 폼에서 받아온거 넣어주는 용
    private String menuId;
    private String menuName;
    private String quantity;
    private String menuPrice;
//    //주문 아이디
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_delivery_id")
//    private OrderDelivery orderDelivery;
//
//    //메뉴 아이디///
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id")
//    private MenuEntity menuEntity;

//    private Long orderId; // ^
//    private Long menuId; // ^
//
//    private String menuName; // 메뉴 이름
//    private int payment; // 메뉴 가격
//    private int quantity; //수량
//
//    public static OrderMenuDto toOrderMenuDto(OrderDelivery orderDelivery, MenuEntity menuEntity, OrderMenu orderMenu){
//        return new OrderMenuDto(
//                orderMenu.getId(),
//                orderDelivery.getId(),
//                menuEntity.getId(),
//                menuEntity.getName(),
//                menuEntity.getPrice(),
//                orderMenu.getQuantity()
//        );
//    }
}
