package com.icia.mgs.controller;

import com.icia.mgs.dto.RequestDTO;
import com.icia.mgs.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class RequestController {

    private ModelAndView mav;

    private final RequestService rqsvc;

    // requestForm : 상품요청 페이지 이동
    @GetMapping("/requestForm")
    public String requestForm(){
        return "request/request";
    }

    // requestWrite 요청보내기
    @PostMapping("/requestWrite")
    public ModelAndView requestWrite(@ModelAttribute RequestDTO request){
        System.out.println(request);
        return rqsvc.requestWrite(request);
    }

    // rqList 요청리스트
    @GetMapping("/rqList")
    public String rqList(){
        return "request/list";
    }

    // rqList2 회원이 보는 요청리스트
    @GetMapping("/rqList2/{mId}")
    public ModelAndView rqList2(@PathVariable String mId){
        return rqsvc.rqList2(mId);
    }

    // rqView 요청리스트 상세보기 관리자
    @GetMapping("/rqView/{rqNum}")
    public ModelAndView rqView(@PathVariable int rqNum){
        return rqsvc.rqView(rqNum);
    }

    // rqView 요청리스트 상세보기 회원
    @GetMapping("/rqView2/{rqNum}")
    public ModelAndView rqView2(@PathVariable int rqNum){
        return rqsvc.rqView2(rqNum);
    }

    // 요청신청서 수정페이지 이동 관리자
    @GetMapping("/rqModiForm/{rqNum}")
    public ModelAndView rqModiForm(@PathVariable int rqNum){
        return rqsvc.rqModiForm(rqNum);
    }

    // 요청신청서 수정페이지 이동 회원
    @GetMapping("/rqModiForm2/{rqNum}")
    public ModelAndView rqModiForm2(@PathVariable int rqNum){
        return rqsvc.rqModiForm2(rqNum);
    }

    // 요청신청서 수정 관리자
    @PostMapping ("/requestModify")
    public ModelAndView requestModify(@ModelAttribute RequestDTO request){
        System.out.println("request cotroller : "+request);
        return rqsvc.requestModify(request);
    }

    // 요청신청서 수정 회원
    @PostMapping ("/requestModify2")
    public ModelAndView requestModify2(@ModelAttribute RequestDTO request){
        System.out.println("request cotroller : "+request);
        return rqsvc.requestModify2(request);
    }

    // rqDelete 삭제 관리자
    @GetMapping("/rqDelete/{rqNum}")
    public ModelAndView rqDelete(@PathVariable int rqNum){
        return rqsvc.rqDelete(rqNum);
    }

    // rqDelete 삭제 회원
    @GetMapping("/rqDelete2/{rqNum}")
    public ModelAndView rqDelete2(@PathVariable int rqNum){
        return rqsvc.rqDelete2(rqNum);
    }


}
