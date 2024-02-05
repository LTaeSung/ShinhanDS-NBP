package com.delivery.controller;

import com.delivery.domain.menu.service.MenuService;
import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.store.repository.StoreRepository;
import com.delivery.domain.store.service.StoreService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/customer")
@Controller
@Slf4j
@RequiredArgsConstructor
public class CustomerController {


    private final StoreRepository storeRepository;
    private final StoreService storeService;
    private final MenuService menuService;
    @GetMapping("/")
    public String test(HttpSession session, Model model){

        // 페이지 번호와 페이지 크기를 기반으로 페이징된 데이터를 가져옴, 내가 설정한 페이징 크기 8
        Pageable pageable = PageRequest.of(0, StoreEntity.PAGE_SIZE);
        // 페이징된 데이터를 가져오고, dummyStorePage에 페이징된 결과가 저장
        Page<StoreEntity> dummyStorePage = storeRepository.findAll(pageable);
        // 페이징된 결과에서 현재 페이지의 데이터를 추출하여 dummyStoreEntityList에 저장,
        // 이 리스트에는 현재 페이지의 더미 스토어 엔터티가 포함
        List<StoreEntity> storeEntityList = dummyStorePage.getContent();



        // 로그 찍어보기
        log.info("로그를 찍어보자: " + storeEntityList.toString());

//        StoreDto storeDto = storeService.findById((Long) session.getAttribute("ownerId"));
//        List<StoreEntity> storeDto1 = storeRepository.findAll();
//
//        storeDto1.forEach( a -> log.info(String.valueOf(a.getId())));
//
//        List<StoreFileEntity> storeFileEntityList = new ArrayList<>();
//        storeDto1.forEach(data -> {
//            storeFileEntityList.add(storeService.EditStores(data.getId()).get());
//            model.addAttribute("storeImage", storeFileEntityList);
//                }
//        );
////        storeFileEntityList.forEach(a -> log.info(String.valueOf(a.getId())));

//        storeDto1.forEach(data -> {
//            Optional<StoreFileEntity> storeData = storeService.EditStores(data.getId());
//
//            storeData.ifPresent(store -> {
//                // 값이 있는 경우에만 model에 추가
//                model.addAttribute("storeImage", store);
//            });
//            // 값이 없을 때 별도의 처리를 추가하지 않음
//        });


//        List<StoreFileEntity> storeImages = new ArrayList<>();
//        storeDto1.forEach(data -> {
//            Optional<StoreFileEntity> storeData = storeService.EditStores(data.getId());
//            storeData.ifPresent(storeImages::add);
//        });
//
//        model.addAttribute("storeImages", storeImages);



        // 세션에 현재 페이지 번호를 저장
        session.setAttribute("currentPage", 1);
//        model.addAttribute("store",storeDto);
        model.addAttribute("stores", storeEntityList);

        // 전체 페이지 수와 현재 페이지 번호를 모델에 추가
        model.addAttribute("totalPages", dummyStorePage.getTotalPages());
        // 클라이언트에서 페이지네이션하기 좋음
        model.addAttribute("currentPage", 1);

//        List<DummyStoreEntity> dummyStoreEntity = dummyStoreRepository.findAll();
//        log.info(dummyStoreEntity.toString());
//        session.setAttribute("stores", dummyStoreEntity);
//        List<String> store = (List<String>) session.getAttribute("stores");
//        model.addAttribute("stores", store);

        String loginName = (String) session.getAttribute("loginName");
        boolean isLoggedIn = true;
        if(loginName == null){
            System.out.println("아이디 틀렸다~!");
            isLoggedIn = false;
            return "html/customer/test";
        }
        model.addAttribute("loginName", loginName);
        model.addAttribute("loggedIn", isLoggedIn);


//        session.setAttribute("loginName", loginName);

        log.info("이동하기 loginEmail: " + session.getAttribute("loginEmail"));
        return "html/customer/test";

    }

}
