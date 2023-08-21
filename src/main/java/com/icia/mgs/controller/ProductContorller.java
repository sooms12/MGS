package com.icia.mgs.controller;

import com.icia.mgs.dto.ProductDTO;
import com.icia.mgs.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProductContorller {

    private final ProductService psvc;

    // 상품 등록 페이지 이동
    @GetMapping("/pRegiForm/{cNum}")
    public ModelAndView pRegiForm(@PathVariable int cNum){
        return psvc.pRegiForm(cNum);
    }

    // 상품 등록
    @PostMapping("/pRegister")
    public ModelAndView pRegister(@ModelAttribute ProductDTO product){
        return psvc.pRegister(product);
    }

    // 상품 목록 페이지 이동
    @GetMapping("/pList")
    public String pList(){
        return "product/list";
    }

    // 상품 정보 상세보기
    @GetMapping("/pView/{pNum}")
    public ModelAndView pView(@PathVariable int pNum){
        return psvc.pView(pNum);
    }

    // 상품 정보 수정 페이지 이동
    @GetMapping("/pModiForm/{pNum}")
    public ModelAndView pModiForm(@PathVariable int pNum){
        return psvc.pModiForm(pNum);
    }

    // 상품 정보 수정
    @PostMapping("/pModify")
    public ModelAndView pModify(@ModelAttribute ProductDTO product){
        return psvc.pModify(product);
    }

    // 상품 정보 삭제
    @GetMapping("/pDelete/{pNum}")
    public ModelAndView pDelete(@PathVariable int pNum){
        return psvc.pDelete(pNum);
    }

}
