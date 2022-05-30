package ru.itis.as.backend.project.workspace;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceBoardDto {
    private UUID id;
    private String name;
    private String backgroundLink;
    private Integer taskCount;
}
