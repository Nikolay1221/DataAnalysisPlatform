package com.example.spring_bot_ai.repository;

import com.example.spring_bot_ai.model.FilteredData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilteredDataRepository extends JpaRepository<FilteredData, Long> {
    List<FilteredData> findByFileDetailId(Long fileDetailId);
}
