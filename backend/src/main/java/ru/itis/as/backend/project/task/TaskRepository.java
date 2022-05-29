package ru.itis.as.backend.project.task;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.as.backend.model.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
