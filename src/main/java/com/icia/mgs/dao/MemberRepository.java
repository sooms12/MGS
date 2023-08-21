package com.icia.mgs.dao;

import com.icia.mgs.dto.MemberDTO;
import com.icia.mgs.dto.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
}
