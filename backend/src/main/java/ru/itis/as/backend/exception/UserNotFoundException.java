package ru.itis.as.backend.exception;

public class UserNotFoundException extends ResourseNotFoundException {
    public UserNotFoundException() {
        super("user");
    }
}
