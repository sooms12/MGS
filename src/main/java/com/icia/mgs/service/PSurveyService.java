package com.icia.mgs.service;

import com.google.gson.JsonObject;
import com.icia.mgs.dao.*;
import com.icia.mgs.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PSurveyService {

    private ModelAndView mav;

    private final RequestRepository rqrepo;

    private final PSurveyRepository psrepo;

    private final SvPeopleRepository svrepo;

    private final ProductRepository prepo;

    private final HttpServletRequest request;

    private final PSurveyDAO psdao;

    private final SvPeopleDAO svdao;

    private final RequestDAO rqdao;


    private Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/psurvey");

    public ModelAndView psWriteForm(int rqNum) {
        mav = new ModelAndView();

        RequestEntity entity = rqrepo.findById(rqNum).orElse(null);

        System.out.println("psWriteForm Service : " + entity);
        mav.addObject("view", entity);
        mav.setViewName("survey/write");

        return mav;
    }

    public ModelAndView pswrite(PSurveyDTO psurvey) {
        mav = new ModelAndView();
        MultipartFile psPic = psurvey.getPsPic();

        if (!psPic.isEmpty()) {


            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = psPic.getOriginalFilename();
            String psPicName = uuid + "_" + originalFileName;

            psurvey.setPsPicName(psPicName);

            String savePath = path + "/" + psPicName;

            try {
                psPic.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        PSurveyEntity entity = PSurveyEntity.toEntity(psurvey);
        RequestEntity rqEntity = rqdao.typechange(psurvey.getRqNum());
        psrepo.save(entity);
        mav.setViewName("redirect:/rqView/"+psurvey.getRqNum());

        return mav;
    }

    public JsonObject uploadSummernoteImageFile(MultipartFile multipartFile) {
        JsonObject jsonObject = new JsonObject();                               //  url을 받을 json 객체 초기화
        String fileRoot = "D:/BootWorkspace/MGS/src/main/resources/static/product/";    //저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();    //오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자

        String savedFileName = UUID.randomUUID().toString().substring(0, 8) + extension;    //저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);   // 파일명저장 위치+ 파일이름

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);    //파일 저장
            jsonObject.addProperty("url", "/BootWorkspace/MGS/src/main/resources/static/product/" + savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);    //저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }
        return jsonObject;
    }

    public List<PSurveyDTO> psPagingList() {
        List<PSurveyDTO> psurveyList = new ArrayList<>();
        List<PSurveyEntity> entityList = psrepo.findAll();
        System.out.println("svPagingList Service : " + entityList);
        // entityList의 갯수만큼 반복문 실행
        for (PSurveyEntity entity : entityList) {
            // 한 사람의 정보 : entity
            // entity에서 dto로 변형 : MemberDTO.toDTO()
            // memberList에 추가 : memberList.add()
            psurveyList.add(PSurveyDTO.toDTO(entity));
        }
        return psurveyList;
    }

    public ModelAndView psView(int psNum) {
        mav = new ModelAndView();
        String mId = (String) request.getSession().getAttribute("loginId");
        System.out.println("mId : " + mId);
        Optional<PSurveyEntity> psEntity = psrepo.findById(psNum);
        Optional<SvPeopleEntity> svEntity = svrepo.findBymIdAndPsNum(mId,psNum);
        List<SvPeopleEntity> svEntity2 = svrepo.findByPsNum(psNum);
        System.out.println("svEntity : " + svEntity);
        System.out.println("psEntity : " + psEntity);
        int count=0;
        if(svEntity.isEmpty()){
            for (SvPeopleEntity e:svEntity2) {
                count+=e.getSvCount();
            }
            PSurveyDTO psurvey = new PSurveyDTO();
            psurvey = PSurveyDTO.toDTO(psEntity.get());
            mav.addObject("view",psurvey);
            mav.addObject("count",count);

        } else{
            for (SvPeopleEntity e:svEntity2) {
                count+=e.getSvCount();
            }
            PSurveyDTO psurvey = new PSurveyDTO();
            psurvey = PSurveyDTO.toDTO(psEntity.get());
            mav.addObject("svid", svEntity.get().getMId());
            mav.addObject("svcount", svEntity.get().getSvCount());
            mav.addObject("count",count);
            mav.addObject("view",psurvey);

        }
        mav.setViewName("survey/view");
        System.out.println(mav);

        return mav;
    }

    public ModelAndView psView2(int rqNum) {
        mav = new ModelAndView();
        String mId = (String) request.getSession().getAttribute("loginId");
        System.out.println("mId : " + mId);
        Optional<PSurveyEntity> psEntity1 = psrepo.findByrqNum(rqNum);
        Optional<PSurveyEntity> psEntity = psrepo.findById(psEntity1.get().getPsNum());
        Optional<SvPeopleEntity> svEntity = svrepo.findBymIdAndPsNum(mId,psEntity1.get().getPsNum());
        List<SvPeopleEntity> svEntity2 = svrepo.findByPsNum(psEntity1.get().getPsNum());
        System.out.println("svEntity : " + svEntity);
        System.out.println("psEntity : " + psEntity);
        int count=0;
        if(svEntity.isEmpty()){
            for (SvPeopleEntity e:svEntity2) {
                count+=e.getSvCount();
            }
            PSurveyDTO psurvey = new PSurveyDTO();
            psurvey = PSurveyDTO.toDTO(psEntity.get());
            mav.addObject("view",psurvey);
            mav.addObject("count",count);

        } else{
            for (SvPeopleEntity e:svEntity2) {
                count+=e.getSvCount();
            }
            PSurveyDTO psurvey = new PSurveyDTO();
            psurvey = PSurveyDTO.toDTO(psEntity.get());
            mav.addObject("svid", svEntity.get().getMId());
            mav.addObject("svcount", svEntity.get().getSvCount());
            mav.addObject("count",count);
            mav.addObject("view",psurvey);

        }
        mav.setViewName("survey/view");
        System.out.println(mav);

        return mav;
    }

    public ModelAndView psModiForm(int psNum) {
        mav = new ModelAndView();
        PSurveyDTO psurvey = (PSurveyDTO) psView(psNum).getModel().get("view");

        mav.setViewName("survey/modify");
        mav.addObject("modify", psurvey);

        return mav;
    }

    public ModelAndView psModify(PSurveyDTO psurvey) {
        mav = new ModelAndView();

        if (!psurvey.getPsPicName().isEmpty()) {
            String delPath = path + "/" + psurvey.getPsPicName();

            File delFile = new File(delPath);
            if (delFile.exists()) {
                delFile.delete();
            }
        }

        MultipartFile psPic = psurvey.getPsPic();

        if (!psPic.isEmpty()) {
            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = psPic.getOriginalFilename();
            String psPicName = uuid + "_" + originalFileName;

            psurvey.setPsPicName(psPicName);

            String savePath = path + "/" + psPicName;

            try {
                psPic.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            PSurveyEntity entity = PSurveyEntity.toEntity(psurvey);
             System.out.println("psModify : " + entity);
            psrepo.save(entity);
            mav.setViewName("redirect:/psView/" + entity.getPsNum());
            return mav;
        }

    public ModelAndView psDelete(int psNum) {
        mav = new ModelAndView();
        PSurveyDTO psurvey = (PSurveyDTO)psView(psNum).getModel().get("view");
        System.out.println("psDelete Service : " + psurvey);
        if (psurvey.getPsPicName() != null) {
            String delPath = path + "/" + psurvey.getPsPicName();

            File delFile = new File(delPath);
            if (delFile.exists()) {
                delFile.delete();
            }
        }

        psrepo.deleteById(psNum);

        mav.setViewName("redirect:/psList");

        return mav;
    }

    public List<PSurveyDTO> psSearchList(SearchDTO search) {
        return psdao.psSearchList(search);
    }

    public int svWrite(int psNum, String mId, int svCount) {
        int result = 1;
        SvPeopleDTO svpeople = new SvPeopleDTO();
        svpeople.setPsNum(psNum);
        svpeople.setMId(mId);
        svpeople.setSvCount(svCount); // 전체 인원수
        System.out.println("svWrite svPeople : " + svpeople);
        SvPeopleDTO svpeople1 = svdao.svapply(svpeople);
        return result;
    }

    public int svCancel(int psNum, String mId) {
        int result = 2;
        SvPeopleDTO svpeople = new SvPeopleDTO();
        svpeople.setPsNum(psNum);
        svpeople.setMId(mId);
        System.out.println("svCancel Service : " + svpeople);
        SvPeopleDTO svpeople1 = svdao.svcancel(svpeople);
        return result;
    }

    public ModelAndView psmList(int psNum) {
        mav = new ModelAndView();

        mav.addObject("psNum",psNum);
        mav.setViewName("survey/psmlist");

        return mav;
    }

    public List<SvPeopleDTO> psmPagingList(int psNum) {
        List<SvPeopleDTO> svList = new ArrayList<>();
        List<SvPeopleEntity> svEntity1 = svrepo.findByPsNum(psNum);
        Optional<PSurveyEntity> survey = psrepo.findById(psNum);
        String psName = null;
        if (survey.isPresent()) {
            PSurveyEntity surveyEntity = survey.get();
            psName = surveyEntity.getPsName();
        }
        for (SvPeopleEntity entity : svEntity1) {
            SvPeopleDTO dto = new SvPeopleDTO();
            dto.setSvNum(entity.getSvNum());
            dto.setPsNum(entity.getPsNum());
            dto.setMId(entity.getMId());
            dto.setSvCount(entity.getSvCount());
            dto.setPsName(psName);
            svList.add(dto);
        }
        System.out.println("psmList Service : " + svList);
        return svList;
    }
    

    public ModelAndView psmList2(String mId) {
        mav = new ModelAndView();
        mav.addObject("id" , mId);
        mav.setViewName("member/psList");
        return mav;
    }

    public List<PSurveyDTO> psmPagingList2(String mId) {
        List<SvPeopleDTO> svDTO = new ArrayList<>();
        List<SvPeopleEntity> svEntity = svrepo.findBymId(mId);
        List<PSurveyEntity> psEntity = new ArrayList<>();
        for (SvPeopleEntity sv : svEntity) {
            List<PSurveyEntity> pEntity = psrepo.findByPsNum(sv.getPsNum());
            psEntity.addAll(pEntity);
        }
        List<PSurveyDTO> psDTO = new ArrayList<>();
        for (PSurveyEntity entity : psEntity) {
            PSurveyDTO dto = new PSurveyDTO();
            // PSurveyEntity 객체인 entity의 값을 PSurveyDTO 객체인 dto에 복사하기 위해 BeanUtils.copyProperties() 메서드를 사용
            BeanUtils.copyProperties(entity, dto);
            psDTO.add(dto);
            System.out.println("psmList2 Service : " + psDTO);
        }
        return psDTO;
    }
}

