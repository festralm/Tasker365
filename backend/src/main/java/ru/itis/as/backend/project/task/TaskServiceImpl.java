package ru.itis.as.backend.project.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.as.backend.exception.BoardNotFoundException;
import ru.itis.as.backend.exception.PriorityNotFoundException;
import ru.itis.as.backend.exception.StatusNotFoundException;
import ru.itis.as.backend.exception.WorkspaceNotFoundException;
import ru.itis.as.backend.model.*;
import ru.itis.as.backend.project.board.BoardRepository;
import ru.itis.as.backend.security.UserPrincipal;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    private BoardRepository boardRepository;

    private PriorityRepository priorityRepository;

    private StatusRepository statusRepository;

    @Override
    public Task create(TaskCreateDto dto, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        Board board = boardRepository.findById(dto.getBoardId())
                .orElseThrow(BoardNotFoundException::new);

        if (!board.getWorkspace().getOwner().getId().equals(principal.getId())
                && board.getWorkspace().getUsers().stream().map(User::getId)
                .noneMatch(x -> x.equals(principal.getId()))) {
            throw new AccessDeniedException("access_denied");
        }

        Status status = statusRepository.findByName(dto.getStatus())
                .orElseThrow(StatusNotFoundException::new);



        Priority priority = priorityRepository.findByName(dto.getPriority())
                .orElseThrow(PriorityNotFoundException::new);



        Task task = Task.builder()
                .id(UUID.randomUUID())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(status)
                .priority(priority)
                .board(board)
                .build();

        return taskRepository.save(task);
    }

    @Override
    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public List<Priority> getPriorities() {
        return priorityRepository.findAll();
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired
    public void setPriorityRepository(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }
}
