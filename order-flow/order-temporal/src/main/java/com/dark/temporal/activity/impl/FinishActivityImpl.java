package com.dark.temporal.activity.impl;

import com.dark.temporal.activity.FinishOrderActivity;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@ActivityImpl(taskQueues = "OrderTaskQueue")
public class FinishActivityImpl implements FinishOrderActivity {

    @Value("${proc.ending.url}")
    private String endingUrl;

    @Override
    public void finishOrder(String orderId, String manualMark) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(endingUrl + "/" + orderId))
                    .POST(HttpRequest.BodyPublishers.ofString(String.format("\"%s\"", manualMark)))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Error during finish order.", e.getCause());
        }
    }
}
