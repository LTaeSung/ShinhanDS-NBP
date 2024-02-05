package com.delivery.domain.orderMenu.controller;


import com.delivery.domain.orderMenu.dto.OrderMenuDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderMenuController {


    @GetMapping("/customer/orderPay")
    public String getAllOrders(Model model ,@ModelAttribute("processedOrders") ArrayList<OrderMenuDto> order) {
        // 실제 데이터는 데이터베이스에서 가져오거나, 임시로 저장된 데이터를 사용하면 됩니다.

        // 모델에 데이터 추가
        model.addAttribute("processedOrders", order);

        // 해당 뷰 페이지로 이동
        return "html/order/orderPay";
    }




}
