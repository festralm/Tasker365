package ru.itis.as.backend.exception;

public class PriorityNotFoundException extends ResourseNotFoundException {
    public PriorityNotFoundException() {
        super("priority");
    }
}
