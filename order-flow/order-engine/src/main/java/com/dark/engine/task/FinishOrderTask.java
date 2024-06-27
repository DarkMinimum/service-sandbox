package com.dark.engine.task;

import com.dark.engine.ProcessEnv;
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

@Component("FinishOrder")
public class FinishOrderTask implements JavaDelegate {

    @Value("${proc.ending.url}")
    private String endingUrl;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var processEnv = new ProcessEnv(execution);
        var orderId = processEnv.getOrderId();

        URI uriWithParams = UriBuilder.fromUri(endingUrl)
                .queryParam("orderId", orderId)
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriWithParams)
                .POST(HttpRequest.BodyPublishers.ofString(StringUtils.EMPTY))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("finish");

    }
}
