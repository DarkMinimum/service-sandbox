package com.dark.entity;

import com.dark.model.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ORDER_TABLE")
public class Order {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private OrderStatus status;

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

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", productIds=" + productIds +
                '}';
    }
}