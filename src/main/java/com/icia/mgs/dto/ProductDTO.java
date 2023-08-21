package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Data
@Alias("product")
public class ProductDTO {
    private int pNum;               // 상품 번호
    private String pName;           // 상품 이름
    private int pPrice;             // 상품 가격
    private String pInfo;           // 상품 정보
    private String pspInfo;         // 상품 간단정보

    private String pmPicName;       // 메인사진 이름
    private String piPicName1;      // 상세사진1 이름
    private String piPicName2;      // 상세사진2 이름
    private String piPicName3;      // 상세사진3 이름
    private int cNum;               // 회사 번호

    private MultipartFile[] pmPic;    // 메인사진
    private String cName;             // 회사 이름

    public static ProductDTO toDTO(ProductEntity entity){
        ProductDTO product = new ProductDTO();

        product.setPNum(entity.getPNum());
        product.setPName(entity.getPName());
        product.setPPrice(entity.getPPrice());
        product.setPInfo(entity.getPInfo());
        product.setPmPicName(entity.getPmPicName());
        product.setPiPicName1(entity.getPiPicName1());
        product.setPiPicName2(entity.getPiPicName2());
        product.setPiPicName3(entity.getPiPicName3());
        product.setCNum(entity.getCNum());
        product.setPspInfo(entity.getPspInfo());
        return product;
    }
}
