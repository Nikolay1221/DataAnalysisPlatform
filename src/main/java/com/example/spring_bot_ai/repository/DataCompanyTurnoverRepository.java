package com.example.spring_bot_ai.repository;

import com.example.spring_bot_ai.model.DataCompanyTurnover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataCompanyTurnoverRepository extends JpaRepository<DataCompanyTurnover, Long> {
    List<DataCompanyTurnover> findByFileDetailId(Long fileDetailId);
}


