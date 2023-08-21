package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("search")
public class SearchDTO {
    private String category;    // 검색 카테고리
    private String keyword;     // 검색 키워드
}
