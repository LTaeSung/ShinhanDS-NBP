package com.delivery.domain.orderDelivery.entiy;


import com.delivery.domain.member.entity.MemberEntity;
import com.delivery.domain.orderDelivery.dto.OrderDeliveryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_delivery")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDelivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_delivery_id")
    private Long id;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "member_id")
    // private MemberEntity memberEntity; //회원 아이디

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "store_name")
//    private StoreEntity storeEntity; //식당 아이디

    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private Status status;//배달 요청 상태

    @CreatedDate
    private LocalDateTime requestTime; //배달 요청 시간

    @Column(nullable = false, length = 50)
    private String address; //배달 주소

    @Column
    private String requestContent; //배달 요청사항

    @Column(nullable = false)
    private LocalDateTime deliveryTime; // 배달 시간

    //==주문 상태 변경==//
    public void cancel(Status status){
        this.status = status;
    }



    //==엔티티 변환==//
    public static OrderDelivery toEntity(OrderDeliveryDto orderDeliveryDto, MemberEntity memberEntity){
        return new OrderDelivery(
                orderDeliveryDto.getId(),
                // memberEntity,

                orderDeliveryDto.getStatus(),
                orderDeliveryDto.getRequestTime(),
                orderDeliveryDto.getAddress(),
                orderDeliveryDto.getRequestContent(),
                orderDeliveryDto.getDeliveryTime()
        );
    }
}