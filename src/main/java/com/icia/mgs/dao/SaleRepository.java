package com.icia.mgs.dao;

import com.icia.mgs.dto.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<SaleEntity, Integer> {
    List<SaleEntity> findBysNum(int sNum);

    Optional<SaleEntity> findBypNum(int pNum);

    List<SaleEntity> findBysType(int type);
}
