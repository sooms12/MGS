package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("svpeople")
public class SvPeopleDTO{
    private int svNum;      // 신청인원번호
    private int psNum;      // 인원조사 번호
    private String mId;     // 구매자아이디
    private int svCount;    // 구매개수
    private String psName;

    private static SvPeopleDTO toDTO(SvPeopleEntity entity){
        SvPeopleDTO svpeople = new SvPeopleDTO();

        svpeople.setSvNum(entity.getSvNum());
        svpeople.setPsNum(entity.getPsNum());
        svpeople.setMId(entity.getMId());
        svpeople.setSvCount(entity.getSvCount());

        return svpeople;

    }

}
