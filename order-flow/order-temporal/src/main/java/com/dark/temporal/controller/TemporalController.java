package com.dark.temporal.controller;

import com.dark.temporal.workflow.OrderWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TemporalController {

    private final WorkflowClient client;

    @Autowired
    public TemporalController(WorkflowClient client) {
        this.client = client;
    }

    @PostMapping("/order")
    public String runWorkflowSignal(@RequestBody List<String> productIds) {
        String workflowId = UUID.randomUUID().toString();

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("OrderTaskQueue")
                .setWorkflowId(workflowId)
                .build();

        OrderWorkflow workflow = client.newWorkflowStub(OrderWorkflow.class, options);

        // Start the workflow asynchronously
        WorkflowClient.start(() -> workflow.startOrder(productIds.get(0)));

        return "Order started with workflow ID: " + workflowId;
    }

    @PostMapping("/order/{workflowId}")
    public String runWorkflowSignal(@PathVariable String workflowId, @RequestBody String manualMark) {
        WorkflowStub workflowStub = client.newUntypedWorkflowStub(workflowId);
        workflowStub.signal("onManualTaskDone", manualMark);
        return "Changed successfully";
    }
}
