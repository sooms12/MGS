package com.icia.mgs.dao;

import com.icia.mgs.dto.OrdertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdertRepository extends JpaRepository<OrdertEntity, String> {

    List<OrdertEntity> findBymId(String id);
}
