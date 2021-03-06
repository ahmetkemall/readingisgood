package com.getir.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String UNEXPECTED_ERROR_TEXT = "Unexpected error";

    @ExceptionHandler(EmailAddressExistsException.class)
    public ResponseEntity<ErrorDetails> emailAddressExistsException(EmailAddressExistsException ex) {
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .errorCode(1)
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDetails> emailAddressExistsException(BookNotFoundException ex) {
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .errorCode(2)
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDetails> customerNotExistsException(CustomerNotFoundException ex) {
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .errorCode(3)
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoStockException.class)
    public ResponseEntity<ErrorDetails> noStockExceptionException(NoStockException ex) {
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .errorCode(3)
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> notFoundException(NotFoundException ex) {
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .errorCode(6)
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorDetails> invalidRequestException(InvalidRequestException ex) {
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .errorCode(8)
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex) {
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .errorCode(99)
                .timestamp(new Date())
                .message(UNEXPECTED_ERROR_TEXT)
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
