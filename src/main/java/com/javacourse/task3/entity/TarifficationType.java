package com.javacourse.task3.entity;

public enum TarifficationType {
    sec12,
    sec60;

    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    @Override
    public String toString() {
        return this.name().toLowerCase().replace(UNDERSCORE, HYPHEN);
    }
}
