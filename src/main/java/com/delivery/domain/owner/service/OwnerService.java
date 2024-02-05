package com.delivery.domain.owner.service;

import com.delivery.domain.owner.dto.OwnerDTO;
import com.delivery.domain.owner.entity.OwnerEntity;
import com.delivery.domain.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository; //Spring data jpa
    public void save(OwnerDTO ownerDTO) {
        // 1. dto -> entity 변환, 왜냐? 레퍼지토리로는 엔티티 객체를 넘거줘야하기 때문임
        // 2. repository의 save 메서드 호출
        OwnerEntity ownerEntity = OwnerEntity.toMemberEntity(ownerDTO);
        ownerRepository.save(ownerEntity); //save 메서드는 Jpa 제공 메서드
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    public OwnerDTO login(OwnerDTO ownerDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<OwnerEntity> byOwnerEmail = ownerRepository.findByOwnerEmail(ownerDTO.getOwnerEmail());
        if (byOwnerEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            OwnerEntity ownerEntity = byOwnerEmail.get(); // .get() 이 Optional을 벗겨내고 entity만 가져오는 것
            if (ownerEntity.getOwnerPassword().equals(ownerDTO.getOwnerPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                OwnerDTO dto = OwnerDTO.toMemberDTO(ownerEntity);
                // 엔티티를 어디까지쓸거냐? : 여기선 서비스까지만 쓰겠다 ~
                return dto;
            } else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return null;
        }
    }

    public List<OwnerDTO> findAll() {
        // memberREpository.findAll() 은 레포지토리가 제공하는 메서드
        // 레퍼지토리랑 관련된건 엔티티로 쓰기때문에

        List<OwnerEntity> ownerEntityList = ownerRepository.findAll();
        List<OwnerDTO> ownerDTOList = new ArrayList<>();

        // 위에선 엔티티 쓰고 돌려줄떈 dto 라서 다시 변환
        for (OwnerEntity ownerEntity : ownerEntityList) {
            ownerDTOList.add(OwnerDTO.toMemberDTO(ownerEntity));
        }
        return ownerDTOList;
    }

    public OwnerDTO findById(Long id) {
        Optional<OwnerEntity> optionalOwnerEntity = ownerRepository.findById(id); //또한 레포지의 메서드

        if (optionalOwnerEntity.isPresent()) {
            return OwnerDTO.toMemberDTO(optionalOwnerEntity.get());
        } else {
            return null;
        }

    }

    public OwnerDTO updateForm(String myEmail) {
        Optional<OwnerEntity> optionalOwnerEntity = ownerRepository.findByOwnerEmail(myEmail);
        if (optionalOwnerEntity.isPresent()) {
            return OwnerDTO.toMemberDTO(optionalOwnerEntity.get());
        } else {
            return null;
        }
    }

    public void update(OwnerDTO ownerDTO) {
        // repository.save는 id 값이 없으면 insert 쿼리 날려주고
        // 있으면 update 쿼리 날려줌
        ownerRepository.save(OwnerEntity.toUpdateMemberEntity(ownerDTO));
    }

    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    public String emailCheck(String ownerEmail) {
        Optional<OwnerEntity> byOwnerEmail = ownerRepository.findByOwnerEmail(ownerEmail);
        if (byOwnerEmail.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다.
            return null;
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }
}













