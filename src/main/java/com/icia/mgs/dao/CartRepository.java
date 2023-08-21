package com.icia.mgs.dao;

import com.icia.mgs.dto.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer>{
    // 해당 사용자의 장바구니 추가
    List<CartEntity> findAllBymId(String mId);
}
