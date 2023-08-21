package com.icia.mgs.dao;

import com.icia.mgs.dto.PSurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PSurveyRepository extends JpaRepository<PSurveyEntity, Integer> {
    List<PSurveyEntity> findByPsNum(int psNum);

    Optional<PSurveyEntity> findByrqNum(int rqNum);
}
