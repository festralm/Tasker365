package ru.itis.as.backend.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private Object data;
    private Integer errorCode;
    private String message;
}
