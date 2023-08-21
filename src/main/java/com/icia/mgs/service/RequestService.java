package com.icia.mgs.service;

import com.icia.mgs.dao.RequestDAO;
import com.icia.mgs.dao.RequestRepository;
import com.icia.mgs.dto.RequestDTO;
import com.icia.mgs.dto.RequestEntity;
import com.icia.mgs.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    private ModelAndView mav;

    private final RequestRepository rqrepo;

    private final RequestDAO rqdao;

    private final HttpServletRequest request;

    public ModelAndView requestWrite(RequestDTO request) {
        mav = new ModelAndView();
        // DTO -> entity
        RequestEntity entity = RequestEntity.toEntity(request);

        rqrepo.save(entity);

        mav.setViewName("index");
        return mav;

    }

    public List<RequestDTO> rqPagingList() {
        // entity를 dto로 변환하기 위한 rqList 생성
        List<RequestDTO> rqList = new ArrayList<>();

        // DB에서 조회한 정보를 entitiyList에 담는다
        List<RequestEntity> entityList = rqrepo.findAllByOrderByRqNumDesc();

        // 데이터(entytyList)의 갯수만큼 반복문을 실행한다.
        // entity를 dto에 담아 rqList에 추가(add)한다.
        for(RequestEntity entity : entityList){
            rqList.add(RequestDTO.toDTO((entity)));
        }
        return rqList;
    }


    public List<RequestDTO> rqSearchList(SearchDTO search) {
        System.out.println("Service : " + search);
        return rqdao.rqSearchList(search);
    }

    public ModelAndView rqView(int rqNum) {
        mav = new ModelAndView();

        // rqNum으로 검색하기
        Optional<RequestEntity> rqEntity = rqrepo.findById(rqNum);

        // 불러와서 담기
        RequestDTO request = RequestDTO.toDTO(rqEntity.get());

        mav.setViewName("request/view");
        mav.addObject("request" , request);

        return mav;

    }
    public ModelAndView rqView2(int rqNum) {
        mav = new ModelAndView();

        // rqNum으로 검색하기
        Optional<RequestEntity> rqEntity = rqrepo.findById(rqNum);

        // 불러와서 담기
        RequestDTO request = RequestDTO.toDTO(rqEntity.get());

        mav.setViewName("request/mView");
        mav.addObject("request" , request);

        return mav;

    }

    public ModelAndView rqModiForm(int rqNum) {
        mav = new ModelAndView();

        // 상세보기에 있는 정보 들고와서 request에 담기
        RequestDTO request = (RequestDTO)rqView(rqNum).getModel().get("request");
        System.out.println("rqModiForm Service : " + request);

        mav.setViewName("request/modify");
        mav.addObject("modify",request);

        return mav;
    }

    public ModelAndView rqModiForm2(int rqNum) {
        mav = new ModelAndView();

        // 상세보기에 있는 정보 들고와서 request에 담기
        RequestDTO request = (RequestDTO)rqView(rqNum).getModel().get("request");
        System.out.println("rqModiForm2 Service : " + request);

        mav.setViewName("request/mModify");
        mav.addObject("modify",request);

        return mav;
    }

    public ModelAndView requestModify(RequestDTO request) {
        mav = new ModelAndView();
        // DTO -> entity
        RequestEntity entity = RequestEntity.toEntity(request);
        // 저장
        System.out.println("request"+request);
        rqrepo.save(entity);

        mav.setViewName("redirect:/rqView/" + request.getRqNum());

        return mav;
    }

    public ModelAndView requestModify2(RequestDTO request) {
        mav = new ModelAndView();
        // DTO -> entity
        RequestEntity entity = RequestEntity.toEntity(request);
        // 저장
        System.out.println("request"+request);
        rqrepo.save(entity);

        mav.setViewName("redirect:/rqView2/" + request.getRqNum());

        return mav;
    }

    public ModelAndView rqDelete(int rqNum) {
        mav = new ModelAndView();

        rqrepo.deleteById(rqNum);
        mav.setViewName("redirect:/rqList");
        return mav;
    }

    public ModelAndView rqDelete2(int rqNum) {
        mav = new ModelAndView();
        String mId = (String) request.getSession().getAttribute("loginId");
        rqrepo.deleteById(rqNum);
        mav.setViewName("redirect:/rqList2/"+mId);
        return mav;
    }

    public ModelAndView rqList2(String mId) {
        mav = new ModelAndView();

        mav.addObject("request", mId);
        System.out.println("rqList2 Service : " + mId);
        mav.setViewName("request/mlist");
        return mav;
    }

    public List<RequestDTO> rqPagingList2(String mId) {
        // entity를 dto로 변환하기 위한 rqList 생성
        List<RequestDTO> rqList = new ArrayList<>();

        // DB에서 조회한 정보를 entitiyList에 담는다
        List<RequestEntity> entityList = rqrepo.findBymId(mId);
        System.out.println("List2 : " + entityList);
        // 데이터(entytyList)의 갯수만큼 반복문을 실행한다.
        // entity를 dto에 담아 rqList에 추가(add)한다.
        for(RequestEntity entity : entityList){
            rqList.add(RequestDTO.toDTO((entity)));
        }
        return rqList;
    }

    public List<RequestDTO> rqSearchList2(SearchDTO search, String mId) {
        System.out.println("Service : " + search + "," + mId);
        return rqdao.rqSearchList2(search,mId);
    }
}
