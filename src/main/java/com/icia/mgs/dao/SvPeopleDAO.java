package com.icia.mgs.dao;

import com.icia.mgs.dto.SvPeopleDTO;
import com.icia.mgs.dto.SvPeopleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SvPeopleDAO {

    SvPeopleDTO svapply(SvPeopleDTO svpeople);

    SvPeopleDTO svcancel(SvPeopleDTO svpeople);

    Optional<SvPeopleEntity> svfindBymIdAndPsNum(String mId, int psNum);

}
