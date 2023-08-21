package com.icia.mgs.service;

import com.icia.mgs.dao.CompanyDAO;
import com.icia.mgs.dao.CompanyRepository;
import com.icia.mgs.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private ModelAndView mav;
    private final CompanyRepository crepo;
    private final CompanyDAO cdao;


    // 회사 등록
    public ModelAndView cRegister(CompanyDTO company) {
        mav = new ModelAndView();

        CompanyEntity entity = CompanyEntity.toEntity(company);

        crepo.save(entity);
        mav.setViewName("redirect:/cList");

        return mav;
    }

    // 회사 목록(페이징)
    public List<CompanyDTO> cPagingList() {
        List<CompanyDTO> companyList = new ArrayList<>();

        List<CompanyEntity> entityList = crepo.findAll();

        // entityList의 갯수만큼 반복문 실행
        for (CompanyEntity entity : entityList) {
            // 한 사람의 정보 : entity
            // entity에서 dto로 변형 : MemberDTO.toDTO()
            // memberList에 추가 : memberList.add()
            companyList.add(CompanyDTO.toDTO(entity));
        }

        return companyList;
    }

    // 회사 검색
    public List<CompanyDTO> cSearchList(SearchDTO search) {
        return cdao.cSearchList(search);
    }

    // 회사 정보 상세보기
    public ModelAndView cView(int cNum) {
        mav = new ModelAndView();

        Optional<CompanyEntity> entity = crepo.findById(cNum);

        if(entity.isPresent()){
            CompanyDTO company = new CompanyDTO();
            company = CompanyDTO.toDTO(entity.get());

            mav.setViewName("company/view");
            mav.addObject("view", company);
        } else {
            mav.setViewName("index");
        }

        return mav;
    }

    // 회사 정보 수정 페이지 이동
    public ModelAndView cModiForm(int cNum) {
        mav = new ModelAndView();

        CompanyDTO company = (CompanyDTO)cView(cNum).getModel().get("view");

        mav.setViewName("company/modify");
        mav.addObject("modify", company);

        return mav;
    }

    // 회사 정보 수정
    public ModelAndView cModify(CompanyDTO company) {
        mav = new ModelAndView();

        CompanyEntity entity = CompanyEntity.toEntity(company);

        crepo.save(entity);

        mav.setViewName("redirect:/cView/" + company.getCNum());
        return mav;
    }

    // 회사 정보 삭제
    public ModelAndView cDelete(int cNum) {
        mav = new ModelAndView();
        CompanyDTO company = (CompanyDTO)cView(cNum).getModel().get("view");


        crepo.deleteById(cNum);
        mav.setViewName("redirect:/cList");

        return mav;
    }



}
