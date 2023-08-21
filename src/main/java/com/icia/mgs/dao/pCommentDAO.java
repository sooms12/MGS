package com.icia.mgs.dao;

import com.icia.mgs.dto.OrdertDTO;
import com.icia.mgs.dto.pCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface pCommentDAO {

    int rvpCheck(OrdertDTO order);

    List<pCommentDTO> rvList(pCommentDTO pco);

    List<pCommentDTO> cmList(pCommentDTO pco);
}
