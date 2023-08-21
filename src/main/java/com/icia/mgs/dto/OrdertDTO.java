package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("ordert")
public class OrdertDTO extends MemberDTO{
    private String oNum;    //주문번호
    private String mId;  //주문자아이디
    private Date oDate;  //결재일자
    private int sNum;    //상품판매번호
    private String oStatus; // 결재상태
    private int   oPay;  //pay
    private int oCount;  //수량
    private int oPrice;  //가격
    private String oState; // 배송상태




    public static OrdertDTO toDTO(OrdertEntity entity){
        OrdertDTO ordert = new OrdertDTO();

        ordert.setONum(entity.getONum());
        ordert.setODate(entity.getODate());
        ordert.setSNum(entity.getSNum());
        ordert.setMId(entity.getMId());
        ordert.setOStatus(entity.getOStatus());
        ordert.setOPay(entity.getOPay());
        ordert.setOCount(entity.getOCount());
        ordert.setOPrice(entity.getOPrice());
        ordert.setOState(entity.getOState());
        return ordert;
    }

}
