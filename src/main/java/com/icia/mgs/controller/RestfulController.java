package com.icia.mgs.controller;

import com.icia.mgs.dto.*;
import com.icia.mgs.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // @Responsebody 생략가능!!
@RequiredArgsConstructor
public class RestfulController {
    private final MemberService msvc;
    private final CompanyService csvc;
    private final ProductService psvc;
    private final SaleService ssvc;
    private final RequestService rqsvc;
    private final PSurveyService svsvc;
    private final CartService ctvc;
    private final OrdertService ksvc;
    private final CustomerService ccsvc;
    private final CommentService ctsvc;


    // 아이디 중복확인
    @PostMapping("idCheck")
    public String idCheck(@RequestParam("mId") String mId){
        return msvc.idCheck(mId);
    }

    // 카카오 이메일 중복확인
    @PostMapping("/oauth/emailCheck2")
    public String emailCheck2(@RequestParam("mEmail") String mEmail){
        return msvc.emailCheck2(mEmail);
    }

    // 일반 이메일 중복확인
    @PostMapping("emailCheck3")
    public String emailCheck3(@RequestParam("mEmail") String mEmail){
        return msvc.emailCheck3(mEmail);
    }

    // 카카오 인증 아이디체크
    @PostMapping("/oauth/idCheck2")
    public String idCheck2(@RequestParam("mId") String mId){
        return msvc.idCheck2(mId);
    }

    // 이메일 인증
    @PostMapping("emailCheck")
    public String emailCheck(@RequestParam("mEmail") String mEmail){
        return msvc.emailCheck(mEmail);
    }

    // 회원목록(페이징)
    @PostMapping("pagingList")
    public List<MemberDTO> pagingList(){
        return msvc.pagingList();
    }

    // 회원검색
    @PostMapping("searchList")
    public List<MemberDTO> searchList(@ModelAttribute SearchDTO search){
        return msvc.searchList(search);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 회사목록(페이징)
    @PostMapping("cPagingList")
    public List<CompanyDTO> cPagingList(){
        return csvc.cPagingList();
    }

    // 회사검색
    @PostMapping("cSearchList")
    public List<CompanyDTO> cSearchList(@ModelAttribute SearchDTO search){
        return csvc.cSearchList(search);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 상품목록(페이징)
    @PostMapping("pPagingList")
    public List<ProductDTO> pPagingList(){
        return psvc.pPagingList();
    }

    // 상품검색
    @PostMapping("pSearchList")
    public List<ProductDTO> pPagingList(@ModelAttribute SearchDTO search){
        return psvc.pSearchList(search);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 판매 상품목록(페이징)
    @PostMapping("sPagingList")
    public List<SaleDTO> sPagingList(){
        return ssvc.sPagingList();
    }

    // sPagingList2 버튼으로 상품목록
    @PostMapping("sPagingList2")
    public List<SaleDTO> sPagingList2(@RequestParam int type){
        return ssvc.sPagingList2(type);
    }

    // 판매 상품검색
    @PostMapping("sSearchList")
    public List<SaleDTO> sPagingList(@ModelAttribute SearchDTO search){
        return ssvc.sSearchList(search);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // rqPagingList 요청페이징리스트
    @PostMapping ("rqPagingList")
    public List<RequestDTO> rqPagingList(){
        return rqsvc.rqPagingList();
    }
    @GetMapping("/rqPagingList2")
    public List<RequestDTO> rqPagingList2(@RequestParam String mId){
        System.out.println("List2 : " + mId);
        return rqsvc.rqPagingList2(mId);
    }

    // rqSearchList 요청 페이징 검색 System.out.println("Controller : " + search);
    @PostMapping("rqSearchList")
    public List<RequestDTO> rqSearchList(@ModelAttribute SearchDTO search){
        System.out.println("Controller : " + search);
        return rqsvc.rqSearchList(search);
    }

    @PostMapping("/rqSearchList2")
    public List<RequestDTO> rqSearchList2(@ModelAttribute SearchDTO search, @RequestParam String mId){
        System.out.println("Controller : " + search);
        return rqsvc.rqSearchList2(search,mId);
    }

    // likeup 좋아요
    @PostMapping("/likeup")
    public int likeup(@RequestParam int sNum , @RequestParam String mId , @RequestParam int pNum){
        System.out.println("likeup Contorller : " + sNum + "," + mId + " , " + pNum);
        return ssvc.likeup(sNum,mId,pNum);
    }
    // likedown 좋아요 취소
    @PostMapping("/likedown")
    public int likedown(@RequestParam int sNum , @RequestParam String mId , @RequestParam int pNum){
        System.out.println("likedown Controller : " + sNum + "," + mId + " , " + pNum);
        return ssvc.likedown(sNum,mId,pNum);
    }

    // svPagingList 인원조사 게시판
    @PostMapping("psPagingList")
    public List<PSurveyDTO> psPagingList(){
        return svsvc.psPagingList();
    }
    // psSearchList 인원조사 검색
    @PostMapping("psSearchList")
    public List<PSurveyDTO> psSearchList(@ModelAttribute SearchDTO search){
        return svsvc.psSearchList(search);
    }

    // svWrite 인원조사 신청하기
    @GetMapping("/svWrite")
    public int svWrite(@RequestParam int psNum, @RequestParam String mId , @RequestParam int svCount){
        System.out.println("svWrite Controller : " + psNum + " , " + mId + " , " + svCount);
        return svsvc.svWrite(psNum,mId,svCount);
    }
    // /svCancel 인원조사 신청취소
    @GetMapping("/svCancel")
    public int svCancel(@RequestParam int psNum, @RequestParam String mId){
        System.out.println("svCancel Controller : " + psNum + " , " + mId);
        return svsvc.svCancel(psNum,mId);
    }
    // plPagingList 좋아요리스트
    @PostMapping("/plPagingList")
    public List<ProductEntity> plPagingList(@RequestParam String mId){
        System.out.println("plList Controller : " + mId);
        return ssvc.plPaginList(mId);
    }

    // 신청참여자 목록 (어드민)
    @PostMapping("/psmPagingList")
    public List<SvPeopleDTO> psmPagingList(@RequestParam int psNum){
        System.out.println("psmList : " + psNum);{
            return svsvc.psmPagingList(psNum);
        }
    }

    // 신청 잠여자 목록 회원본인
    @PostMapping("/psmPagingList2")
    public List<PSurveyDTO> psmPagingList2(@RequestParam String mId){
        System.out.println("psmList2 : " + mId);
        return svsvc.psmPagingList2(mId);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 장바구니 목록
    @PostMapping("/cctlist")
    public List<CartDTO> cctlist(@RequestParam String mId) {
        return  ctvc.cctlist(mId);
    }


    // 장바구니에 상품 추가
    @PostMapping("/addToCart")
    public List<CartDTO> addToCart(@ModelAttribute CartDTO cart) {
        System.out.println("1 :" + cart);
        return ctvc.addToCart(cart);
    }

    // 장바구니에 상품 삭제
    @PostMapping("/deleteCartItem")
    public List<CartDTO> deleteCartItem(@ModelAttribute CartDTO cart) {
        System.out.println("1 :" + cart);
        return  ctvc.deleteCartItem(cart);
    }

    //결제영역
    @PostMapping("/successPay")
    public String successPay(@RequestParam("oNum") String oNum){
        return ksvc. successPay(oNum);
    }

    // 결제 정보 DB
    @PostMapping("/insertDB")
    public String insertDB(@ModelAttribute OrdertDTO order){
        return ksvc.insertDB(order);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // pagingCsList : 고객센터 게시글 리스트 불러오기
    @PostMapping("pagingCsList")
    public List<CustomerDTO> pagingCsList(){
        System.out.println("1");
        return ccsvc.pagingCsList();
    }


    // csmList : 댓글 불러오기
    @PostMapping("/csmList")
    public List<csCommentDTO> csmList(@RequestParam("csNum") int csNum){
        return ccsvc.csmList(csNum);
    }

    // csmWrite : 댓글 작성
    @PostMapping("/csmWrite")
    public List<csCommentDTO> csmWrite(@ModelAttribute csCommentDTO csco){
        return ccsvc.csmWrite(csco);
    }

    // csmModify : 댓글 수정
    @PostMapping("/csmModify")
    public List<csCommentDTO> csmModify(@ModelAttribute csCommentDTO csco){
        return ccsvc.csmModify(csco);
    }

    // csmDelete : 댓글 삭제
    @PostMapping("/csmDelete")
    public List<csCommentDTO> csmDelete(@RequestParam int ccNum, int csNum){
        return ccsvc.csmDelete(ccNum, csNum);
    }

    // rvList : 리뷰 불러오기
    @PostMapping("/rvList")
    public List<pCommentDTO> rvList(@ModelAttribute pCommentDTO pco){return ctsvc.rvList(pco);}

    // rvpCheck : 결제 확인
    @GetMapping("/rvpCheck")
    public String rvpCheck(@ModelAttribute OrdertDTO order){
        return ctsvc.rvpCheck(order);
    }

    // rvDelete : 리뷰 삭제
    @PostMapping("/rvDelete")
    public List<pCommentDTO> rvDelete(@ModelAttribute pCommentDTO pco){
        return ctsvc.rvDelete(pco);
    }


    // rvModify : 리뷰 수정
    @PostMapping("/rvModify")
    public List<pCommentDTO> rvModify(@ModelAttribute pCommentDTO pco){
        System.out.println("리뷰 수정 controller : " + pco);

        return  ctsvc.rvModify(pco);
    }

    // qaList : 문의글 불러오기
    @PostMapping("/qaList")
    public List<pCommentDTO> qaList(@ModelAttribute pCommentDTO pco){
        return ctsvc.qaList(pco);
    }



    // qaDelete : 문의글 삭제
    @PostMapping("/qaDelete")
    public List<pCommentDTO> qaDelete(@ModelAttribute pCommentDTO pco){
        return ctsvc.qaDelete(pco);
    }

    // reList : 답글 불러오기
    @PostMapping("/reList")
    public List<ReplyDTO> reList(@RequestParam int sNum){
        return ctsvc.reList(sNum);
    }

    // reDelete : 답글 삭제
    @PostMapping("/reDelete")
    public List<ReplyDTO> reDelete(@RequestParam int sNum, int reNum){
        return ctsvc.reDelete(sNum, reNum);
    }


}
