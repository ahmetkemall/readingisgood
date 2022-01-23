package com.getir.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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
