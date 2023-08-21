package com.icia.mgs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("csco")
public class csCommentDTO {

    private int ccNum;
    private int csNum;
    private String mId;
    private String cComment;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date ccDate;

    public static csCommentDTO toDTO(csCommentEntity entity){
        csCommentDTO csco = new csCommentDTO();

        csco.setCcNum(entity.getCcNum());
        csco.setCsNum(entity.getCsNum());
        csco.setMId(entity.getMId());
        csco.setCComment(entity.getCComment());
        csco.setCcDate(entity.getCcDate());

        return csco;
    }



}
