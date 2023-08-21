package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;


@Data
@Alias("request")
public class RequestDTO {
    private int rqNum;          // 요청서 번호
    private String mId;         // 요청서 작성자
    private String rqPName;     // 요청 상품 이름
    private String rqLink;      // 요청 상품 링크
    private Date rqDate;        // 작성일
    private String rqType;      // 진행상태

    public static RequestDTO toDTO(RequestEntity entity){
        RequestDTO request = new RequestDTO();

        request.setRqNum(entity.getRqNum());
        request.setMId(entity.getMId());
        request.setRqPName(entity.getRqPName());
        request.setRqLink(entity.getRqLink());
        request.setRqDate(entity.getRqDate());
        request.setRqType(entity.getRqType());

        return request;
    }
}
