package com.dark.ending.service;

import com.dark.ending.repo.OrderRepository;
import com.dark.entity.Order;
import com.dark.model.OrderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndingService {

    private final OrderRepository repository;

    @Autowired
    public EndingService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order finishOrder(String id, String manualMark) {
        var order = repository.getReferenceById(id);
        order.setStatus(OrderStatus.CLOSED);
        order.setManualMark(manualMark);
        return repository.saveAndFlush(order);
    }
}
