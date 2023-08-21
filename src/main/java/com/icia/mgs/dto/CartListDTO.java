package com.icia.mgs.dto;

import lombok.Data;

@Data
public class CartListDTO {
    private int pNum;               // 상품 번호
    private String pName;           // 상품 이름
    private int pPrice;             // 상품 가격
    private String pmPicName;       // 메인사진 이름
    private int sNum;               // 판매 번호
    private int sdPrice;            // 배송비
}
