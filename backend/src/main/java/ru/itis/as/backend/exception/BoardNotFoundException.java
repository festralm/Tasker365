package ru.itis.as.backend.exception;

public class BoardNotFoundException extends ResourseNotFoundException {
    public BoardNotFoundException() {
        super("board");
    }
}
