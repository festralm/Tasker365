package ru.itis.as.backend.project.board;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BoardDto {
    private UUID id;
    private String name;
    private List<BoardTaskDto> tasks;
}
