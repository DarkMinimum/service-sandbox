package com.dark.temporal.activity.impl;

import com.dark.temporal.activity.CreateOrderActivity;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@ActivityImpl(taskQueues = "OrderTaskQueue")
public class CreateOrderActivityImpl implements CreateOrderActivity {

    @Value("${proc.starter.url}")
    private String starterUrl;

    @Override
    public String createOrder(String product) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(starterUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(String.format("[\"%s\"]", product)))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("Order cannot be created due to exception", e.getCause());
        }

    }
}
