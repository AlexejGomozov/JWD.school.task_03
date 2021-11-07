package com.javacourse.task3.entity;

public enum SmsType {
    sms,
    mms;

    @Override
    public String toString() {
        return this.name();
    }
}
