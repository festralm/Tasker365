package ru.itis.as.backend.project.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.as.backend.exception.BoardNotFoundException;
import ru.itis.as.backend.exception.WorkspaceNotFoundException;
import ru.itis.as.backend.model.Board;
import ru.itis.as.backend.model.User;
import ru.itis.as.backend.model.Workspace;
import ru.itis.as.backend.project.workspace.WorkspaceRepository;
import ru.itis.as.backend.security.UserPrincipal;

import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;

    private WorkspaceRepository workspaceRepository;

    @Override
    public Board create(BoardCreateDto dto, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        Workspace workspace = workspaceRepository.findById(dto.getWorkspaceId())
                .orElseThrow(WorkspaceNotFoundException::new);

        if (!workspace.getOwner().getId().equals(principal.getId())
                && workspace.getUsers().stream().map(User::getId)
                .noneMatch(x -> x.equals(principal.getId()))) {
            throw new AccessDeniedException("access_denied");
        }

        Board board = Board.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .workspace(workspace)
                .backgroundLink("default_background.jpeg")
                .build();

        return boardRepository.save(board);
    }

    @Override
    public Board getById(UUID id, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFoundException::new);

        if (!board.getWorkspace().getOwner().getId().equals(principal.getId())
                && board.getWorkspace().getUsers().stream().map(User::getId)
                .noneMatch(x -> x.equals(principal.getId()))) {
            throw new AccessDeniedException("access_denied");
        }

        return board;
    }

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired
    public void setWorkspaceRepository(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }
}
