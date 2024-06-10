package com.example.spring_bot_ai.dto;

import com.example.spring_bot_ai.model.DataCompanyTurnover;
import com.example.spring_bot_ai.model.FilteredData;

import java.time.LocalDate;
import java.util.List;

public class FileDetailWithDataDTO {
    private Long id;
    private LocalDate uploadDate;
    private String tableName;
    private int rowCount;
    private List<DataCompanyTurnover> turnoverData;
    private List<FilteredData> generatedData;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<DataCompanyTurnover> getTurnoverData() {
        return turnoverData;
    }

    public void setTurnoverData(List<DataCompanyTurnover> turnoverData) {
        this.turnoverData = turnoverData;
    }

    public List<FilteredData> getGeneratedData() {
        return generatedData;
    }

    public void setGeneratedData(List<FilteredData> generatedData) {
        this.generatedData = generatedData;
    }
}
