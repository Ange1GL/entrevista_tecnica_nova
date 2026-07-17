package com.nova.customer_update.constant;

public enum TopicCustomerEnum {

    ALTA_CUSTOMER("alta-customer"),
    CUSTOMER_VALIDATED("customer-validated");


    private final String value;

    TopicCustomerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
