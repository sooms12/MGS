package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="REQUEST")
@SequenceGenerator(name="REQUEST_SEQ_GENERATOR", sequenceName ="REQUEST_SEQ" , allocationSize = 1)
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUEST_SEQ_GENERATOR")
    private int rqNum;          // 요청서 번호
    @Column
    private String mId;         // 요청서 작성자
    @Column
    private String rqPName;     // 요청 상품 이름
    @Column
    private String rqLink;      // 요청 상품 링크
    @Column
    @UpdateTimestamp
    private Date rqDate;        // 작성일
    @Column
    private String rqType;      // 진행상태

    public static RequestEntity toEntity(RequestDTO request){
        RequestEntity entity = new RequestEntity();

        entity.setRqNum(request.getRqNum());
        entity.setMId(request.getMId());
        entity.setRqPName(request.getRqPName());
        entity.setRqLink(request.getRqLink());
        entity.setRqDate(request.getRqDate());
        entity.setRqType(request.getRqType());

        return entity;
    }
}
