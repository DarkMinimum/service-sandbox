package com.dark.engine;

import org.camunda.bpm.engine.delegate.VariableScope;

public class ProcessEnv {

    public static final String IS_VALIDATATED = "isValidated";
    public static final String PRODUCT_LIST = "productList";
    public static final String ORDER_ID = "orderId";

    private VariableScope variableScope;

    public ProcessEnv(VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public String getProducts() {
        return variableScope.getVariable(PRODUCT_LIST).toString();
    }

    public void setValidationResult(boolean validationResult) {
        variableScope.setVariable(IS_VALIDATATED, validationResult);
    }

    public String getOrderId() {
        return variableScope.getVariable(ORDER_ID).toString();
    }

    public void setOrderId(String id) {
        variableScope.setVariable(ORDER_ID, id);
    }
}
