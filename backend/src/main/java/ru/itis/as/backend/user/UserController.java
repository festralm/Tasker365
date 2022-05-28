package ru.itis.as.backend.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.as.backend.authentication.RegisterDto;
import ru.itis.as.backend.model.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        userService.register(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        User user = userService.getCurrentUser(authentication);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<FullUserDto>> getAllUsers() {
        List<User> users = userService.getAll();
        List<FullUserDto> userDtos = users.stream()
                .map(x -> modelMapper.map(x, FullUserDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDtos);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
