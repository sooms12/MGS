package com.icia.mgs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Alias("pco")
public class pCommentDTO {

    private int pcNum;
    private int sNum;
    private String mId;
    private String pcComment;
    private String pcPicName1;
    private String pcPicName2;
    private String pcPicName3;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date pcDate;

    private int pcType;
    private int qcType;

    private MultipartFile[] pcFile;

    public static pCommentDTO toDTO(pCommentEntity entity){
        pCommentDTO pco = new pCommentDTO();

        pco.setPcNum(entity.getPcNum());
        pco.setMId(entity.getMId());
        pco.setPcComment(entity.getPcComment());
        pco.setPcPicName1(entity.getPcPicName1());
        pco.setPcPicName2(entity.getPcPicName2());
        pco.setPcPicName3(entity.getPcPicName3());
        pco.setPcDate(entity.getPcDate());
        pco.setPcType(entity.getPcType());
        pco.setQcType(entity.getQcType());
        pco.setSNum(entity.getSNum());

        return pco;
    }



}
