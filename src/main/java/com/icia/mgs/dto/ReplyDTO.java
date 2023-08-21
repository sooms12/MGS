package com.icia.mgs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("reply")
public class ReplyDTO {

    private int reNum;
    private int pcNum;
    private int sNum;
    private int pNum;
    private String mId;
    private String reComment;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date reDate;
    private int isDelete;

    public static ReplyDTO toDTO(ReplyEntity entity){
        ReplyDTO reply = new ReplyDTO();

        reply.setReNum(entity.getReNum());
        reply.setPcNum(entity.getPcNum());
        reply.setSNum(entity.getSNum());
        reply.setPNum(entity.getPNum());
        reply.setMId(entity.getMId());
        reply.setReComment(entity.getReComment());
        reply.setReDate(entity.getReDate());
        reply.setIsDelete(entity.getIsDelete());

        return reply;
    }

}
