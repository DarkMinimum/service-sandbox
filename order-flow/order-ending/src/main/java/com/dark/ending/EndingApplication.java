package com.dark.ending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.dark.model")
@SpringBootApplication
public class EndingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndingApplication.class, args);
    }

}
