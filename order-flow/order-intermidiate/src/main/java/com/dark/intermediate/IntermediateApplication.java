package com.dark.intermediate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.dark.model")
@SpringBootApplication
public class IntermediateApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntermediateApplication.class, args);
    }

}
