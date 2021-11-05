package com.javacourse.task3.entity;

public enum SmsType {
    sms,//SHORT_SMS,
    mms;//MULTIMEDIA_MMS;

    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';

    @Override
    public String toString() {
        return name().toLowerCase().replace(UNDERSCORE, HYPHEN);
    }
}
