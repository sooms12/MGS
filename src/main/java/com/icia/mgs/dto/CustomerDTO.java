package com.icia.mgs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("customer")
public class CustomerDTO {

    private int csNum;
    private String mId;
    private String csTitle;
    private String csContent;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date csDate;

    private int csType;


    public static CustomerDTO toDTO(CustomerEntity entity){
        CustomerDTO customer = new CustomerDTO();

        customer.setCsNum(entity.getCsNum());
        customer.setMId(entity.getMId());
        customer.setCsTitle(entity.getCsTitle());
        customer.setCsContent(entity.getCsContent());
        customer.setCsDate(entity.getCsDate());
        customer.setCsType(entity.getCsType());

        return customer;
    }




}
