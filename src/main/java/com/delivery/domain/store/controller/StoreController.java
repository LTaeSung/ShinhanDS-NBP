package com.delivery.domain.store.controller;

import com.delivery.domain.menu.dto.MenuDto;
import com.delivery.domain.menu.service.MenuService;
import com.delivery.domain.store.dto.StoreDto;
import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.store.repository.StoreRepository;
import com.delivery.domain.store.service.StoreService;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@RequestMapping("/owner/store")
@Slf4j
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final MenuService menuService;
    private final StoreRepository storeRepository;

    //키워드 검색
    @GetMapping("/searchResults")
    public String searchResultsPage(@RequestParam String searchTerm, Model model) {
        List<StoreEntity> searchResults = storeService.searchStoresByName(searchTerm);
        model.addAttribute("searchResults", searchResults);

        return "/html/store/StoreFindResult"; // 검색 결과를 보여줄 HTML 파일명
    }

    // 점주 가게 등록 폼
    @GetMapping("/new")
    public String storeSaveForm(HttpSession session, Model model) {
        Long ownerId = (Long) session.getAttribute("ownerId");
        model.addAttribute("ownerId",ownerId);
        model.addAttribute("storeDto", new StoreDto());
        return "layouts/html/regist";

    }

    //점주 가게 등록 저장
    @PostMapping("/new")
    public String storeSave(@ModelAttribute StoreDto storeDto, HttpSession session, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("가게 등록입니다:" + file.toString());
        System.out.println(file);
        storeService.save(storeDto, (Long) session.getAttribute("ownerId"),file);
        return "redirect:/owner/";
    }

    //    //점주 가게 수정 폼
//    @GetMapping("/update")
//    public String storeUpdateForm(Model model, HttpSession session) {
//        StoreDto storeDto = storeService.findById((Long) session.getAttribute("ownerId"));
//
//        model.addAttribute("storeDto",storeDto);
//        return "layouts/html/update";
//    }
//
//    //점주 가게 수정
//    @PostMapping("/update")
//    public String storeUpdateForm(@ModelAttribute StoreDto storeDto, HttpSession session) {
//        log.info("수정폼에서 넘어온 폼 객체입니다."+storeDto);
//        storeService.update(storeDto,(Long) session.getAttribute("ownerId"));
//        return "redirect:/owner/store/update";
//    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        StoreDto storeDto1 = storeService.findById(id);
        StoreDto storeDto = storeService.findById((Long) session.getAttribute("ownerId"));
        log.info("스토어 디티오 참조값: "+storeDto);

        List<MenuDto> menuListEntity = menuService.findAllByStoreEntity_Id(id);

        Optional<StoreEntity> storeEntity = storeRepository.findById(id);

        log.info("메뉴 디테일 페이지"+menuListEntity.toString());

        model.addAttribute("store",storeDto1);
        model.addAttribute("stores", storeEntity.get());
        model.addAttribute("editStore", storeService.EditStores(id).get()); // 가게 사진
        model.addAttribute("editMenu", menuService.EditMenus(id)); // 메뉴 사진
        model.addAttribute("menuList", menuListEntity);

        // model.addAttribute("base64Image", storePicturePath);


        return "html/store/detail";
        // 예시로 null을 반환
    }

}

