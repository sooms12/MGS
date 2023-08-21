package com.icia.mgs.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PLIKE")
@SequenceGenerator(name="PLIKE_SEQ_GENERATOR" , sequenceName = "PLIKE_SEQ", allocationSize = 1)
public class PLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLIKE_SEQ_GENERATOR")
    private int lNum;
    @Column
    private String mId;
    @Column
    private int sNum;
    @Column
    private int pNum;

    public static PLikeEntity toEntity(PLikeDTO plike){
        PLikeEntity entity = new PLikeEntity();

        entity.setLNum(plike.getLNum());
        entity.setMId(plike.getMId());
        entity.setSNum(plike.getSNum());
        entity.setPNum(plike.getPNum());

        return entity;
    }

}
