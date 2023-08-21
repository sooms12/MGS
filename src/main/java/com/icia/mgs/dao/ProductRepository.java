package com.icia.mgs.dao;

import com.icia.mgs.dto.MemberEntity;
import com.icia.mgs.dto.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findBypNum(int pNum);
}
