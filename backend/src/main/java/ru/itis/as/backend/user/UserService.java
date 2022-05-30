package ru.itis.as.backend.user;

import org.springframework.security.core.Authentication;
import ru.itis.as.backend.authentication.RegisterDto;
import ru.itis.as.backend.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User register(RegisterDto dto);

    User getUserById(UUID id);

    List<User> getAll();

    User getCurrentUser(Authentication authentication);
}
