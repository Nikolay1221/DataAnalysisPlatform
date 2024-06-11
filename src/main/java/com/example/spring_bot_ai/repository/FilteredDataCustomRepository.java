package com.example.spring_bot_ai.repository;

import com.example.spring_bot_ai.model.FilteredData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilteredDataCustomRepository extends JpaRepository<FilteredData, Long> {
    void deleteByIdAndFileDetailId(Long id, Long fileDetailId);
}
