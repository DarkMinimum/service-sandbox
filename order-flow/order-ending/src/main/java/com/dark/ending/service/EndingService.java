package com.dark.ending.service;

import com.dark.ending.repo.OrderRepository;
import com.dark.entity.Order;
import com.dark.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndingService {

    @Autowired
    private OrderRepository repository;

    public Order finishOrder(String id) {
        var order = repository.getReferenceById(id);
        order.setStatus(OrderStatus.CLOSED);
        return repository.saveAndFlush(order);
    }
}
