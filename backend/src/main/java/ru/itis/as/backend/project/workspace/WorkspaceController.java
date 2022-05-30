package ru.itis.as.backend.project.workspace;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.as.backend.model.Workspace;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {
    private WorkspaceService workspaceService;

    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> getWorkspacesOfCurrentUser(Authentication authentication) {
        List<Workspace> workspaces = workspaceService.getWorkspacesByUserId(authentication);
        List<WorkspaceDto> workspaceDtos = workspaces.stream()
                .map(x -> modelMapper.map(x, WorkspaceDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(workspaceDtos);
    }

    @PostMapping
    public ResponseEntity<?> createWorkspace(@RequestBody WorkspaceCreateDto dto, Authentication authentication) {
        workspaceService.createWorkspace(dto, authentication);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullWorkspaceDto> getWorkspacesById(@PathVariable UUID id, Authentication authentication) {
        Workspace workspace = workspaceService.getWorkspaceById(id, authentication);
        FullWorkspaceDto workspaceDto = modelMapper.map(workspace, FullWorkspaceDto.class);

        return ResponseEntity.ok(workspaceDto);
    }

    @Autowired
    public void setWorkspaceService(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
