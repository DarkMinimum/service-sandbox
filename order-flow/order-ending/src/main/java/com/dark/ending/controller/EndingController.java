package com.dark.ending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dark.ending.service.EndingService;
import com.dark.entity.Order;

@RestController
public class EndingController {

    @Autowired
    private EndingService service;

    @PostMapping("/order/{orderId}")
    public Order finishOrder(@PathVariable String orderId, @RequestBody String manualMark) {
        return service.finishOrder(orderId, manualMark);
    }
}
