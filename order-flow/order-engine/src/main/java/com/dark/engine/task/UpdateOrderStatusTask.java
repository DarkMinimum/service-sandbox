package com.dark.engine.task;

import com.dark.engine.ProcessEnv;
import com.dark.entity.Order;
import com.dark.model.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.UriBuilder;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component("UpdateOrderStatus")
public class UpdateOrderStatusTask implements JavaDelegate {

    @Value("${proc.intermediate.url}")
    private String intermediateUrl;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var processEnv = new ProcessEnv(execution);
        var orderId = processEnv.getOrderId();

        URI uriWithParams = UriBuilder.fromUri(intermediateUrl)
                .queryParam("orderId", orderId)
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriWithParams)
                .POST(HttpRequest.BodyPublishers.ofString(StringUtils.EMPTY))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        var order = objectMapper.readValue(response.body(), Order.class);

        if (order.getStatus().equals(OrderStatus.CLOSED)) {
            throw new IllegalStateException(String.format("Order should have %s status", OrderStatus.CLOSED));
        }
        System.out.println("update");
    }
}