package com.dark.temporal.activity.impl;

import com.dark.entity.Order;
import com.dark.model.OrderStatus;
import com.dark.temporal.activity.UpdateOrderStatusActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.temporal.spring.boot.ActivityImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@ActivityImpl(taskQueues = "OrderTaskQueue")
public class UpdateOrderStatusActivityImpl implements UpdateOrderStatusActivity {

    @Value("${proc.intermediate.url}")
    private String intermediateUrl;

    @Override
    public void updateOrder(String orderId) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(intermediateUrl + "/" + orderId))
                .POST(HttpRequest.BodyPublishers.ofString(StringUtils.EMPTY))
                .build();

        try {HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            var order = objectMapper.readValue(response.body(), Order.class);

            if (order.getStatus().equals(OrderStatus.CLOSED)) {
                throw new IllegalStateException(String.format("Order should have %s status", OrderStatus.CLOSED));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during sending-parsing req/res", e.getCause());
        }

    }
}
