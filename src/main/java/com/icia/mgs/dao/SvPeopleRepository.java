package com.icia.mgs.dao;

import com.icia.mgs.dto.SvPeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SvPeopleRepository extends JpaRepository<SvPeopleEntity, Integer> {
    Optional<SvPeopleEntity> findBymIdAndPsNum(String mId, int psNum);

    List<SvPeopleEntity> findByPsNum(int psNum);

    List<SvPeopleEntity> findBymId(String mId);
}
