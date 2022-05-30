package ru.itis.as.backend.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itis.as.backend.authentication.RegisterDto;
import ru.itis.as.backend.model.User;
import ru.itis.as.backend.security.UserPrincipal;

import java.security.Principal;

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
    public ResponseEntity<UserDto> getCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUserById(principal.getId());
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok(userDto);
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
