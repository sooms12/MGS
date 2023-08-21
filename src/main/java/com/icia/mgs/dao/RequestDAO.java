package com.icia.mgs.dao;

import com.icia.mgs.dto.RequestDTO;
import com.icia.mgs.dto.RequestEntity;
import com.icia.mgs.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestDAO {

    List<RequestDTO> rqSearchList(SearchDTO search);

    List<RequestDTO> rqSearchList2(SearchDTO search, String mId);

    RequestEntity typechange(int rqNum);
}
