package com.dark.starter.repo;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

@Entity(name = "ORDER_TABLE")
public class Order {

    @Id
    private String id;

    private String markField;

    @ElementCollection
    private List<String> productIds;

    public Order() {
    }

    public Order(String id, String markField, List<String> productIds) {
        this.id = id;
        this.markField = markField;
        this.productIds = productIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarkField() {
        return markField;
    }

    public void setMarkField(String markField) {
        this.markField = markField;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", markField='" + markField + '\'' +
                ", productIds=" + productIds +
                '}';
    }
}