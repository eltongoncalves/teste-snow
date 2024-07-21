package com.snow.catalogo.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseErrorDetails {
    private LocalDateTime timestamp;
    private int status;
    private String error;
}
