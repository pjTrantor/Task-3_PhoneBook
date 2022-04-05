package com.trantor.phonebookapi.exception;

public class WrongURLException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WrongURLException() {
    }

    public WrongURLException(String message) {
        super(message);
    }
}
