package ru.itis.as.backend.project.workspace;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class WorkspaceCreateDto {
    private String name;
    private List<UUID> users;
}
