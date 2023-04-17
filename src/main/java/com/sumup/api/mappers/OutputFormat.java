package com.sumup.api.mappers;

public enum OutputFormat {
    SCRIPT("script"),
    JSON("json");

    private final String value;

    OutputFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
