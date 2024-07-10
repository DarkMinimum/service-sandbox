package com.dark.temporal.activity.impl;

import com.dark.temporal.activity.ValidationActivity;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ActivityImpl(taskQueues = "OrderTaskQueue")
public class ValidationActivityImpl implements ValidationActivity {

    @Value("#{'${products.validation.list}'.split(',')}")
    private List<String> allowedProductList;

    public boolean validate(String product) {
        return allowedProductList.contains(product);
    }

}
