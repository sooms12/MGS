package com.icia.mgs.controller;

import com.icia.mgs.service.OrdertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrdertController {

    private final OrdertService otsvc;
    // 결제 페이지 이동
    @GetMapping("/kakaopay")
    public String kakaopay() {
        return "/order/orderPage";
    }

    // 결제
    @GetMapping("/order")
    public String order() {
        return "/order/orderPage";
    }

    // 예약 내역 출력
    @GetMapping("/myOrder")
    public ModelAndView myOrder() {
        return otsvc.myOrder();
    }
}