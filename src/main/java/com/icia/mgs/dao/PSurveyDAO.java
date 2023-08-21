package com.icia.mgs.dao;

import com.icia.mgs.dto.PSurveyDTO;
import com.icia.mgs.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PSurveyDAO {

    List<PSurveyDTO> psSearchList(SearchDTO search);
}
