package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PCOMMENT")
@SequenceGenerator(name = "PCOMMENT_SEQ_GENERATOR", sequenceName = "PCOMMENT_SEQ", allocationSize = 1)
public class pCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PCOMMENT_SEQ_GENERATOR")
    private int pcNum;

    @Column
    private String mId;

    @Column
    private String pcComment;

    @Column
    private String pcPicName1;

    @Column
    private String pcPicName2;

    @Column
    private String pcPicName3;

    @Column
    private int sNum;

    @Column
    @UpdateTimestamp
    private Date pcDate;

    @Column
    private int pcType;

    @Column
    private int qcType;

    public static pCommentEntity toEntity(pCommentDTO pco){
        pCommentEntity entity = new pCommentEntity();

        entity.setPcNum(pco.getPcNum());
        entity.setMId(pco.getMId());
        entity.setPcComment(pco.getPcComment());
        entity.setPcPicName1(pco.getPcPicName1());
        entity.setPcPicName2(pco.getPcPicName2());
        entity.setPcPicName3(pco.getPcPicName3());
        entity.setPcDate(pco.getPcDate());
        entity.setPcType(pco.getPcType());
        entity.setQcType(pco.getQcType());
        entity.setSNum(pco.getSNum());

        return entity;
    }
}
