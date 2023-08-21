package com.icia.mgs.dao;

import com.icia.mgs.dto.pCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface pCommentRepository extends JpaRepository<pCommentEntity, Integer> {


    @Query("SELECT b FROM  pCommentEntity b ORDER BY b.pcNum DESC")
    List<pCommentEntity> findBysNumAndPcTypeOrderByPcNumDesc(int sNum, int pcType);
}
