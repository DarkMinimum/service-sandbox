package com.dark.intermediate.controller;

import com.dark.entity.Order;
import com.dark.intermediate.service.IntermediateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntermediateController {

    @Autowired
    private IntermediateService service;

    @PostMapping("/order/{orderId}")
    public Order shipOrder(@PathVariable String orderId) {
        return service.updateOrderStatus(orderId);
    }

}
