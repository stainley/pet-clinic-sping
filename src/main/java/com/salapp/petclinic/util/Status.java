package com.salapp.petclinic.util;

import com.sun.xml.internal.ws.developer.Serialization;

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
