package com.delivery.domain.orderDelivery.service;

import com.delivery.domain.orderDelivery.dto.OrderDeliveryDto;
import com.delivery.domain.orderDelivery.entiy.OrderDelivery;
import com.delivery.domain.orderDelivery.entiy.Status;
import com.delivery.domain.orderDelivery.repositry.OrderDeliveryRepository;
import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.store.repository.StoreRepository;
import groovy.util.logging.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDeliveryServiceTest {

    @Autowired
    private OrderDeliveryService orderDeliveryService;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    @Rollback(value = false)
    void 회워아이디랑_넣기() {
//        storeRepository.save(new StoreEntity(null,"나랑너랑","02-123-123",null,null,null,"마포구","ㅈㄷㄱ"));
        OrderDeliveryDto orderDeliveryDto = new OrderDeliveryDto(null, Status.WAIT, LocalDateTime.now(), "서울특별시", "맛있게 부탁드립니다.", LocalDateTime.now().plusMinutes(10));
        OrderDeliveryDto orderDelivery = orderDeliveryService.save(orderDeliveryDto, 2L,1L);

        List<OrderDelivery> allByMemberId = orderDeliveryService.findAllByMemberEntity_Id(2L);

        Assertions.assertThat(orderDelivery).isNotNull();
    }

}