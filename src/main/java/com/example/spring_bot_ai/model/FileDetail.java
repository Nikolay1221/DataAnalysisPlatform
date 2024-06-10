package com.example.spring_bot_ai.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "table_details")
public class FileDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate uploadDate;
    private String tableName;
    private int rowCount;

    @OneToMany(mappedBy = "fileDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DataCompanyTurnover> dataCompanyTurnovers;

    @OneToMany(mappedBy = "fileDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<FilteredData> filteredData;

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

    public List<DataCompanyTurnover> getDataCompanyTurnovers() {
        return dataCompanyTurnovers;
    }

    public void setDataCompanyTurnovers(List<DataCompanyTurnover> dataCompanyTurnovers) {
        this.dataCompanyTurnovers = dataCompanyTurnovers;
    }

    public List<FilteredData> getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(List<FilteredData> filteredData) {
        this.filteredData = filteredData;
    }
}
