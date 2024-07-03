package com.dark.engine.task;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dark.engine.ProcessEnv;

@Component("CreateOrder")
public class CreateOrderTask implements JavaDelegate {

    @Value("${proc.starter.url}")
    private String starterUrl;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var processEnv = new ProcessEnv(execution);
        var product = processEnv.getProducts();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(starterUrl))
            .POST(HttpRequest.BodyPublishers.ofString(String.format("[\"%s\"]", product)))
            .header("Content-Type", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        processEnv.setOrderId(response.body());
    }
}
