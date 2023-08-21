package com.icia.mgs.dao;

import com.icia.mgs.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDAO {
    // 장바구니 담은 목록 리스트
    List<CartDTO> ctlist(String mId);
}
