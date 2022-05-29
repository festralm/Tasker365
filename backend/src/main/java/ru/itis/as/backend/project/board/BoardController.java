package ru.itis.as.backend.project.board;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.as.backend.model.Board;
import ru.itis.as.backend.model.Task;

import javax.annotation.PostConstruct;
import java.util.UUID;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    private BoardService boardService;

    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        TypeMap<Task, BoardTaskDto> boardTaskMapper = modelMapper.createTypeMap(Task.class, BoardTaskDto.class);
        boardTaskMapper.addMappings(mapper -> mapper.map(src -> src.getStatus().getName(), BoardTaskDto::setStatus));
    }

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
