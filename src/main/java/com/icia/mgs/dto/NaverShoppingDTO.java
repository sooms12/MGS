package com.icia.mgs.dto;

import lombok.Data;

@Data
public class NaverShoppingDTO {

    // 쇼핑관련
    private String Image;     // 메인사진
    private String Title;     // 상품이름
    private String Price;     // 상품가격
    private String mallName;  // 판매사이트이름
    private String Link;      // 판매링크
    private String keyword;   // 키워드
    private String result;    // 결과


}
