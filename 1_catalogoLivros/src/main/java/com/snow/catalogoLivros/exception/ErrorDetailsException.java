package com.snow.catalogoLivros.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetailsException {
    private LocalDateTime timestamp;
    private int status;
    private String error;
}
