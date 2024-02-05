package com.delivery.domain.point.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PointController {
    @GetMapping("/pay")
    public String iamport(HttpSession session, Model model){

        return "pay";
    }
    @GetMapping("/getLoginName")
    @ResponseBody
    public String getName(HttpSession session){

        String loginName = (String) session.getAttribute("loginName");
        System.out.println(loginName);
        return loginName;
    }
}
