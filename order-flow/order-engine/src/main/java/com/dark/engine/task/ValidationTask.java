package com.dark.engine.task;

import com.dark.engine.ProcessEnv;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Validation")
public class ValidationTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var processEnv = new ProcessEnv(execution);
        var product = processEnv.getProducts();
        var res = List.of("tea", "coffee", "cake", "pie", "apple").contains(product);
        processEnv.setValidationResult(res);
        System.out.println("validate");
    }
}
