package com.dark.starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.dark.model.Order;

@RestController
public class StarterController {

    @PostMapping("/sayHello/")
    public Order showMessage(@RequestBody Order message) {
        return message;
    }

    @GetMapping("/getHello/")
    public Order showMessage() {
        var message = new Order("", "", List.of());
        return message;
    }

}
