package com.delivery.domain.orderDelivery.service;

import com.delivery.domain.member.entity.MemberEntity;
import com.delivery.domain.member.repository.MemberRepository;
import com.delivery.domain.orderDelivery.dto.OrderDeliveryDto;
import com.delivery.domain.orderDelivery.entiy.OrderDelivery;
import com.delivery.domain.orderDelivery.entiy.Status;
import com.delivery.domain.orderDelivery.repositry.OrderDeliveryRepository;
import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderDeliveryService {

    private final OrderDeliveryRepository orderDeliveryRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    //등록하는 거니깐 기본 트랜잭션 걸어주기

    @Transactional //주문하기
    public OrderDeliveryDto save(OrderDeliveryDto orderDeliveryDto, Long memberID, Long storeId){

        //메뉴아이디랑 받아와야할듯
        // 처음 주믄을 하면 요청수락대기 단계
        Optional<MemberEntity> targetMember = memberRepository.findById(memberID);
        Optional<StoreEntity> targetStore = storeRepository.findById(storeId);

        if(targetMember.isPresent() && targetStore.isPresent()){
            OrderDelivery orderDelivery = OrderDelivery.toEntity(orderDeliveryDto, targetMember.get());
            OrderDelivery saveEntity = orderDeliveryRepository.save(orderDelivery);
            //주문정보 저장하고 주문 상품 담기


            return OrderDeliveryDto.toDto(saveEntity);
        }
        return null;
    }

    //주문 목록 가져오기
    public List<OrderDelivery> findAllByMemberEntity_Id(Long id){
        Optional<MemberEntity> targetMember = memberRepository.findById(id);
        if (targetMember.isPresent()){
            // return orderDeliveryRepository.findAllByMemberEntity_Id(id);
        }

        return null;
    }

    //주문 상세 페이지
    public OrderDeliveryDto findByID(Long id){
        Optional<OrderDelivery> targetOrderDelivery = orderDeliveryRepository.findById(id);

        if (targetOrderDelivery.isPresent()){
            return OrderDeliveryDto.toDto(targetOrderDelivery.get());
        }

        return null;
    }

    //주문 취소 -> 상태 업데이트
    public OrderDeliveryDto cancel(Long id){
        Optional<OrderDelivery> targetOrderDelivery = orderDeliveryRepository.findById(id);

        if (targetOrderDelivery.isPresent()){
            OrderDelivery findEntity = targetOrderDelivery.get();
            findEntity.cancel(Status.CANCEL);
            return OrderDeliveryDto.toDto(findEntity);
        }
        return null;
    }
}