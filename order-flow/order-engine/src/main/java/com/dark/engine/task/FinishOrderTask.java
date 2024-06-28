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

@Component("FinishOrder")
public class FinishOrderTask implements JavaDelegate {

    @Value("${proc.ending.url}")
    private String endingUrl;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var processEnv = new ProcessEnv(execution);
        var orderId = processEnv.getOrderId();
        var manualMark = processEnv.getManualMark();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .uri(URI.create(endingUrl + "/" + orderId))
            .POST(HttpRequest.BodyPublishers.ofString(String.format("\"%s\"", manualMark)))
            .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
