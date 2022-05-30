package ru.itis.as.backend.exception;

public class StatusNotFoundException extends ResourseNotFoundException {
    public StatusNotFoundException() {
        super("status");
    }
}
