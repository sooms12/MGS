package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "CSERVICE")
@SequenceGenerator(name = "CSERVICE_SEQ_GENERATOR", sequenceName = "CSERVICE_SEQ", allocationSize = 1)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CSERVICE_SEQ_GENERATOR")
    private int csNum;

    @Column
    private String mId;

    @Column
    private String csTitle;

    @Column
    private String csContent;

    @Column
    @UpdateTimestamp
    private Date csDate;

    @Column
    private int csType;







    public static CustomerEntity toEntity(CustomerDTO customer){
        CustomerEntity entity = new CustomerEntity();

        entity.setCsNum(customer.getCsNum());
        entity.setMId(customer.getMId());
        entity.setCsTitle(customer.getCsTitle());
        entity.setCsContent(customer.getCsContent());
        entity.setCsDate(customer.getCsDate());
        entity.setCsType(customer.getCsType());


        return entity;
    }
}
