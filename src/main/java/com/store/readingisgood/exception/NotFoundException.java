package com.store.readingisgood.exception;

public class NotFoundException extends Exception {
    private static final long serialVersionUID = -5607956332541372630L;

    private static final String MESSAGE = "Record is not found with id :";

    public NotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
