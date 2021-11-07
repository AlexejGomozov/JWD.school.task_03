package com.javacourse.task3.entity;

public enum TarifficationType {
    sec12,
    sec60;

    @Override
    public String toString() {
        return this.name();
    }
}
