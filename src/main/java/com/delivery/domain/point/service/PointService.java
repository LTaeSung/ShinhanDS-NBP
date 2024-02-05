package com.delivery.domain.point.service;

import com.delivery.domain.member.repository.MemberRepository;
import com.delivery.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    private final MemberRepository memberRepository;

//    @Transactional
//    public PointEntity save(Long id, PointDto pointDto){
//        Optional<MemberEntity> targetMember = memberRepository.findById(id);
//
//    }
}
