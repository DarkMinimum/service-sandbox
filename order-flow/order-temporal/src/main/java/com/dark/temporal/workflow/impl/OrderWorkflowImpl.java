package com.dark.temporal.workflow.impl;

import com.dark.temporal.activity.CreateOrderActivity;
import com.dark.temporal.activity.FinishOrderActivity;
import com.dark.temporal.activity.UpdateOrderStatusActivity;
import com.dark.temporal.activity.ValidationActivity;
import com.dark.temporal.workflow.OrderWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.time.Duration;

@WorkflowImpl(taskQueues = "OrderTaskQueue")
public class OrderWorkflowImpl implements OrderWorkflow {
    private final Logger workflowLogger = Workflow.getLogger(OrderWorkflowImpl.class);
    private String manualMark = StringUtils.EMPTY;

    private boolean wasProcessedManually = false;
    private ValidationActivity validationActivity =
            Workflow.newActivityStub(
                    ValidationActivity.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

    private CreateOrderActivity createOrderActivity =
            Workflow.newActivityStub(
                    CreateOrderActivity.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

    private UpdateOrderStatusActivity updateOrderStatusActivity =
            Workflow.newActivityStub(
                    UpdateOrderStatusActivity.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

    private FinishOrderActivity finishActivity =
            Workflow.newActivityStub(
                    FinishOrderActivity.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

    @Override
    public void startOrder(String product) {
        if (!validationActivity.validate(product)) {
            throw new IllegalArgumentException("Order contains wrong products");
        }

        var orderId = createOrderActivity.createOrder(product);
        Workflow.sleep(Duration.ofSeconds(5L));
        updateOrderStatusActivity.updateOrder(orderId);

        // User manual task to be done
        // Waiting for the signal or timeout
        if (Workflow.await(Duration.ofMinutes(5), () -> wasProcessedManually)) {
            // Proceed with further processing
            finishActivity.finishOrder(orderId, manualMark);
        } else {
            // Handle timeout
            throw new RuntimeException("Timeout: was not processed manually within 5 minutes");
        }
    }

    @Override
    public void onManualTaskDone(String manualMark) {
        this.manualMark = manualMark;
        wasProcessedManually = true;
    }
}
