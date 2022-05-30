package ru.itis.as.backend.project.task;

import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.as.backend.model.Priority;
import ru.itis.as.backend.model.Status;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskCreateDto dto, Authentication authentication) {
        taskService.create(dto, authentication);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<String>> getStatuses() {
        return ResponseEntity.ok(taskService.getStatuses().stream().map(Status::getName).collect(Collectors.toList()));
    }

    @GetMapping("/priorities")
    public ResponseEntity<List<String>> getPriorities() {
        return ResponseEntity.ok(taskService.getPriorities().stream().map(Priority::getName).collect(Collectors.toList()));
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
