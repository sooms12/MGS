package com.icia.mgs.controller;

import com.icia.mgs.dto.CompanyDTO;
import com.icia.mgs.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService csvc;


    // 회사 등록 페이지 이동
    @GetMapping("/cRegiForm")
    public String cRegiForm(){
        return "company/register";
    }

    // 회사 등록
    @PostMapping("/cRegister")
    public ModelAndView cRegister(@ModelAttribute CompanyDTO company){
        return csvc.cRegister(company);
    }

    // 회사 목록 페이지 이동
    @GetMapping("/cList")
    public String cList(){
        return "company/list";
    }

    // 회사 정보 상세보기
    @GetMapping("/cView/{cNum}")
    public ModelAndView cView(@PathVariable int cNum){
        return csvc.cView(cNum);
    }

    // 회사 정보 수정 페이지 이동
    @GetMapping("/cModiForm/{cNum}")
    public ModelAndView cModiForm(@PathVariable int cNum){
        return csvc.cModiForm(cNum);
    }

    // 회사 정보 수정
    @PostMapping("/cModify")
    public ModelAndView cModify(@ModelAttribute CompanyDTO company){
        return csvc.cModify(company);
    }


    // 회사 정보 삭제
    @GetMapping("/cDelete/{cNum}")
    public ModelAndView cDelete(@PathVariable int cNum){

        return csvc.cDelete(cNum);
    }


}
