package com.icia.mgs.controller;

import com.icia.mgs.dto.MemberDTO;
import com.icia.mgs.service.KakaoLoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class KakaoLoginController {

    private final KakaoLoginService osvc;




    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/kakao")
    public ModelAndView kakaoCallback(@RequestParam String code, HttpSession session) {
        // 로그인시 받아오는 코드로 토큰 받아오기
        String access_Token = osvc.getKakaoAccessToken(code);
        // 받아온 토큰으로 유저 정보 담아오기
        HashMap<String, Object> userInfo = osvc.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo );

        ModelAndView mav = new ModelAndView();
        // 로그인 됐을 시
        if(userInfo.get("nickname") != null){
            // 세션에 닉네임이랑, 토큰 담아놓기
            session.setAttribute("userId", userInfo.get("nickname"));
            session.setAttribute("access_Token", access_Token);
            MemberDTO member = new MemberDTO();
            // 멤버 mkid(카카오아이디)에 유저 닉네임 담기
            member.setMkId((String) userInfo.get("nickname"));
            mav.addObject("mkid", member.getMkId());
            System.out.println("mkid : " + member.getMkId());
            mav.setViewName("member/kjoin");
        } else {
            mav.setViewName("loginError");
        }
        return mav;
    }


    @GetMapping(value="/klogout")
    public ModelAndView klogout(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        // 로그인시 담아놓은 토큰으로 로그아웃 하러가기
        osvc.kakaoLogout((String)session.getAttribute("access_Token"));
        System.out.println("로그아웃중");
        // 세션에서 토큰과 아이디 지우기
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        modelAndView.setViewName("index");
        return modelAndView;
    }




}
