
package com.icia.mgs.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CART")
@SequenceGenerator(name = "CART_SEQ_GENERATOR", sequenceName = "CART_SEQ", allocationSize = 1)
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ_GENERATOR")
    private int ctNum;      //장바구니 번호
    @Column
    private String mId;     //회원아이디
    @Column
    private int sNum;       // 상품판매번호
    @Column
    private int  cCount;    // 상품수량




    public static CartEntity toEntity(CartDTO cart){
        CartEntity entity = new CartEntity();

        entity.setCtNum(cart.getCtNum());
        entity.setMId(cart.getMId());
        entity.setSNum(entity.getSNum());
        entity.setCCount(entity.getCCount());
        return entity;
    }

}