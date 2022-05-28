package ru.itis.as.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.as.backend.authentication.RegisterDto;
import ru.itis.as.backend.exception.EmailIsTakenException;
import ru.itis.as.backend.exception.ResourseNotFoundException;
import ru.itis.as.backend.exception.UserNotFoundException;
import ru.itis.as.backend.model.User;
import ru.itis.as.backend.security.UserPrincipal;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailIsTakenException();
        };

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(dto.getEmail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .avatarUrl("default_avatar.jpeg")
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getCurrentUser(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        return getUserById(principal.getId());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
