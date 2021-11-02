package com.javacourse.task3.entity;

public enum CallsType {
    CALL_PRICE_FOR_INTERNATIONAL_CONNECTION,
    CALL_PRICES_ON_NET,
    UNLIMITED_CALLS_TO_ANY_NETWORK,
    CALL_PRICES_ON_ANOTHER_NETWORK,
    CALL_PRICES_T0_LANDLINE_PHONES;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
