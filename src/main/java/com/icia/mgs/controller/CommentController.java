package com.icia.mgs.controller;

import com.icia.mgs.dto.ReplyDTO;
import com.icia.mgs.dto.pCommentDTO;
import com.icia.mgs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private ModelAndView mav;

    private final CommentService ctsvc;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    private final HttpSession session;



    // rvWrite : 리뷰 작성
    @PostMapping("/rvWrite")
    public String rvWrite(@ModelAttribute pCommentDTO pco){
        System.out.println("리뷰 작성 : " + pco);

        ctsvc.rvWrite(pco);
        return "redirect:/sView/" + pco.getSNum();

    }

    // qaWrite : 상품 문의글 작성
    @PostMapping("/qaWrite")
    public ModelAndView qaWrite(@ModelAttribute pCommentDTO pco){
        return ctsvc.qaWrite(pco);
    }

    // qaModify : 문의글 수정
    @PostMapping("/qaModify")
    public ModelAndView qaModify(@ModelAttribute pCommentDTO pco){

        System.out.println("문의글 수정 controller : " + pco);

        return ctsvc.qaModify(pco);
    }

    // reWrite : 문의글 답글 작성
    @PostMapping("/reWrite")
    public ModelAndView reWrite(@ModelAttribute ReplyDTO reply){

        System.out.println("답글 작성 controller : " + reply);
        return ctsvc.reWrite(reply);
    }

    // reModify : 문의글 답글 수정
    @PostMapping("/reModify")
    public ModelAndView reModify(@ModelAttribute ReplyDTO reply){

        System.out.println("답글 수정 controller : " + reply);

        return ctsvc.reModify(reply);
    }


}
