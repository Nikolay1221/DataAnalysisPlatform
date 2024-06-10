package com.example.spring_bot_ai.repository;

import com.example.spring_bot_ai.model.FileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileDetailRepository extends JpaRepository<FileDetail, Long> {
    @Query("SELECT fd FROM FileDetail fd WHERE fd.id = (SELECT MAX(fd.id) FROM FileDetail fd)")
    FileDetail findTopByOrderByIdDesc();
}
