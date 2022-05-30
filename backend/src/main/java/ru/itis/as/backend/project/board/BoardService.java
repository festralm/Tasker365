package ru.itis.as.backend.project.board;

import org.springframework.security.core.Authentication;
import ru.itis.as.backend.model.Board;

import java.util.UUID;

public interface BoardService {
    Board create(BoardCreateDto dto, Authentication authentication);

    Board getById(UUID id, Authentication authentication);
}
