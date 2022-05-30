package ru.itis.as.backend.project.workspace;

import org.springframework.security.core.Authentication;
import ru.itis.as.backend.model.Workspace;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    List<Workspace> getWorkspacesByUserId(Authentication authentication);

    Workspace createWorkspace(WorkspaceCreateDto dto, Authentication authentication);

    Workspace getWorkspaceById(UUID id, Authentication authentication);
}
