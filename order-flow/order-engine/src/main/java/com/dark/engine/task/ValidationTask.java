package com.dark.engine.task;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dark.engine.ProcessEnv;

@Component("Validation")
public class ValidationTask implements JavaDelegate {

    @Value("#{'${products.validation.list}'.split(',')}")
    private List<String> allowedProductList;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var processEnv = new ProcessEnv(execution);
        var product = processEnv.getProducts();
        var res = allowedProductList.contains(product);
        processEnv.setValidationResult(res);
    }
}
