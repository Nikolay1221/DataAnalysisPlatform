package com.example.spring_bot_ai.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "filtered_data", schema = "public")
public class FilteredData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "file_detail_id", nullable = false)
    @JsonBackReference
    private FileDetail fileDetail;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("Predicted Sales")
    private double predictedSales;

    private double arrival;
    private double backorder;

    @JsonProperty("balance end")
    private double balanceEnd;

    @JsonProperty("balancestart")
    private double balanceStart;

    private double expenditure;

    @JsonProperty("month")
    private String month;

    private int orders;

    @JsonProperty("productcategory")
    private String productCategory;

    private double transit;
    private double turnover;

    @JsonProperty("turnover_diff")
    private double turnoverDiff;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getPredictedSales() {
        return predictedSales;
    }

    public void setPredictedSales(double predictedSales) {
        this.predictedSales = predictedSales;
    }

    public double getArrival() {
        return arrival;
    }

    public void setArrival(double arrival) {
        this.arrival = arrival;
    }

    public double getBackorder() {
        return backorder;
    }

    public void setBackorder(double backorder) {
        this.backorder = backorder;
    }

    public double getBalanceEnd() {
        return balanceEnd;
    }

    public void setBalanceEnd(double balanceEnd) {
        this.balanceEnd = balanceEnd;
    }

    public double getBalanceStart() {
        return balanceStart;
    }

    public void setBalanceStart(double balanceStart) {
        this.balanceStart = balanceStart;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getTransit() {
        return transit;
    }

    public void setTransit(double transit) {
        this.transit = transit;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getTurnoverDiff() {
        return turnoverDiff;
    }

    public void setTurnoverDiff(double turnoverDiff) {
        this.turnoverDiff = turnoverDiff;
    }
}
