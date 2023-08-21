package com.icia.mgs.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMPANY")
@SequenceGenerator(name = "COMPANY_SEQ_GENERATOR", sequenceName = "COMPANY_SEQ", allocationSize = 1)
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ_GENERATOR")
    private int cNum;       // 회사 번호
    @Column
    private String cName;   // 회사 이름
    @Column
    private String cAddr;   // 회사 주소
    @Column
    private String cPhone;  // 회사 연락처

    public static CompanyEntity toEntity(CompanyDTO company){
        CompanyEntity entity = new CompanyEntity();

        entity.setCNum(company.getCNum());
        entity.setCName(company.getCName());
        entity.setCAddr(company.getCAddr());
        entity.setCPhone(company.getCPhone());

        return entity;
    }
}
