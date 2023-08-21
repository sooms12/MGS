package com.icia.mgs.service;

import com.icia.mgs.dao.*;
import com.icia.mgs.dto.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {

    private ModelAndView mav;
    private final SaleRepository srepo;
    private final ProductRepository prepo;
    private final SaleDAO sdao;
    private final ProductDAO pdao;
    private final PLikeRepository plrepo;
    private final PLikeDAO pldao;
    private final HttpServletRequest request;
    private final SqlSessionTemplate sqlSession;

    // 판매 등록 페이지 이동
    public ModelAndView sRegiForm(int pNum) {
        mav = new ModelAndView();
        Optional<ProductEntity> entity = prepo.findById(pNum);

        ProductDTO product = new ProductDTO();
        product = ProductDTO.toDTO(entity.get());

        mav.setViewName("sale/register");
        mav.addObject("product", product);

        return mav;
    }

    // 판매 등록
    public ModelAndView sRegister(SaleDTO sale) {
        mav = new ModelAndView();

        SaleEntity entity = SaleEntity.toEntity(sale);

        srepo.save(entity);
        mav.setViewName("redirect:/sList");

        return mav;
    }

    // 판매 목록(페이징)
    public List<SaleDTO> sPagingList() {
        List<SaleDTO> saleList = new ArrayList<>();
        List<SaleEntity> entityList = srepo.findAll();

        for (SaleEntity entity : entityList){
            saleList.add(SaleDTO.toDTO(entity));
        }
        return sdao.saleList();
    }

    // 버튼으로 판매상품 보기
    public List<SaleDTO> sPagingList2(int type) {
        List<SaleDTO> saleList = new ArrayList<>();
        List<SaleEntity> entityList = srepo.findBysType(type);
        for (SaleEntity entity : entityList){
            saleList.add(SaleDTO.toDTO(entity));
        }
        System.out.println(saleList);
        return sdao.saleList2(type);
    }

    // 판매 상품 검색
    public List<SaleDTO> sSearchList(SearchDTO search) {
        return sdao.sSearchList(search);
    }

    // 판매 상품 상세보기
    public ModelAndView sView(int sNum) {
        mav = new ModelAndView();
        String mId = (String) request.getSession().getAttribute("loginId");
        SaleDTO sale = sdao.sView(sNum);
        Optional<SaleEntity> sEntity = srepo.findById(sNum); // sNum
        System.out.println(sEntity);

        if(mId == null){
            mav.addObject("view", sale);
            mav.addObject("likecount" , sEntity.get().getLikeCount());
            System.out.println("Service : " + sale);
            mav.setViewName("sale/view");
        } else if (mId != null){
            PLikeEntity plEntity = pldao.findid(mId,sNum);
            if (plEntity == null){
                mav.addObject("view", sale);
                mav.addObject("likecount" , sEntity.get().getLikeCount());
                System.out.println("Service : " + sale);
                mav.setViewName("sale/view");
            }else{
                mav.addObject("id", plEntity.getMId());
                System.out.println("Service id : " + plEntity.getMId());
                mav.addObject("likecount" , sEntity.get().getLikeCount());
                mav.addObject("view", sale);
                System.out.println("Service : " + sale);
                mav.setViewName("sale/view");
            }
        }
        return mav;
    }

    // 판매상품 수정 페이지 이동
    public ModelAndView sModiForm(int sNum) {
        mav = new ModelAndView();

        SaleDTO sale = (SaleDTO)sView(sNum).getModel().get("view");
        System.out.println(sale);
        System.out.println("PNum : " + sale.getPNum());
        System.out.println("SNum : " + sale.getSNum());
        mav.setViewName("sale/modify");
        mav.addObject("modify",sale);

        return mav;
    }

    // 판매상품 수정
    public ModelAndView sModify(SaleDTO sale) {
        mav = new ModelAndView();

        SaleEntity entity = SaleEntity.toEntity(sale);
        System.out.println("sModify Service : " + entity);
        srepo.save(entity);

        mav.setViewName("redirect:/sView/" + sale.getSNum());
        return mav;
    }

    // 판매상품 삭제
    public ModelAndView sDelete(int sNum) {
        mav = new ModelAndView();
        SaleDTO sale = (SaleDTO) sView(sNum).getModel().get("view");
        System.out.println("sDelete Service : " + sale);
        srepo.deleteById(sNum);
        mav.setViewName("redirect:/sList");
        return mav;
    }

    public int likeup(int sNum, String mId, int pNum) {
        int result = 1;
        PLikeDTO plike = new PLikeDTO();
        plike.setSNum(sNum);
        plike.setMId(mId);
        plike.setPNum(pNum);
        System.out.println("plike : " + plike);
        PLikeDTO plike1 = pldao.likeup1(plike);
        SaleDTO sale = sdao.likeup2(sNum);
        SaleDTO check = sdao.likecheck(sNum);
        System.out.println(check.getLikeCount());
        return result;
    }

    public int likedown(int sNum, String mId , int pNum) {
        int result = 2;
        PLikeDTO plike = new PLikeDTO();
        plike.setSNum(sNum);
        plike.setMId(mId);
        plike.setPNum(pNum);
        System.out.println("pdown : " + plike);
        PLikeDTO plike1 = pldao.likedown1(plike);
        SaleDTO sale = sdao.likedown2(sNum);
        SaleDTO check = sdao.likecheck(sNum);
        System.out.println(check.getLikeCount());
        return result;
    }




    public List<ProductEntity> plPaginList(String mId) {
        List<PLikeDTO> plikeList = new ArrayList<>();
        List<SaleDTO> sList = new ArrayList<>();
        List<PLikeEntity> sEntity = plrepo.findBymId(mId);
        List<ProductEntity> pEntity = new ArrayList<>();
        for (PLikeEntity entity : sEntity){
            plikeList.add(PLikeDTO.toDTO(entity));
            System.out.println("plikeList : " + plikeList);
        }
        for (PLikeDTO plike : plikeList){
            List<SaleEntity> sEntityList = srepo.findBysNum(plike.getSNum());
            System.out.println("sEntity : " + sEntityList);
            for (SaleEntity saleEntity : sEntityList) {
                // saleEntity에서 pNum 필드 값을 가져와 ProductEntity 검색
                ProductEntity productEntity = prepo.findBypNum(saleEntity.getPNum());
                // pEntity 리스트에 검색된 ProductEntity 추가
                System.out.println("pEntoty : " +productEntity);
                pEntity.add(productEntity);
            }
        }
        return (pEntity);
    }

    public ModelAndView plView(int pNum) {
        mav = new ModelAndView();
        String mId = (String) request.getSession().getAttribute("loginId");
        Optional<SaleEntity> sEntity = srepo.findBypNum(pNum);
        SaleDTO sale = sdao.sView(sEntity.get().getSNum());
        PLikeEntity plEntity = pldao.findid(mId,sEntity.get().getSNum());
        System.out.println(sEntity);
        if (plEntity == null) {
            mav.addObject("view", sale);
            mav.addObject("likecount" , sEntity.get().getLikeCount());
            System.out.println("Service : " + sale);
            mav.setViewName("sale/view");
        } else {
            mav.addObject("id", plEntity.getMId());
            System.out.println("Service id : " + plEntity.getMId());
            mav.addObject("likecount" , sEntity.get().getLikeCount());
            mav.addObject("view", sale);
            System.out.println("Service : " + sale);
            mav.setViewName("sale/view");
        }
        return mav;
    }
}
