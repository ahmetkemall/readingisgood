package com.store.readingisgood.exception;

public class CustomerNotFoundException extends Exception {
    private static final long serialVersionUID = -5607956332541372630L;

    private static final String MESSAGE = "Customer not found with id :";

    public CustomerNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
