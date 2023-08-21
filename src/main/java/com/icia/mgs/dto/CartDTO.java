package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("cart")
public class CartDTO {

    private int ctNum;      //장바구니 번호
    private String mId;     //회원아이디
    private int sNum;       //상품판매번호
    private int cCount;     //상품수량

    private String pName;
    private String pmPicName;
    private int pPrice;
    private int sdPrice;

    public static CartDTO toDTO(CartEntity entity) {
        CartDTO cart = new CartDTO();

        cart.setCtNum(entity.getCtNum());
        cart.setMId(entity.getMId());
        cart.setSNum(entity.getSNum());
        cart.setCCount(entity.getCCount());
        return cart;
    }


}
