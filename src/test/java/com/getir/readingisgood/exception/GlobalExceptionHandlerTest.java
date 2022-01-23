package com.getir.readingisgood.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Test
    public void shouldThrowEmailAddressExistsException() {
        EmailAddressExistsException ex = new EmailAddressExistsException();
        ResponseEntity<?> response = handler.emailAddressExistsException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is already exists", ((ErrorDetails) Objects.requireNonNull(response.getBody())).getMessage());
    }

    @Test
    public void shouldThrowGlobalExceptionHandler() {
        Exception ex = new Exception();
        ResponseEntity<?> response = handler.globalExceptionHandler(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error", ((ErrorDetails) Objects.requireNonNull(response.getBody())).getMessage());
    }

}