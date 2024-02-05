package com.delivery.domain.menu.api;

import com.delivery.domain.menu.dto.MenuDto;
import com.delivery.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MenuApi {

    private final MenuService menuService;

    // @PostMapping("/store/{storeId}/create")
    // public ResponseEntity<Void> create(@RequestBody List<MenuDto> menuDtoList, @PathVariable Long storeId) {
    //     log.info("dummyMenuDtoList = {}", menuDtoList);
    //     for (MenuDto dto : menuDtoList) {
    //         log.info("DummyMenuDto - {}", dto);
    //     }
    //     log.info("b = " + storeId.toString());
    //     MenuDto menuDto = menuService.save(menuDtoList, storeId);
    //     if (menuDto != null) {
    //         // 기존 코드
    //         log.info("dummyMenu - " + menuDto.toString());
    //         // ...
    //     } else {
    //         // null인 경우 처리
    //         // 예: 로깅 또는 다른 처리
    //         log.error("dummyMenuDto is null");
    //     }
    //
    //     return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 성공!!
    // }
}
