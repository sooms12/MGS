package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "ORDERT")
public class OrdertEntity {

    @Id
    @Column
    private String oNum;   //주문번호
    @Column
    @CreationTimestamp
    private Date oDate;  //결제일자
    @Column
    private int sNum;    //상품판매번호
    @Column
    private String oStatus; // 결제상태
    @Column
    private String mId; //주문자아이디
    @Column
    private int oPay;   // pay
    @Column
    private int oCount; //수량
    @Column
    private int oPrice; //가격
    @Column
    private String oState; // 배송상태

    public static OrdertEntity toEntity(OrdertDTO ordert){
        OrdertEntity entity = new OrdertEntity();

        entity.setONum(ordert.getONum());
        entity.setODate(ordert.getODate());
        entity.setSNum(ordert.getSNum());
        entity.setMId(ordert.getMId());
        entity.setOStatus(ordert.getOStatus());
        entity.setOPay(ordert.getOPay());
        entity.setOCount(ordert.getOCount());
        entity.setOPrice(ordert.getOPrice());
        entity.setOState(ordert.getOState());
        return entity;
    }

}
