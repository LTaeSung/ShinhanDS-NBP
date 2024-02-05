package com.delivery.domain.point.entity;

import com.delivery.domain.member.entity.MemberEntity;
import com.delivery.domain.orderDelivery.entiy.OrderDelivery;
import com.delivery.domain.point.dto.PointDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "point")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 회원 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    // 사용 내역
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_delivery_id")
    private OrderDelivery orderDelivery;

    // 유형 (CHARGE, REDEEM)
    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private Type type;

    // 포인트 추가(차감) 시간
    @CreatedDate
    private LocalDateTime pointTime;

    // 금액?
    @Column
    private int amount;

    public static PointEntity toEntity(PointDto pointDto, MemberEntity memberEntity, OrderDelivery orderDelivery) {
        return new PointEntity(
                pointDto.getId(),
                memberEntity,
                orderDelivery,
                pointDto.getType(),
                pointDto.getPointTime(),
                pointDto.getAmount()
        );
    }
}

