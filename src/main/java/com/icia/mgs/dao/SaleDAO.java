package com.icia.mgs.dao;

import com.icia.mgs.dto.SaleDTO;
import com.icia.mgs.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleDAO {

    // 판매상품목록
    List<SaleDTO> saleList();

    // 판매상품검색
    List<SaleDTO> sSearchList(SearchDTO search);

    // 판매상품 상세보기
    SaleDTO sView(int sNum);

    SaleDTO likeup2(int sNum);

    SaleDTO likedown2(int sNum);

    SaleDTO likecheck(int sNum);
    List<SaleDTO> saleList2(int type);
}
