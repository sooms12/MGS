package com.icia.mgs.dao;

import com.icia.mgs.dto.PLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PLikeRepository extends JpaRepository <PLikeEntity, Integer> {

    List<PLikeEntity> findBymId(String mId);
}
