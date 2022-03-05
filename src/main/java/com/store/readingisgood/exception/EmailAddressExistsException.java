package com.store.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailAddressExistsException extends Exception {

    private static final long serialVersionUID = -4531742279895326496L;
    private static final String EMAIL_ALREADY_EXISTS = "Email is already exists";

    public EmailAddressExistsException() {
        super(EMAIL_ALREADY_EXISTS);
    }
}
