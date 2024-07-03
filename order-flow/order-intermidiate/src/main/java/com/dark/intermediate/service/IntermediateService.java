package com.dark.intermediate.service;

import com.dark.entity.Order;
import com.dark.intermediate.repo.OrderRepository;
import com.dark.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntermediateService {

    private final OrderRepository repository;

    @Autowired
    public IntermediateService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order updateOrderStatus(String id) {
        var order = repository.getReferenceById(id);
        order.setStatus(OrderStatus.SHIPPING);
        return repository.saveAndFlush(order);
    }
}
