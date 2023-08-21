package com.icia.mgs.controller;

import com.icia.mgs.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequiredArgsConstructor
public class CartController {
    private CartService cartService;
    private  ModelAndView mav;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/ctlist")
    public String ctlist(){
        return "cart/list";
    }

}
