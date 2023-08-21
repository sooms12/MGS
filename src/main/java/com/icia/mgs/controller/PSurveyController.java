package com.icia.mgs.controller;

import com.google.gson.JsonObject;
import com.icia.mgs.dto.PSurveyDTO;
import com.icia.mgs.service.PSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PSurveyController {

    private final PSurveyService svsvc;

    // svWriteForm 인원조사 등록페이지 이동
    @GetMapping("/psWriteForm/{rqNum}")
    public ModelAndView psWriteForm(@PathVariable int rqNum) {
        return svsvc.psWriteForm(rqNum);
    }

    // 서머노트 파일받기
    @PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
        return svsvc.uploadSummernoteImageFile(multipartFile);
    }

        // svwrite 인원조사 등록
    @PostMapping("/pswrite")
    public ModelAndView pswrite(@ModelAttribute PSurveyDTO psurvey){
        System.out.println("pswrite Controller : " + psurvey);
        return svsvc.pswrite(psurvey);
    }
    //svList 인원조사 리스트
    @GetMapping("/psList")
    public String svList(){
        return "survey/list";
    }

    // psView 상세보기
    @GetMapping("/psView/{psNum}")
    public ModelAndView psView(@PathVariable int psNum){
        System.out.println("psView Controller : " + psNum);
        return svsvc.psView(psNum);
    }
    // psView 신청내용에서 인원조사글가기
    @GetMapping("/psView2/{rqNum}")
    public ModelAndView psView2(@PathVariable int rqNum){
        System.out.println("psView2 Controller : " + rqNum);
        return svsvc.psView2(rqNum);
    }

    // psModiForm 수정페이지 이동
    @GetMapping("/psModiForm/{psNum}")
    public ModelAndView psModiForm (@PathVariable int psNum){
        return svsvc.psModiForm(psNum);
    }
    // psmodify 수정
    @PostMapping("/psModify")
    public ModelAndView psModify(@ModelAttribute PSurveyDTO psurvey){
        System.out.println("psModify Controller : " + psurvey);
        return svsvc.psModify(psurvey);
    }
    // psDelete 삭제
    @GetMapping("/psDelete/{psNum}")
    public ModelAndView psDelete(@PathVariable int psNum){
        System.out.println("psDelete Controller : " + psNum);
        return svsvc.psDelete(psNum);
    }
    // psmList 신청자리스트(관리자)
    @GetMapping("/psmList/{psNum}")
    public ModelAndView psmList(@PathVariable int psNum){
        return svsvc.psmList(psNum);
    }
    // psmList2 신청자리스트 (회원본인)
    @GetMapping("/psmList2/{mId}")
    public ModelAndView psmList2(@PathVariable String mId){
        return svsvc.psmList2(mId);
    }

}
