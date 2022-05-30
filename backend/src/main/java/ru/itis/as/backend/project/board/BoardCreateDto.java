package ru.itis.as.backend.project.board;

import lombok.Data;

import java.util.UUID;

@Data
public class BoardCreateDto {
    private String name;
    private UUID workspaceId;
}
