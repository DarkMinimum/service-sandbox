package com.dark.engine.task;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dark.engine.ProcessEnv;
import com.dark.entity.Order;
import com.dark.model.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("UpdateOrderStatus")
public class UpdateOrderStatusTask implements JavaDelegate {

    @Value("${proc.intermediate.url}")
    private String intermediateUrl;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var processEnv = new ProcessEnv(execution);
        var orderId = processEnv.getOrderId();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(intermediateUrl + "/" + orderId))
            .POST(HttpRequest.BodyPublishers.ofString(StringUtils.EMPTY))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        var order = objectMapper.readValue(response.body(), Order.class);

        if (order.getStatus().equals(OrderStatus.CLOSED)) {
            throw new IllegalStateException(String.format("Order should have %s status", OrderStatus.CLOSED));
        }
    }
}
