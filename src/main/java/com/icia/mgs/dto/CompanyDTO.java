package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("company")
public class CompanyDTO {
    private int cNum;       // 회사 번호
    private String cName;   // 회사 이름
    private String cAddr;   // 회사 주소
    private String cPhone;  // 회사 연락처

    public static CompanyDTO toDTO(CompanyEntity entity){
        CompanyDTO company = new CompanyDTO();

        company.setCNum(entity.getCNum());
        company.setCName(entity.getCName());
        company.setCAddr(entity.getCAddr());
        company.setCPhone(entity.getCPhone());

        return company;
    }
}
