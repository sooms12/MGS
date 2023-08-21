package com.icia.mgs.controller;

import com.icia.mgs.dto.SaleDTO;
import com.icia.mgs.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class SaleContorller {

    private final SaleService ssvc;

    // 판매 등록 페이지 이동
    @GetMapping("/sRegiForm/{pNum}")
    public ModelAndView sRegiForm(@PathVariable int pNum){
        return ssvc.sRegiForm(pNum);
    }

    // 판매 등록
    @PostMapping("/sRegister")
    public ModelAndView sRegister(@ModelAttribute SaleDTO sale){
        return ssvc.sRegister(sale);
    }

    // 판매 목록 페이지 이동
    @GetMapping("sList")
    public String sList(){
        return "sale/list";
    }

    // 상품 정보 상세보기
    @GetMapping("/sView/{sNum}")
    public ModelAndView sView(@PathVariable int sNum){
        return ssvc.sView(sNum);
    }

    // sModiForm 판매상품 수정페이지 이동
    @GetMapping("/sModiForm/{sNum}")
    public ModelAndView sModiForm(@PathVariable int sNum){
        System.out.println("sModiForm Controller : " + sNum);
        return ssvc.sModiForm(sNum);
    }

    // sModify 판매상품 수정
    @PostMapping("/sModify")
    public ModelAndView sModify(@ModelAttribute SaleDTO sale){
        System.out.println("sModify Controller : " + sale);
        return ssvc.sModify(sale);
    }

    // sDelete 판매상품 삭제
    @GetMapping("/sDelete/{sNum}")
    public ModelAndView sDelete(@PathVariable int sNum){
        System.out.println("sDelete Controller : " + sNum);
        return ssvc.sDelete(sNum);
    }

    // plView 좋아요 페이지에서 상세보기 이동
    @GetMapping("/plView/{pNum}")
    public ModelAndView plView(@PathVariable int pNum){
        return ssvc.plView(pNum);
    }

}
