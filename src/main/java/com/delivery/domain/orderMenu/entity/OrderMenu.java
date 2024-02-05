
package com.delivery.domain.orderMenu.entity;


//<<<<<<< HEAD
////package com.delivery.domain.orderMenu.entity;
////
////import com.delivery.domain.menu.entity.MenuEntity;
////import com.delivery.domain.orderDelivery.entiy.OrderDelivery;
////import com.delivery.domain.orderMenu.dto.OrderMenuDto;
////import jakarta.persistence.*;
////import lombok.AllArgsConstructor;
////import lombok.Getter;
////import lombok.NoArgsConstructor;
////import lombok.Setter;
////
////@Entity
////@Table(name = "order_menu")
////@Getter
////@Setter
////@NoArgsConstructor
////@AllArgsConstructor
////public class OrderMenu {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Column(name = "order_menu_id")
////    private Long id;
////
////    //주문 아이디
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "order_delivery_id")
////    private OrderDelivery orderDelivery;
////
////    //메뉴 아이디///
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "menu_id")
////    private MenuEntity menuEntity;
////
////
////    @Column(nullable = false)
////    private String menuName; // 메뉴 이름
////
////    @Column(nullable = false)
////    private int payment; // 메뉴 가격
////
////    @Column(nullable = false)
////    private int quantity; //수량
////
////    //== 가격 계산==//
////    public int calculationPrice(){
////        return getPayment() * getQuantity();
////    }
////    public static OrderMenu toOrderMenuEntity(OrderDelivery orderDelivery, MenuEntity menuEntity, OrderMenuDto orderMenuDto){
////        return new OrderMenu(
////                orderMenuDto.getId(),
////                orderDelivery,
////                menuEntity,
////                orderMenuDto.getMenuName(),
////                orderMenuDto.getPayment(),
////                orderMenuDto.getQuantity()
////        );
////    }
////
////
////}
//=======
//package com.delivery.domain.orderMenu.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "order_menu")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class OrderMenu {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_menu_id")
//    private Long id;
//
//
//    @Column(nullable = false)
//    private String menuName; // 메뉴 이름
//
//    @Column(nullable = false)
//    private int payment; // 메뉴 가격
//
//    @Column(nullable = false)
//    private int quantity; //수량
//
//    @Column(nullable = false)
//    private int totalAmount; // 최종 가격
//
//
//}
