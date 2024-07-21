package com.snow.catalogo.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleBadRequestException() {
        BadRequestException badRequestException = new BadRequestException("Invalid request");

        ResponseEntity<ResponseErrorDetails> responseEntity = exceptionHandler.handleBadRequestException(badRequestException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ResponseErrorDetails errorDetails = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorDetails.getStatus());
        assertEquals("Invalid request", errorDetails.getError());
        assertTrue(errorDetails.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void testHandleRuntimeException() {
        RuntimeException runtimeException = new RuntimeException("Unexpected error");

        ResponseEntity<ResponseErrorDetails> responseEntity = exceptionHandler.handleRuntimeException(runtimeException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ResponseErrorDetails errorDetails = responseEntity.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorDetails.getStatus());
        assertTrue(errorDetails.getError().startsWith("Ocorreu um erro inesperado: "));
        assertTrue(errorDetails.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}
