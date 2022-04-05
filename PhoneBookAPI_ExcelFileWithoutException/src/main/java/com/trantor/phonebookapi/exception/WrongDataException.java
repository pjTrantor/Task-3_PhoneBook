package com.trantor.phonebookapi.exception;

public class WrongDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WrongDataException() {
    }

    public WrongDataException(String message) {
        super(message);
    }
}
