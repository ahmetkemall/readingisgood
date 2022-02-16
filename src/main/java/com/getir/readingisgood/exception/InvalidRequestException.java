package com.getir.readingisgood.exception;

public class InvalidRequestException extends Exception {
    private static final long serialVersionUID = 3193898835686116991L;

    public InvalidRequestException(String message) {
        super(message);
    }
}
