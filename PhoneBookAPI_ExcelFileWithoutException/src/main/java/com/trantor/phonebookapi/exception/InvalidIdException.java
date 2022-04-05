package com.trantor.phonebookapi.exception;

public class InvalidIdException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidIdException() {
    }

    public InvalidIdException(String message) {

        super(message);
    }
}
