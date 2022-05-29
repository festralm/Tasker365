package ru.itis.as.backend.project.task;

import org.springframework.security.core.Authentication;
import ru.itis.as.backend.model.Priority;
import ru.itis.as.backend.model.Status;
import ru.itis.as.backend.model.Task;

import java.util.List;

public interface TaskService {
    Task create(TaskCreateDto dto, Authentication authentication);

    List<Status> getStatuses();

    List<Priority> getPriorities();
}
