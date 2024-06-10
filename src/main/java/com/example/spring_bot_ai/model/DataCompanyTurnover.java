package com.example.spring_bot_ai.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "data_company_turnover", schema = "public")
public class DataCompanyTurnover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "file_detail_id", nullable = false)
    @JsonBackReference
    private FileDetail fileDetail;

    @JsonProperty("product_id")
    private int productId;

    @JsonProperty("yyyy_MM")
    private String yyyyMM;

    @JsonProperty("productCategory")
    private String productCategory;

    @JsonProperty("min")
    private int min;

    @JsonProperty("max")
    private int max;

    @JsonProperty("sales")
    private float sales;

    @JsonProperty("balanceStart")
    private float balanceStart;

    @JsonProperty("transit")
    private float transit;

    @JsonProperty("backorder")
    private float backorder;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FileDetail getFileDetail() {
        return fileDetail;
    }

    public void setFileDetail(FileDetail fileDetail) {
        this.fileDetail = fileDetail;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getYyyyMM() {
        return yyyyMM;
    }

    public void setYyyyMM(String yyyyMM) {
        this.yyyyMM = yyyyMM;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public float getSales() {
        return sales;
    }

    public void setSales(float sales) {
        this.sales = sales;
    }

    public float getBalanceStart() {
        return balanceStart;
    }

    public void setBalanceStart(float balanceStart) {
        this.balanceStart = balanceStart;
    }

    public float getTransit() {
        return transit;
    }

    public void setTransit(float transit) {
        this.transit = transit;
    }

    public float getBackorder() {
        return backorder;
    }

    public void setBackorder(float backorder) {
        this.backorder = backorder;
    }
}
