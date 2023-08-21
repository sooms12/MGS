package com.icia.mgs.service;

import com.icia.mgs.dao.CustomerDAO;
import com.icia.mgs.dao.CustomerRepository;
import com.icia.mgs.dao.csCommentRepository;
import com.icia.mgs.dto.CustomerDTO;
import com.icia.mgs.dto.CustomerEntity;
import com.icia.mgs.dto.csCommentDTO;
import com.icia.mgs.dto.csCommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private ModelAndView mav;

    private final CustomerRepository crepo;

    private final csCommentRepository csrepo;

    private final HttpServletResponse response;

    private final HttpServletRequest request;

    private final HttpSession session;


    // pagingCsList : 고객센터 게시글 리스트 불러오기
    public List<CustomerDTO> pagingCsList() {
        System.out.println("2");

        List<CustomerDTO> bList = new ArrayList<>();

        List<CustomerEntity> csEntityList = crepo.findAll();

        System.out.println("csEntityList : " + csEntityList);

        for (CustomerEntity entity : csEntityList){


            bList.add(CustomerDTO.toDTO(entity));
        }

        System.out.println(bList);
        return bList;
    }

    // csView : 고객문의 상세보기
    public ModelAndView csView(int csNum) {
        mav = new ModelAndView();

//        String loginId = (String) session.getAttribute("loginId");
//
//        if(loginId==null){
//            loginId = "Guest";
//        }

        Optional<CustomerEntity> cEntity = crepo.findById(csNum);

        CustomerDTO cList = CustomerDTO.toDTO(cEntity.get());

        mav.setViewName("customer/view");
        mav.addObject("view", cList);


        return mav;
    }


    // csModiForm : 고객문의 게시글 수정 페이지 이동
    public ModelAndView csModiForm(int csNum) {
        mav = new ModelAndView();

        CustomerDTO customer = (CustomerDTO) csView(csNum).getModel().get("view");

        mav.setViewName("customer/modify");
        mav.addObject("modify", customer);

        return mav;
    }


    // csModify : 고객문의 게시글 수정
    public ModelAndView csModify(CustomerDTO customer) {
        mav = new ModelAndView();


        CustomerEntity entity = CustomerEntity.toEntity(customer);

        crepo.save(entity);

        mav.setViewName("redirect:/csView/" + customer.getCsNum());

        return mav;
    }


    // csWrite : 게시글 작성
    public ModelAndView csWrite(CustomerDTO customer) {
        mav = new ModelAndView();

        CustomerEntity cEntity = CustomerEntity.toEntity(customer);
        crepo.save(cEntity);

        mav.setViewName("redirect:/csList");

        return mav;
    }


    // csDelete : 게시글 삭제
    public ModelAndView csDelete(int csNum) {
        mav = new ModelAndView();

        List<csCommentDTO> cNumList = new ArrayList<>();

        List<csCommentEntity> ccList = csrepo.findByCsNum(csNum);


        for (csCommentEntity cEntity : ccList){
            cNumList.add(csCommentDTO.toDTO(cEntity));
            csmDelete(cEntity.getCcNum(),csNum);
        }

        crepo.deleteById(csNum);
        mav.setViewName("redirect:/csList");

        return mav;
    }


    // csmList : 댓글 불러오기
    public List<csCommentDTO> csmList(int csNum) {

        List<csCommentDTO> cmList = new ArrayList<>();

        // csrepo.findById(csNum) 메서드를 사용하여 단일 엔티티를 가져옵니다.
        List<csCommentEntity> csEntityList  = csrepo.findByCsNumOrderByCcNum(csNum);


        // 만약 엔티티가 존재한다면 DTO로 변환하여 리스트에 추가합니다.
        // csEntityOpt .ifPresent(entity -> cmList.add(csCommentDTO.toDTO(entity)));

        for(csCommentEntity entity : csEntityList){
            cmList.add(csCommentDTO.toDTO(entity));
        }


        return cmList;
    }


    // csmWrite : 댓글 작성
    public List<csCommentDTO> csmWrite(csCommentDTO csco) {

        csCommentEntity csEntity = csCommentEntity.toEntity(csco);
        csrepo.save(csEntity);

        List<csCommentDTO> cmList = csmList(csEntity.getCsNum());

        System.out.println("cmList : " + cmList);

        return cmList;
    }


    // csmModify : 댓글 수정
    public List<csCommentDTO> csmModify(csCommentDTO csco) {

        csCommentEntity csmEntity = csCommentEntity.toEntity(csco);
        csrepo.save(csmEntity);

        List<csCommentDTO> cmList = csmList(csmEntity.getCsNum());

        return cmList;
    }


    // csmDelete : 댓글 삭제
    public List<csCommentDTO> csmDelete(int ccNum,int csNum) {


        csrepo.deleteById(ccNum);

        List<csCommentDTO> cmList = csmList(csNum);

        return cmList;
    }
}
