package com.example.spring_bot_ai.repository;

import com.example.spring_bot_ai.model.DataCompanyTurnover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCompanyTurnoverDeletionRepository extends JpaRepository<DataCompanyTurnover, Long> {
    void deleteByFileDetailId(Long fileDetailId);
}
