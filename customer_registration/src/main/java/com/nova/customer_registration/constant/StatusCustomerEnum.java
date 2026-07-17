package com.nova.customer_registration.constant;

public enum StatusCustomerEnum {

    ALTA("ALTA"),
    ACTIVO("ACTIVO");

    private final String value;

    StatusCustomerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
