package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@Alias("psurvey")
public class PSurveyDTO {

    private int psNum;           // 인원조사 번호
    private int rqNum;           // 신청서 번호
    private String mId;          // 작성자 아이디
    private String psName;       // 상품이름
    private String psInfo;       // 상품정보
    private String psPicName;    // 상품 사진
    private Date psDate;         // 등록일
    private int psCount;         // 상품수량
    private int psPrice;         // 가격
    private String psFDate;        // 마감일

    private MultipartFile psPic; // 상품사진

    public static PSurveyDTO toDTO(PSurveyEntity entity){
        PSurveyDTO psurvey = new PSurveyDTO();

        psurvey.setPsNum(entity.getPsNum());
        psurvey.setRqNum(entity.getRqNum());
        psurvey.setMId(entity.getMId());
        psurvey.setPsName(entity.getPsName());
        psurvey.setPsInfo(entity.getPsInfo());
        psurvey.setPsPicName(entity.getPsPicName());
        psurvey.setPsDate(entity.getPsDate());
        psurvey.setPsCount(entity.getPsCount());
        psurvey.setPsPrice(entity.getPsPrice());
        psurvey.setPsFDate(entity.getPsFDate());

        return psurvey;
    }

}
