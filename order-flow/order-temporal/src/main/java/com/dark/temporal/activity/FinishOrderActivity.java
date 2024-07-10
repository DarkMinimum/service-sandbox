package com.dark.temporal.activity;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface FinishOrderActivity {

    void finishOrder(String orderId, String manualMark);
}
