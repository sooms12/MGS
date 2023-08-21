package com.icia.mgs.controller;

import com.icia.mgs.dto.NaverShoppingDTO;
import com.icia.mgs.service.NaverShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NaverShoppingController {

    private final NaverShoppingService nsvc;

    // nsCrawling
    @GetMapping("nsCrawling")
    public List<NaverShoppingDTO> nsCrawling(@RequestParam String keyword) {

        System.out.println(keyword);
        return nsvc.nsCrawling(keyword);
    }

}
