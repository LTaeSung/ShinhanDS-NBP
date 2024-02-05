package com.delivery.domain.store.api;

import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.store.repository.StoreRepository;
import com.delivery.domain.store.service.StoreService;
import com.delivery.domain.storefile.entity.StoreFileEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class StoreApi {

    private final StoreRepository storeRepository;
    private final StoreService storeService;

    @Autowired
    public StoreApi(StoreRepository storeRepository, StoreService storeService) {
        this.storeRepository = storeRepository;
        this.storeService = storeService;

    }

    @GetMapping("/get-more-data")
    public ResponseEntity<List<StoreEntity>> getMoreData(@RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "pageSize", defaultValue = "8") int pageSize) {
        // 페이징 처리를 위한 Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, pageSize);
        log.info(pageable.toString());

        // 해당 페이지의 데이터를 가져옴
        Page<StoreEntity> dummyStorePage = storeRepository.findAll(pageable);
        log.info("aaa: " + dummyStorePage.toString());
        List<StoreEntity> newData = dummyStorePage.getContent();
        log.info("bbbb: " + newData.toString());

        // ResponseEntity를 사용하여 클라이언트에 응답 데이터 전송
        return ResponseEntity.ok().body(newData);
    }

    @GetMapping("/get-initial-images")
    public ResponseEntity<List<StoreFileEntity>> getImages(){
        List<StoreEntity> storeDto1 = storeRepository.findAll();
        storeDto1.forEach( a -> log.info(String.valueOf(a.getId())));

        List<StoreFileEntity> storeFileEntityList = new ArrayList<>();
        storeDto1.forEach(data -> {
                    storeFileEntityList.add(storeService.EditStores(data.getId()).get());
                }
        );

        storeFileEntityList.forEach(a -> log.info(String.valueOf(a.getId())));

        return ResponseEntity.ok().body(storeFileEntityList);
    }

//    @PostMapping("/new/store/{ownerId}/create")
//    public ResponseEntity<StoreDto> create(@PathVariable Long ownerId, @RequestBody StoreDto storeDto) {
//
//        log.info("ownerId = {}", ownerId);
//
//        log.info("dummyStore = {}", storeDto);
//
//        storeService.save(storeDto, ownerId);
//        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 성공!!
//    }
}
