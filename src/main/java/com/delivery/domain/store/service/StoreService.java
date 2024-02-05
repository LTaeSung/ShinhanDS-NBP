package com.delivery.domain.store.service;


import com.delivery.domain.menu.repository.MenuRepository;
import com.delivery.domain.menufile.repository.MenuFileRepository;
import com.delivery.domain.owner.entity.OwnerEntity;
import com.delivery.domain.owner.repository.OwnerRepository;
import com.delivery.domain.store.dto.StoreDto;
import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.store.repository.StoreRepository;
import com.delivery.domain.storefile.entity.StoreFileEntity;
import com.delivery.domain.storefile.repository.StoreFileRepository;
import com.delivery.domain.storefile.service.StoreFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j

public class StoreService {

    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;
    private final StoreFileService storeFileService;
    private final StoreFileRepository storeFileRepository;
    private final MenuRepository menuRepository;
    private final MenuFileRepository menuFileRepository;
    //가게 키워드 검색
    public List<StoreEntity> searchStoresByName(String searchTerm) {
        return storeRepository.findByNameContainingIgnoreCase(searchTerm);
    }


    //가게 등록
    @Transactional
    public void save(StoreDto storeDto, Long ownerId, MultipartFile file) throws IOException {
        //먼저 점주 아이디 조회
        Optional<OwnerEntity> targetOwner = ownerRepository.findById(ownerId);

        if (targetOwner.isPresent()){
            //dto값을 받았으니 엔티티도 변환
            StoreEntity storeEntity = StoreEntity.toStoreEntity(storeDto, targetOwner.get());
            storeRepository.save(storeEntity);
            storeFileService.saveFile(file, storeEntity);
            log.info("성공적으로 저장");
        }
    }


    //가게조회
    public StoreDto findById(Long ownerId){
        //먼저 점주 아이디 조회
        Optional<OwnerEntity> targetOwner = ownerRepository.findById(ownerId);

        if(targetOwner.isPresent()){
            Optional<StoreEntity> storeEntity = storeRepository.findByOwnerEntity_Id(ownerId);
            Optional<StoreFileEntity> storeFileEntity = storeFileRepository.findByStoreEntity_Id(
                    storeEntity.get().getId());
            return StoreDto.toStoreDto(storeEntity.get(), targetOwner.get(), storeFileEntity.get());
        }
        else{
            return null;
        }


    }

    // 가게 상세 조회
    public Optional<StoreFileEntity> EditStores(Long id) {
        Optional<StoreEntity> targetStore = storeRepository.findById(id);
//        Long ownerId = (Long) session.getAttribute("ownerId");
        if (targetStore.isPresent()) {
            Optional<StoreFileEntity> storeFileEntity = storeFileRepository.findByStoreEntity_Id(id);
            return storeFileEntity;
        }
        return null;
    }




//
//    //가게 수정
//    @Transactional
//    public StoreDto update(StoreDto storeDto, Long ownerId){
//        Optional<StoreEntity> storeEntity = storeRepository.findByOwnerEntity_Id(ownerId);
//        Optional<OwnerEntity> ownerEntity = ownerRepository.findById(ownerId);
//        //원래 객체의 아이디를 넣어준다
//        storeDto.setId(storeEntity.get().getId());
//
//        //폼객체를 저장소에 넣을 엔티티도 변환
//        StoreEntity updateEntity = StoreEntity.toStoreEntity(storeDto, ownerEntity.get());
//
//        storeRepository.save(updateEntity);
//
//        return StoreDto.toStoreDto(updateEntity, ownerEntity.get());
//    }
//
//    //가게 삭제
//    @Transactional
//    public void delete(Long ownerId){
//        Optional<StoreEntity> storeEntity = storeRepository.findById(ownerId);
//        if(storeEntity.isPresent()){
//            storeRepository.delete(storeEntity.get());
//        }
//    }
}