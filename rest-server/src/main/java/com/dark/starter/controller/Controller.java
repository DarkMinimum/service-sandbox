package com.dark.starter.controller;

import com.dark.starter.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/sayHello/")
    public Message showMessage(@RequestBody Message message) {
        return message;
    }

    @GetMapping("/getHello/")
    public Message showMessage() {
        var message = new Message();
        message.setParam("hello bro");
        return message;
    }

}
