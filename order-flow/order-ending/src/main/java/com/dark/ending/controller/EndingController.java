package com.dark.ending.controller;

import com.dark.ending.service.EndingService;
import com.dark.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndingController {

    @Autowired
    private EndingService service;

    @PostMapping("/order/{orderId}")
    public Order finishOrder(@PathVariable String orderId) {
        return service.finishOrder(orderId);
    }

    @GetMapping("/order/{orderId}")
    public Order getFinishedOrder(@PathVariable String orderId) {
        return service.finishOrder(orderId);
    }

}
