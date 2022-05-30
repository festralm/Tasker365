package ru.itis.as.backend.exception;

public class EmailIsTakenException extends RuntimeException {
    public EmailIsTakenException() {
        super("email_taken");
    }
}
