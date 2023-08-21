package com.icia.mgs.dao;

import com.icia.mgs.dto.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {


    List<ReplyEntity> findByPcNum(int pcNum);


    @Query("SELECT b FROM  ReplyEntity b ORDER BY b.reNum DESC")
    List<ReplyEntity> findBysNumOrderByReNumDesc(int sNum);
}
