package com.icia.mgs.controller;

import com.icia.mgs.dto.MemberDTO;
import com.icia.mgs.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService msvc;
    private final HttpSession session;

    private ModelAndView mav;

    // 인덱스 페이지 이동
    @GetMapping("/indexForm")
    public String indexForm(){
        return "index";
    }

    // 회원가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm() {
        return "member/join";
    }

    // 회원가입
    @PostMapping("/mJoin")
    public ModelAndView mJoin(@ModelAttribute MemberDTO member) {
        return msvc.mJoin(member);
    }

    // 카카오인증 회원가입
    @PostMapping("/oauth/mJoin2")
    public ModelAndView mJoin2(@ModelAttribute MemberDTO member) {
        return msvc.mJoin(member);
    }

    // 회원목록 페이지 이동
    @GetMapping("/mList")
    public String mList(){
        return "member/list";
    }

    // 로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(){
        return "member/login";
    }

    // 로그인
    @PostMapping("/mLogin")
    public ModelAndView mLogin(@ModelAttribute MemberDTO member) {
        return msvc.mLogin(member);
    }

    // 로그아웃
    @GetMapping("/mLogout")
    public String mLogout(){
        session.invalidate();
        return "index";
    }

    // 회원정보 상세보기
    @GetMapping("/mView/{mId}")
    public ModelAndView mView(@PathVariable String mId){

        return msvc.mView(mId);
    }

    // 회원정보 수정 페이지 이동
    @GetMapping("/mModiForm/{mId}")
    public ModelAndView mModiForm(@PathVariable String mId){

        return msvc.mModiForm(mId);
    }

    // 회원정보 수정
    @PostMapping("/mModify")
    public ModelAndView mModify(@ModelAttribute MemberDTO member){

        return msvc.mModify(member);
    }

    // 회원정보 삭제
    @GetMapping("/mDelete/{mId}")
    public ModelAndView mDelete(@PathVariable String mId){

        return msvc.mDelete(mId);
    }

    // /likeList/[[${view.mId}]] 좋아요 리스트
    @GetMapping("/likeList/{mId}")
    public ModelAndView likeList(@PathVariable String mId){
        mav = new ModelAndView();
        mav.addObject("id" , mId);
        mav.setViewName("member/plList");
        return mav;
    }

}
