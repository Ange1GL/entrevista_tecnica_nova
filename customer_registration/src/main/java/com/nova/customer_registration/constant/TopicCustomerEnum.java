package com.nova.customer_registration.constant;

public enum TopicCustomerEnum {

    ALTA_CUSTOMER("ALTA_CUSTOMER");


    private final String value;

    TopicCustomerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
