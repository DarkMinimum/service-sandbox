package com.dark.entity;

import com.dark.model.OrderStatus;
import com.dark.model.OrderType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private OrderType type;


    private String manualMark;

    @ElementCollection
    private List<String> productIds;

    public Order() {
    }

    public Order(Long id, OrderStatus status, List<String> productIds) {
        this.id = id;
        this.status = status;
        this.productIds = productIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public String getManualMark() {
        return manualMark;
    }

    public void setManualMark(String manualMark) {
        this.manualMark = manualMark;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", productIds=" + productIds +
                '}';
    }
}