package com.icia.mgs.dao;

import com.icia.mgs.dto.ProductDTO;
import com.icia.mgs.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDAO {
    
    // 상품 검색
    List<ProductDTO> pSearchList(SearchDTO search);

    // 상품 목록
    List<ProductDTO> productList();

    // 상품 상세보기
    ProductDTO pView(int pNum);

    // 좋아요
    ProductDTO likeup2(int pNum);

    ProductDTO likedown2(int pNum);

    ProductDTO likecheck(int pNum);
}
