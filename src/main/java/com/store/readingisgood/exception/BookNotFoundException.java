package com.store.readingisgood.exception;

public class BookNotFoundException extends Exception {
    private static final long serialVersionUID = -5607956332541372630L;

    private static final String MESSAGE = "Book not found with id :";

    public BookNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
