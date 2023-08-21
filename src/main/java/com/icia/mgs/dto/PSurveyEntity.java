package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="PSURVEY")
@SequenceGenerator(name="PSURVEY_SEQ_GENERATOR", sequenceName = "PSURVEY_SEQ" , allocationSize = 1)
public class PSurveyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PSURVEY_SEQ_GENERATOR")
    private int psNum;           // 인원조사 번호
    @Column
    private int rqNum;           // 신청서 번호
    @Column
    private String mId;          // 작성자 아이디
    @Column
    private String psName;       // 상품이름
    @Column
    private String psInfo;       // 상품정보
    @Column
    private String psPicName;    // 상품 사진
    @Column
    @UpdateTimestamp
    private Date psDate;         // 등록일
    @Column
    private int psCount;         // 상품수량
    @Column
    private int psPrice;         // 가격
    @Column
    private String psFDate;        // 마감일

    public static PSurveyEntity toEntity(PSurveyDTO psurvey){
        PSurveyEntity entity = new PSurveyEntity();

        entity.setPsNum(psurvey.getPsNum());
        entity.setRqNum(psurvey.getRqNum());
        entity.setMId(psurvey.getMId());
        entity.setPsName(psurvey.getPsName());
        entity.setPsInfo(psurvey.getPsInfo());
        entity.setPsPicName(psurvey.getPsPicName());
        entity.setPsDate(psurvey.getPsDate());
        entity.setPsCount(psurvey.getPsCount());
        entity.setPsPrice(psurvey.getPsPrice());
        entity.setPsFDate(psurvey.getPsFDate());

        return entity;
    }

}
