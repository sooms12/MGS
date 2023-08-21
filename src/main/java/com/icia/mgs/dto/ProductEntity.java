package com.icia.mgs.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(name = "PRODUCT_SEQ_GENERATOR", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ_GENERATOR")
    private int pNum;               // 상품 번호
    @Column
    private String pName;           // 상품 이름
    @Column
    private int pPrice;             // 상품 가격
    @Column
    private String pInfo;           // 상품 정보
    @Column
    private String pmPicName;       // 메인사진 이름
    @Column
    private String piPicName1;      // 상세사진1 이름
    @Column
    private String piPicName2;      // 상세사진2 이름
    @Column
    private String piPicName3;      // 상세사진3 이름
    @Column
    private int cNum;               // 회사 번호
    @Column
    private String pspInfo;         // 상품간단정보

    public static ProductEntity toEntity(ProductDTO product){
        ProductEntity entity = new ProductEntity();

        entity.setPNum(product.getPNum());
        entity.setPName(product.getPName());
        entity.setPPrice(product.getPPrice());
        entity.setPInfo(product.getPInfo());
        entity.setPmPicName(product.getPmPicName());
        entity.setPiPicName1(product.getPiPicName1());
        entity.setPiPicName2(product.getPiPicName2());
        entity.setPiPicName3(product.getPiPicName3());
        entity.setCNum(product.getCNum());
        entity.setPspInfo(product.getPspInfo());

        return entity;
    }
}
