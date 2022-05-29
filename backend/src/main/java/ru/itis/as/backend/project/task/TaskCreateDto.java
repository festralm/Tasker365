package ru.itis.as.backend.project.task;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskCreateDto {
    private String title;
    private String description;
    private String status;
    private String priority;
    private UUID boardId;
}
