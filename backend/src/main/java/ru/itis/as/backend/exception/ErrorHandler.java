package ru.itis.as.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourseNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleResourseNotFoundException(RuntimeException e) {
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(CustomAuthenticationException.class)
    public final ResponseEntity<ErrorResponse> handleCustomAuthenticationException(RuntimeException e) {
        return buildResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(EmailIsTakenException.class)
    public final ResponseEntity<ErrorResponse> handleEmailIsTakenException(RuntimeException e) {
        return buildResponse(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorResponse> handleAccessDeniedException(RuntimeException e) {
        return buildResponse(HttpStatus.FORBIDDEN, "access_denied");
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAnyOtherException(Exception e) {
        log.error(e.getMessage(), e);

        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка на сервере");
    }

    private static ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(status.value())
                .message(message)
                .build(), status);
    }
}
