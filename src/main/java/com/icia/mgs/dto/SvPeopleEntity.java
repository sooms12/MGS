package com.icia.mgs.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SVPEOPLE")
@SequenceGenerator(name = "SVPEOPLE_SEQ_GENERATOR", sequenceName = "SVPEOPLE_SEQ" , allocationSize = 1)
public class SvPeopleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SVPEOPLE_SEQ_GENERATOR")
    private int svNum;
    @Column
    public int psNum;
    @Column
    private String mId;
    @Column
    private int svCount;

    private static SvPeopleEntity toEntity(SvPeopleDTO svpeople){
        SvPeopleEntity entity = new SvPeopleEntity();

        entity.setSvNum(svpeople.getSvNum());
        entity.setPsNum(svpeople.getPsNum());
        entity.setMId(svpeople.getMId());
        entity.setSvCount(svpeople.getSvCount());

        return entity;
    }

}
