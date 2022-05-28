package ru.itis.as.backend.project.workspace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.as.backend.exception.UserNotFoundException;
import ru.itis.as.backend.exception.WorkspaceNotFoundException;
import ru.itis.as.backend.model.User;
import ru.itis.as.backend.model.Workspace;
import ru.itis.as.backend.security.UserPrincipal;
import ru.itis.as.backend.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    private WorkspaceRepository workspaceRepository;

    private UserRepository userRepository;

    @Override
    public List<Workspace> getWorkspacesByUserId(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        return workspaceRepository.findAllByOwnerId(principal.getId());
    }

    @Override
    public Workspace createWorkspace(WorkspaceCreateDto dto, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        User owner = userRepository.findById(principal.getId())
                .orElseThrow(UserNotFoundException::new);

        Workspace workspace = Workspace.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .owner(owner)
                .users(new ArrayList<>())
                .build();

        dto.setUsers(dto.getUsers().stream().distinct().collect(Collectors.toList()));

        dto.getUsers().remove(principal.getId());

        for (UUID id : dto.getUsers()) {
            User user = userRepository.findById(id)
                    .orElseThrow(UserNotFoundException::new);

            workspace.getUsers().add(user);
        }

        return workspaceRepository.save(workspace);
    }

    @Override
    public Workspace getWorkspaceById(UUID id, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(WorkspaceNotFoundException::new);

        if (!workspace.getOwner().getId().equals(principal.getId())
                && workspace.getUsers().stream().map(User::getId)
                .noneMatch(x -> x.equals(principal.getId()))) {
            throw new AccessDeniedException("access_denied");
        }

        return workspace;
    }

    @Autowired
    public void setWorkspaceRepository(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
