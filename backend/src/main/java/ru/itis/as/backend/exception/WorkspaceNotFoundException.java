package ru.itis.as.backend.exception;

public class WorkspaceNotFoundException extends ResourseNotFoundException {
    public WorkspaceNotFoundException() {
        super("workspace");
    }
}
