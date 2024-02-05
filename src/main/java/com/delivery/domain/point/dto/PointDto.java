
package com.delivery.domain.point.dto;

import com.delivery.domain.point.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointDto {

    private Long id;
    private Type type;
    private LocalDateTime pointTime;
    private int amount;

    private Long memberId;
    private Long orderDeliveryId;

//    public static PointDto toDto(PointEntity pointEntity){
//        return new PointDto(
//                pointEntity.getId(),
//                pointEntity.getType(),
//                pointEntity.getPointTime(),
//                pointEntity.getAmount()
//        );
//    }
}
