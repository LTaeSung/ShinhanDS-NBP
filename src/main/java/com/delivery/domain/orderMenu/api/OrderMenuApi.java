package com.delivery.domain.orderMenu.api;

import com.delivery.domain.orderMenu.dto.OrderMenuDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class OrderMenuApi {

    private List<OrderMenuDto> storedOrderRequests = new ArrayList<>();

    @PostMapping("/order/orderContent")
    public List<OrderMenuDto> processOrder(@RequestBody List<OrderMenuDto> orderRequests, Model model, RedirectAttributes redirectAttributes) {
//        model.addAttribute()
        orderRequests.forEach(a -> log.info("데이터가 들어와라 - " + a.getMenuId() + a.getMenuName() + a.getMenuPrice() + a.getQuantity()));

        // 서버 측에서 받은 데이터를 저장
        storedOrderRequests = new ArrayList<>(orderRequests);
        // 리다이렉트 시 데이터를 전달
        redirectAttributes.addFlashAttribute("processedOrders", orderRequests);

        // 성공적으로 처리되었다는 응답 반환
        return orderRequests;
    }
    @GetMapping("/api/processedOrders")
    public List<OrderMenuDto> getProcessedOrders() {
        // 저장된 데이터를 클라이언트로 전달
        return storedOrderRequests;
    }
    @GetMapping("/api/orderContent")
    @ResponseBody
    public List<OrderMenuDto> getOrderContent() {
        // 서버에서 데이터를 조회하거나 가공하여 반환
        List<OrderMenuDto> orderRequests = retrieveOrderRequests();
        return orderRequests;
    }

    // 임시로 데이터를 생성하는 메서드
    private List<OrderMenuDto> retrieveOrderRequests() {
        List<OrderMenuDto> orderRequests = new ArrayList<>();
        orderRequests.add(new OrderMenuDto("1", "Menu1", "2", "20.00"));
        orderRequests.add(new OrderMenuDto("2", "Menu2", "1", "15.00"));
        // ... 이하 생략 ...
        return orderRequests;
    }

//    @GetMapping("/order/orderContent")
//    public List<OrderMenuDto> processOrderGet(
//            @RequestParam("menuId") String menuId,
//            @RequestParam("menuName") String menuName,
//            @RequestParam("menuPrice") String menuPrice,
//            @RequestParam("quantity") String quantity,
//            Model model, RedirectAttributes redirectAttributes) {
//
//        List<OrderMenuDto> orderMenuDtos =
//
//        OrderMenuDto orderRequest = new OrderMenuDto(menuId, menuName, menuPrice, quantity);
//
//        // 모델에 데이터 추가
//        model.addAttribute("processedOrders", Collections.singletonList(orderRequest));
//
//        // 리다이렉트 시 데이터를 전달
//        redirectAttributes.addFlashAttribute("processedOrders", Collections.singletonList(orderRequest));
//
//        // 성공적으로 처리되었다는 응답 반환
//        return "redirect:/customer/orderPay";
//    }

}
