package com.icia.mgs.controller;

import com.icia.mgs.dto.CustomerDTO;
import com.icia.mgs.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private ModelAndView mav;

    private final CustomerService ccsvc;

    // csList : 고객센터 이동
    @GetMapping("csList")
    public String csList(){
        return "customer/CustomerService";
    }


    // csView : 고객문의 게시글 상세보기
    @GetMapping("/csView/{csNum}")
    public ModelAndView csView(@PathVariable int csNum){
        return ccsvc.csView(csNum);
    }

    // csModiForm : 고객문의 게시글 수정 페이지 이동
    @GetMapping("/csModiForm/{csNum}")
    public ModelAndView csModiForm(@PathVariable int csNum){ return ccsvc.csModiForm(csNum); }

    // csModify : 고객문의 게시글 수정
    @PostMapping("/csModify")
    public ModelAndView csModify(@ModelAttribute CustomerDTO customer){ return ccsvc.csModify(customer); }

    // csWriteForm : 게시글 작성 페이지 이동
    @GetMapping("csWriteForm")
    public String csWriteForm(){
        return "customer/write";
    }

    // csWrite : 게시글 작성
    @PostMapping("csWrite")
    public ModelAndView csWrite(@ModelAttribute CustomerDTO customer){
        return ccsvc.csWrite(customer);
    }

    // csDelete : 게시글 삭제
    @GetMapping("/csDelete/{csNum}")
    public ModelAndView csDelete(@PathVariable int csNum){
        return ccsvc.csDelete(csNum);
    }


}
