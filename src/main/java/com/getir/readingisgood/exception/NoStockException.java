package com.getir.readingisgood.exception;

public class NoStockException extends Exception {
    private static final long serialVersionUID = -5607956332541372630L;

    private static final String MESSAGE = "No enough stock left for book id :";

    public NoStockException(Long id) {
        super(MESSAGE + id);
    }
}
