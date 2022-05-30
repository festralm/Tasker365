package ru.itis.as.backend.project.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.as.backend.model.Priority;
import ru.itis.as.backend.model.Status;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, String> {
    Optional<Priority> findByName(String name);
}
