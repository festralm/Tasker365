package ru.itis.as.backend.project.workspace;

import lombok.Data;
import ru.itis.as.backend.user.FullUserDto;

import java.util.List;
import java.util.UUID;

@Data
public class FullWorkspaceDto {
    private UUID id;
    private String name;
    private List<FullUserDto> users;
    private List<WorkspaceBoardDto> boards;
}
