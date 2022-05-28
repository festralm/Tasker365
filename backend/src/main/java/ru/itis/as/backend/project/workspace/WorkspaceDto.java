package ru.itis.as.backend.project.workspace;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceDto {
    private UUID id;
    private String name;
    private Integer boardCount;
    private Integer userCount;
}
