package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Alias("sale")
public class SaleDTO{
    private int sNum;               // 판매 번호
    private int sDiscount;          // 할인율
    private int sdPrice;            // 배송비
    private int sHit;               // 추천수
    private int sCount;             // 재고수량
    private Date sDate;             // 작성일
    private String sfDate;          // 마감일
    private int sType;              // 상품 타입 1-일반, 2-공동
    private int pNum;               // 상품 번호
    private int likeCount;          // 좋아요 수

    private String pName;           // 상품 이름
    private int pPrice;             // 상품 가격
    private String pspInfo;         // 상품간단정보
    private String pInfo;           // 상품 정보
    private String pmPicName;       // 메인사진 이름
    private String piPicName1;      // 상세사진1 이름
    private String piPicName2;      // 상세사진2 이름
    private String piPicName3;      // 상세사진3 이름

    public static SaleDTO toDTO(SaleEntity entity){
        SaleDTO sale = new SaleDTO();

        sale.setSNum(entity.getSNum());
        sale.setSDiscount(entity.getSDiscount());
        sale.setSdPrice(entity.getSdPrice());
        sale.setSHit(entity.getSHit());
        sale.setSCount(entity.getSCount());
        sale.setSDate(entity.getSDate());
        sale.setSfDate(entity.getSfDate());
        sale.setSType(entity.getSType());
        sale.setPNum(entity.getPNum());
        sale.setLikeCount(entity.getLikeCount());

        return sale;
    }
}
