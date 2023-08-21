package com.icia.mgs.dao;

import com.icia.mgs.dto.CompanyDTO;
import com.icia.mgs.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyDAO {
    
    // 회사 검색
    List<CompanyDTO> cSearchList(SearchDTO search);
}
