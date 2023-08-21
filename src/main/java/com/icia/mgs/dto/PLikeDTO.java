package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("plike")
public class PLikeDTO extends SaleDTO{
    private int lNum;
    private String mId;
    private int sNum;
    private int pNum;

    public static PLikeDTO toDTO(PLikeEntity entity){
        PLikeDTO plike = new PLikeDTO();

        plike.setLNum(entity.getLNum());
        plike.setMId(entity.getMId());
        plike.setSNum(entity.getSNum());
        plike.setPNum(entity.getPNum());

        return plike;
    }

}
