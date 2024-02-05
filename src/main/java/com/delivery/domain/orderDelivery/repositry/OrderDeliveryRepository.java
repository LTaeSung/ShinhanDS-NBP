package com.delivery.domain.orderDelivery.repositry;

import com.delivery.domain.orderDelivery.entiy.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//스프링 데이터 JPA 리포지토리를 쓰면
//@Repository 안붙여도 된다!
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {
    // save(S) : 새로운 엔티티는 저장하고 이미 있는 엔티티는 병합한다.
    // delete(T) : 엔티티 하나를 삭제한다. 내부에서 EntityManager.remove() 호출
    // findById(ID) : 엔티티 하나를 조회한다. 내부에서 EntityManager.find() 호출
    // getOne(ID) : 엔티티를 프록시로 조회한다. 내부에서 EntityManager.getReference() 호출
    // findAll(…) : 모든 엔티티를 조회한다. 정렬( Sort )이나 페이징( Pageable ) 조건을 파라미터로 제공할 수 있다.
    // List<OrderDelivery> findAllByMemberEntity_Id(Long MemberId);
}
