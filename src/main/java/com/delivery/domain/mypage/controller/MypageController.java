package com.delivery.domain.mypage.controller;


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

@Controller // view 템플릿을 반환
@RequestMapping("/mypage")
@Slf4j
@RequiredArgsConstructor // final로 명시된 필드를 DI 적용해준다, autowired보다 좋다~~~~~
public class MypageController {

    private final StoreRepository storeRepository;
    private final StoreService storeService;
    private final MenuService menuService;

    @GetMapping("/orderlist")
    public String orderlist(Model model){

        return "html/mypage/orderlist";
    }

    @GetMapping("/info")
    public String info(Model model){

        return "html/mypage/info";
    }
    @GetMapping("/")
    public String mypage(Model model) {

        return "html/mypage/mypage";
    }
    @GetMapping("/bookmark")
    public String bookmark(HttpSession session, Model model){
        Pageable pageable = PageRequest.of(0, StoreEntity.PAGE_SIZE);
        Page<StoreEntity> dummyStorePage = storeRepository.findAll(pageable);
        // 페이징된 결과에서 현재 페이지의 데이터를 추출하여 dummyStoreEntityList에 저장,
        // 이 리스트에는 현재 페이지의 더미 스토어 엔터티가 포함
        List<StoreEntity> storeEntityList = dummyStorePage.getContent();

        session.setAttribute("currentPage", 1);
//        model.addAttribute("store",storeDto);
        model.addAttribute("stores1", storeEntityList.get(2));
        model.addAttribute("stores2", storeEntityList.get(5));

        // 전체 페이지 수와 현재 페이지 번호를 모델에 추가
        model.addAttribute("totalPages", dummyStorePage.getTotalPages());
        // 클라이언트에서 페이지네이션하기 좋음
        model.addAttribute("currentPage", 1);
        return "html/mypage/bookmark";
    }
    @GetMapping("/recharge")
    public String recharge(Model model){

        return "html/mypage/recharge";
    }
    @GetMapping("/setting")
    public String setting(Model model){

        return "html/mypage/setting";
    }

}
