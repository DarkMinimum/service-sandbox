package com.dark.temporal.activity;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface UpdateOrderStatusActivity {

    void updateOrder(String orderId);
}
