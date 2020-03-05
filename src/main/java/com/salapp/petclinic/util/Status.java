package com.salapp.petclinic.util;

/**
 * Contain the status code
 */
public enum Status {
    ACTIVE(0), INACTIVE(1);

    Status(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return this.code;
    }
}
