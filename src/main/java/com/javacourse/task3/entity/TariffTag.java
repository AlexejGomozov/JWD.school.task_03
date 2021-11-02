package com.javacourse.task3.entity;

import java.util.Locale;

public enum TariffTag {
    TARIFF("tariff"),
    TARIFF_ID("tariff_id"),
    START_TARIFF("start-tariff"),
    FAMILY_TARIFF("family-tariff"),
    BUSINESS_TARIFF("business-tariff"),
    UNLIMITED_TARIFF("unlimited-tariff"),
    OPERATOR_NAME("operator-name"),
    PAYROLL("payroll"),
    YEAR("year"),
    CALLS("calls"),
    CALL_PRICE_FOR_INTERNATIONAL_CONNECTION("call-price-for-international-connection"),
//    CALL_PRICES_ON_NET("call-prices-on-net"),
    UNLIMITED_CALLS_TO_ANY_NETWORK("unlimited-calls-to-any-network"),
//    CALL_PRICES_ON_ANOTHER_NETWORK("call-prices-on-another-network"),
//    CALL_PRICES_T0_LANDLINE_PHONES("call-prices-to-landline-phones"),
      TARIFFICATION("tariffication"),
//    TARIFFICATION_60_SEC("tariffication60Sec"),
    SMS("sms"),
//    SHORT_SMS("short-sms"),
//    MULTIMEDIA_MMS("multimedia-mms"),
    FAVORITE_NUMBER("favorite-number"),
    FAMILY_NUMBER("family-number"),
    CONNECTION_FEE("connection-fee");

    String title;

    TariffTag(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    @Override
    public  String toString(){
        String result = this.name();
        result = result.toLowerCase(Locale.ROOT);
        result = result.replace(UNDERSCORE, HYPHEN);
        return result;
    }
}




// String id, String operatorName, double payroll, YearMonth year, double calls, String sms, int favoriteNumber, double connectionFee ){
//