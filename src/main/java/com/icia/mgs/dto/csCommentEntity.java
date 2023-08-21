package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "CSCOMMENT")
@SequenceGenerator(name = "CSCOMMENT_SEQ_GENERATOR", sequenceName = "CSCOMMENT_SEQ", allocationSize = 1)
public class csCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CSCOMMENT_SEQ_GENERATOR")
    private int ccNum;

    @Column
    private int csNum;

    @Column
    private String mId;

    @Column
    private String cComment;

    @Column
    @UpdateTimestamp
    private Date ccDate;

    public static csCommentEntity toEntity(csCommentDTO csco){
        csCommentEntity entity = new csCommentEntity();

        entity.setCcNum(csco.getCcNum());
        entity.setCsNum(csco.getCsNum());
        entity.setMId(csco.getMId());
        entity.setCComment(csco.getCComment());
        entity.setCcDate(csco.getCcDate());

        return entity;
    }
}
