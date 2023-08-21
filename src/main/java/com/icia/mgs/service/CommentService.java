package com.icia.mgs.service;

import com.icia.mgs.dao.*;
import com.icia.mgs.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private ModelAndView mav;

    private final pCommentRepository pcrepo;
    private final SaleRepository srepo;
    private final ReplyRepository rrepo;
    private final SaleDAO sdao;
    private final pCommentDAO pcdao;

    private Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/review");


    // rvList : 리뷰 불러오기
    public List<pCommentDTO> rvList(pCommentDTO pco) {
        System.out.println("리뷰 불러오기 service  pco : " + pco);

        List<pCommentDTO> rvList = new ArrayList<>();

        // pcrepo.findById(csNum) 메서드를 사용하여 단일 엔티티를 가져옵니다.
      /*  List<pCommentEntity> pEntityList  = pcrepo.findBysNumAndPcTypeOrderByPcNumDesc(pco.getSNum(), pco.getPcType());

        System.out.println("리뷰 불러오기 pEntityList : "+ pEntityList);

        // 만약 엔티티가 존재한다면 DTO로 변환하여 리스트에 추가합니다.
        // csEntityOpt .ifPresent(entity -> .add(csCommentDTO.toDTO(entity)));

        for(pCommentEntity entity : pEntityList){
            cmList.add(pCommentDTO.toDTO(entity));
        }*/

        System.out.println("리뷰 불러오기  rvList : "+ rvList);
        return pcdao.rvList(pco);
    }


    // rvpCheck : 결제 확인
    public String rvpCheck(OrdertDTO order) {

        String  check = null;

        int result = pcdao.rvpCheck(order);

        if (result > 0){
            check = "OK";
        } else {
            check = "NO";
        }

        System.out.println("결제 확인 service  check : " + check);
        return check;
    }


    // rvWrite : 리뷰 작성
    public void rvWrite(pCommentDTO pco) {

        System.out.println("리뷰 작성 service  pco : " + pco);

        for (int i =0; i<pco.getPcFile().length; i++){
            if (!pco.getPcFile()[i].isEmpty()){
                System.out.println(i + " : " + pco.getPcFile()[i].getOriginalFilename());

                Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/review");

                String uuid = UUID.randomUUID().toString().substring(0, 8);

                String originalFileName = pco.getPcFile()[i].getOriginalFilename();

                String pcPicName = uuid + "_" + originalFileName;


                switch (i) {
                    case 0:
                        pco.setPcPicName1(pcPicName);
                        break;
                    case 1:
                        pco.setPcPicName2(pcPicName);
                        break;
                    case 2:
                        pco.setPcPicName3(pcPicName);
                        break;
                }

                System.out.println("pcPicName1 : " + pco.getPcPicName1());
                System.out.println("pcPicName2 : " + pco.getPcPicName2());
                System.out.println("pcPicName3 : " + pco.getPcPicName3());


                String savePath = path + "/" + pcPicName;

                try {
                    pco.getPcFile()[i].transferTo(new File(savePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }


        pCommentEntity entity = pCommentEntity.toEntity(pco);

        pcrepo.save(entity);


        System.out.println("리뷰 작성   entity : " + entity);

    }


    // rvModify : 리뷰 수정
    public List<pCommentDTO> rvModify(pCommentDTO pco) {

        System.out.println("리뷰 수정 전 : " + pco);
   
        if (!pco.getPcFile()[0].isEmpty()) {
            String delPath = path + "/" + pco.getPcPicName1();
            
            File delFile = new File(delPath);
            
            if (delFile.exists()){

                delFile.delete();
            }
        }
        
        if (!pco.getPcFile()[1].isEmpty()) {
            String delPath = path + "/" + pco.getPcPicName2();

            File delFile = new File(delPath);

            if (delFile.exists()){
                delFile.delete();
            }
        }
        
        if (!pco.getPcFile()[2].isEmpty()) {
            String delPath = path + "/" + pco.getPcPicName3();

            File delFile = new File(delPath);

            if (delFile.exists()){
                delFile.delete();
            }
        }

        for (int i =0; i<pco.getPcFile().length; i++){
            if (!pco.getPcFile()[i].isEmpty()){

                System.out.println(i + " : " + pco.getPcFile()[i].getOriginalFilename());
                Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/review");
                String uuid = UUID.randomUUID().toString().substring(0, 8);
                String originalFileName = pco.getPcFile()[i].getOriginalFilename();
                String pcPicName = uuid + "_" + originalFileName;




                switch (i) {
                    case 0:
                        pco.setPcPicName1(pcPicName);
                        break;
                    case 1:
                        pco.setPcPicName2(pcPicName);
                        break;
                    case 2:
                        pco.setPcPicName3(pcPicName);
                        break;
                }

                System.out.println("pcPicName1 : " + pco.getPcPicName1());
                System.out.println("pcPicName2 : " + pco.getPcPicName2());
                System.out.println("pcPicName3 : " + pco.getPcPicName3());


                String savePath = path + "/" + pcPicName;

                try {
                    pco.getPcFile()[i].transferTo(new File(savePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        pCommentEntity entity = pCommentEntity.toEntity(pco);

        pcrepo.save(entity);


        System.out.println("리뷰 수정  entity : " + entity);

        List<pCommentDTO> cmList = new ArrayList<>();

        cmList = rvList(pco);

        System.out.println("리뷰 수정  cmList : " + cmList);

        return cmList;
    }



    // rvDelete : 리뷰 삭제
    public List<pCommentDTO> rvDelete(pCommentDTO pco) {
        List<pCommentDTO> cmList = new ArrayList<>();

        System.out.println("리뷰 삭제 전 pco : " + pco);

        if (pco.getPcPicName1()!=null) {
            String delPath = path + "/" + pco.getPcPicName1();

            File delFile = new File(delPath);

            if (delFile.exists()){
                delFile.delete();
            }
        }

        if (pco.getPcPicName2()!=null) {
            String delPath = path + "/" + pco.getPcPicName2();

            File delFile = new File(delPath);

            if (delFile.exists()){
                delFile.delete();
            }
        }

        if (pco.getPcPicName3()!=null) {
            String delPath = path + "/" + pco.getPcPicName3();

            File delFile = new File(delPath);

            if (delFile.exists()){
                delFile.delete();
            }
        }

        pcrepo.deleteById(pco.getPcNum());

        cmList = rvList(pco);

        System.out.println("리뷰 삭제 후 cmList : "+ cmList);
        return cmList;
    }


    // qaWrite : 문의글 작성
    public ModelAndView qaWrite(pCommentDTO pco) {
        mav = new ModelAndView();

        System.out.println("문의글 작성 service  pco : " + pco);

        pCommentEntity entity = pCommentEntity.toEntity(pco);
        pcrepo.save(entity);


        mav.setViewName("redirect:/sView/" + pco.getSNum());


        System.out.println("문의글 작성   entity : " + entity);
        return mav;
    }


    // qaList : 문의글 불러오기
    public List<pCommentDTO> qaList(pCommentDTO pco) {

        System.out.println("문의글 불러오기 service  pco : " + pco);

        List<pCommentDTO> cmList = new ArrayList<>();

      /*  // pcrepo.findById(csNum) 메서드를 사용하여 단일 엔티티를 가져옵니다.
        List<pCommentEntity> pEntityList  = pcrepo.findBypNumAndPcType(pco.getPNum(), pco.getPcType());

        System.out.println("pEntityList : "+ pEntityList);

        // 만약 엔티티가 존재한다면 DTO로 변환하여 리스트에 추가합니다.
        // csEntityOpt .ifPresent(entity -> cmList.add(csCommentDTO.toDTO(entity)));

        for(pCommentEntity entity : pEntityList){
            cmList.add(pCommentDTO.toDTO(entity));
        }*/

        System.out.println("문의글 불러오기 cmList : "+ cmList);
        return pcdao.cmList(pco);
    }


    // qaModify : 문의글 수정
    public ModelAndView qaModify(pCommentDTO pco) {
        mav = new ModelAndView();

        System.out.println("문의글 수정 전 : " + pco);

        pCommentEntity entity = pCommentEntity.toEntity(pco);

        pcrepo.save(entity);

        pCommentDTO pcoE = pCommentDTO.toDTO(entity);

        List<pCommentDTO> cmList = qaList(pcoE);


        System.out.println("문의글 수정  cmList : " + cmList);

        mav.setViewName("redirect:/sView/" + pco.getSNum());
        return mav;
    }

    // qaDelete : 문의글 삭제
    public List<pCommentDTO> qaDelete(pCommentDTO pco) {
        List<pCommentDTO> cmList = new ArrayList<>();

        List<ReplyDTO> reList = new ArrayList<>();

        List<ReplyEntity> reEntity = rrepo.findByPcNum(pco.getPcNum());

        for (ReplyEntity entity : reEntity){
            reList.add(ReplyDTO.toDTO(entity));
            reDelete(entity.getSNum(), entity.getReNum());
        }

        System.out.println("문의글 삭제 전 pco : " + pco);

        pcrepo.deleteById(pco.getPcNum());

        cmList = qaList(pco);

        System.out.println("문의글 삭제 후 cmList : "+ cmList);
        return cmList;
    }


    // reWrite : 문의글 답글 작성
    public ModelAndView reWrite(ReplyDTO reply) {
        mav = new ModelAndView();

        System.out.println("문의글 답글 작성 service  reply : " + reply);

        ReplyEntity entity = ReplyEntity.toEntity(reply);
        rrepo.save(entity);


        mav.setViewName("redirect:/sView/" + reply.getSNum());


        System.out.println("문의글 작성   entity : " + entity);

        return mav;
    }


    // reList : 답글 불러오기
    public List<ReplyDTO> reList(int sNum) {

        System.out.println("답글 불러오기 service  sNum : " + sNum);

        List<ReplyDTO> cmList = new ArrayList<>();

        // pcrepo.findById(csNum) 메서드를 사용하여 단일 엔티티를 가져옵니다.
        List<ReplyEntity> rEntityList  = rrepo.findBysNumOrderByReNumDesc(sNum);

        System.out.println("rEntityList : "+ rEntityList);

        // 만약 엔티티가 존재한다면 DTO로 변환하여 리스트에 추가합니다.
        // csEntityOpt .ifPresent(entity -> cmList.add(csCommentDTO.toDTO(entity)));

        for(ReplyEntity entity : rEntityList){
            cmList.add(ReplyDTO.toDTO(entity));
        }

        System.out.println("답글 불러오기 cmList : "+ cmList);
        return cmList;
    }


    // reModify : 답글 수정
    public ModelAndView reModify(ReplyDTO reply) {
        mav = new ModelAndView();

        System.out.println("답글 수정 전 : " + reply);

        ReplyEntity entity = ReplyEntity.toEntity(reply);

        rrepo.save(entity);


        List<ReplyDTO> cmList = reList(reply.getSNum());


        System.out.println("답글 수정  cmList : " + cmList);

        mav.setViewName("redirect:/sView/" + reply.getSNum());

        return mav;
    }

    // reDelete : 답글 삭제
    public List<ReplyDTO> reDelete(int sNum, int reNum) {

        List<ReplyDTO> cmList = new ArrayList<>();

        System.out.println("답글 삭제 전 reply : " + sNum + reNum);

        rrepo.deleteById(reNum);

        cmList = reList(sNum);

        System.out.println("답글 삭제 후 cmList : " + cmList);

        return cmList;
    }


}
