package com.icia.mgs.dao;

import com.icia.mgs.dto.MemberDTO;
import com.icia.mgs.dto.MemberEntity;
import com.icia.mgs.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberDAO {
    
    // 회원 검색
    List<MemberDTO> searchList(SearchDTO search);

    Optional<MemberEntity> emailCheck2(String mEmail);

    Optional<MemberEntity> emailCheck3(String mEmail);
}
