package com.icia.mgs.dao;

import com.icia.mgs.dto.csCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface csCommentRepository extends JpaRepository<csCommentEntity, Integer> {

    List<csCommentEntity> findByCsNum(Integer csNum);

    List<csCommentEntity> deleteByCsNumAndCcNum(Integer csNum, Integer ccNum );

    @Query("SELECT b FROM  csCommentEntity b ORDER BY b.ccNum ASC")
    List<csCommentEntity> findByCsNumOrderByCcNum(int csNum);
}
