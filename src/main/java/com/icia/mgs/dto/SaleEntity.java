package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SALE")
@SequenceGenerator(name = "SALE_SEQ_GENERATOR", sequenceName = "SALE_SEQ", allocationSize = 1)

public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALE_SEQ_GENERATOR")
    private int sNum;               // 판매 번호
    @Column
    private int sDiscount;          // 할인율
    @Column
    private int sdPrice;            // 배송비
    @Column
    private int sHit;               // 추천수
    @Column
    private int sCount;             // 재고수량
    @Column
    @CreationTimestamp
    private Date sDate;             // 작성일
    @Column
    private String sfDate;          // 마감일
    @Column
    private int sType;              // 상품 타입 1-일반, 2-공동
    @Column
    private int pNum;               // 상품 번호
    @Column
    private int likeCount;          // 좋아요 수

    public static SaleEntity toEntity(SaleDTO sale){
        SaleEntity entity = new SaleEntity();

        entity.setSNum(sale.getSNum());
        entity.setSDiscount(sale.getSDiscount());
        entity.setSdPrice(sale.getSdPrice());
        entity.setSHit(sale.getSHit());
        entity.setSCount(sale.getSCount());
        entity.setSDate(sale.getSDate());
        entity.setSfDate(sale.getSfDate());
        entity.setSType(sale.getSType());
        entity.setPNum(sale.getPNum());
        entity.setLikeCount(sale.getLikeCount());

        return entity;
    }

}
