package com.dark.starter.service;

import com.dark.entity.Order;
import com.dark.model.OrderStatus;
import com.dark.starter.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarterService {

    @Autowired
    private OrderRepository repository;

    public Long createOrder(List<String> productIds) {
        var order = new Order();
        order.setProductIds(productIds);
        order.setStatus(OrderStatus.STARTED);
        return repository.save(order).getId();
    }
}
