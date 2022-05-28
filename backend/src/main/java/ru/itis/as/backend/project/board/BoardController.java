package ru.itis.as.backend.project.board;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.as.backend.exception.UserNotFoundException;
import ru.itis.as.backend.model.Board;
import ru.itis.as.backend.model.User;
import ru.itis.as.backend.model.Workspace;
import ru.itis.as.backend.security.UserPrincipal;
import ru.itis.as.backend.user.UserRepository;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    private BoardService boardService;

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardCreateDto dto, Authentication authentication) {
        boardService.create(dto, authentication);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getById(@PathVariable UUID id, Authentication authentication) {
        Board board = boardService.getById(id, authentication);
        BoardDto boardDto = modelMapper.map(board, BoardDto.class);

        return ResponseEntity.ok(boardDto);
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
