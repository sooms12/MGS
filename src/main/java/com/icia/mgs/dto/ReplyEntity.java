package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "REPLY")
@SequenceGenerator(name = "REPLY_SEQ_GENERATOR", sequenceName = "REPLY_SEQ", allocationSize = 1)
public class ReplyEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLY_SEQ_GENERATOR")
    private int reNum;

    @Column
    private int pcNum;

    @Column
    private int sNum;

    @Column
    private int pNum;

    @Column
    private String mId;

    @Column
    private String reComment;

    @Column
    @UpdateTimestamp
    private Date reDate;

    @Column
    private int isDelete;


    public static ReplyEntity toEntity(ReplyDTO reply){
        ReplyEntity entity = new ReplyEntity();

        entity.setReNum(reply.getReNum());
        entity.setPcNum(reply.getPcNum());
        entity.setSNum(reply.getSNum());
        entity.setPNum(reply.getPNum());
        entity.setMId(reply.getMId());
        entity.setReComment(reply.getReComment());
        entity.setReDate(reply.getReDate());
        entity.setIsDelete(reply.getIsDelete());

        return entity;
    }
}
