package com.gla.demo.core.exception;

public class RecordNotFoundException extends Exception {

    private static final long serialVersionUID = 4662776607368428864L;

    public RecordNotFoundException(String message) {
        super(message);
    }
}
