package com.icia.mgs.dao;

import com.icia.mgs.dto.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
    // rqNum 정렬
    List<RequestEntity> findAllByOrderByRqNumDesc();

    List<RequestEntity> findBymId(String mId);
}
