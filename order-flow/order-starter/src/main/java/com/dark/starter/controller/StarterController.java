package com.dark.starter.controller;

import com.dark.starter.service.StarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StarterController {

    private final StarterService service;

    @Autowired
    public StarterController(StarterService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public Long showMessage(@RequestBody List<String> productIds) {
        return service.createOrder(productIds);
    }

}
