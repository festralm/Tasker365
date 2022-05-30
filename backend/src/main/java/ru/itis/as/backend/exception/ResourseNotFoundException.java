package ru.itis.as.backend.exception;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String resourseName) {
        super(resourseName + "_not_found");
    }
}
