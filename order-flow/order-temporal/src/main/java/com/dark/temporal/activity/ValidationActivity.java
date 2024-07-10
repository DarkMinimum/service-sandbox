package com.dark.temporal.activity;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface ValidationActivity {

    boolean validate(String product);
}
