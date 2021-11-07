package com.javacourse.task3.builder;

public enum TariffXmlTag {
    TARIFFS,
    START_TARIFF,
    FAMILY_TARIFF,
    BUSINESS_TARIFF,
    UNLIMITED_TARIFF,
    OPERATOR_NAME,
    PAYROLL,
    YEAR,
    CONNECTION_FEE,
    CALLS,
    CALL_PRICES_ON_NET,
    CALL_PRICES_ON_ANOTHER_NETWORK,
    CALL_PRICES_TO_LANDLINE_PHONES,
    CALL_PRICE_FOR_INTERNATIONAL_CONNECTION,
    UNLIMITED_CALLS_TO_ANY_NETWORK,
    SMS,
    TARIFFICATION,
    FAVORITE_NUMBER,
    FAMILY_NUMBER;

    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';

@Override
public String toString() {
    return this.name().toLowerCase().replace(UNDERSCORE, HYPHEN);
}
}




