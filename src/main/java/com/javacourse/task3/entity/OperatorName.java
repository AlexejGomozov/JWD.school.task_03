package com.javacourse.task3.entity;

public enum OperatorName {
    A1,
    MTS,
    LIFE;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
