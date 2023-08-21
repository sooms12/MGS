package com.icia.mgs.dao;

import com.icia.mgs.dto.PLikeDTO;
import com.icia.mgs.dto.PLikeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PLikeDAO {
    PLikeDTO likeup1(PLikeDTO plike);

    PLikeDTO likedown1(PLikeDTO plike);

    PLikeEntity findid(String mId , int sNum);

    PLikeDTO plist(String mId);
}
