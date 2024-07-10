package com.dark.temporal.activity;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface CreateOrderActivity {

    String createOrder(String product);

}
