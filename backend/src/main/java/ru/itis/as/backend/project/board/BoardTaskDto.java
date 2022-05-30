package ru.itis.as.backend.project.board;

import lombok.Data;

import java.util.UUID;

@Data
public class BoardTaskDto {
    private UUID id;
    private String title;
    private String description;
    private String status;
}
