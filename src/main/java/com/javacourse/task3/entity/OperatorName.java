package com.javacourse.task3.entity;

public enum OperatorName {

    a1,
    life,
    mts;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
