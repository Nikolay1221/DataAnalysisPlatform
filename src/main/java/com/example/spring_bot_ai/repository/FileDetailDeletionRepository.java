package com.example.spring_bot_ai.repository;

import com.example.spring_bot_ai.model.FileDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDetailDeletionRepository extends JpaRepository<FileDetail, Long> {
    void deleteById(Long id);
}
