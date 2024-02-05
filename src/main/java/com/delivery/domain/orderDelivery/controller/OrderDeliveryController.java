package com.delivery.domain.orderDelivery.controller;

import com.delivery.domain.orderDelivery.dto.OrderDeliveryDto;
import com.delivery.domain.orderDelivery.service.OrderDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class OrderDeliveryController {
    private final OrderDeliveryService orderDeliveryService;

    @GetMapping("/new/{id}")
    public String createOrderDeliverForm(Model model) {
        OrderDeliveryDto orderDeliveryDto = new OrderDeliveryDto();
        orderDeliveryDto.setAddress("123123");
        model.addAttribute("entity", orderDeliveryDto);
        return "html/order/order";
    }

//    @GetMapping("/orderPay")
//    public String orderPay(Model model) {
////        model.addAttribute("orderMenu", orderRequests);
//        return "html/order/orderPay";
//    }

    @GetMapping("/orderTest")
    public String test111(){
        return "html/order/orderP";
    }

//    @PostMapping("/new")
//    public String createOrderDeliver(HttpSession session, @ModelAttribute OrderDeliveryDto orderDeliveryDto){
//        Long memberId =(Long) session.getAttribute("memberId");
//        orderDeliveryService.save(orderDeliveryDto, memberId,1l);
//        return "html/customer/customer";
//    }

//    @GetMapping("detail/{memberID}")
}
