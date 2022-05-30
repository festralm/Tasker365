package ru.itis.as.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.as.backend.authentication.RegisterDto;
import ru.itis.as.backend.model.User;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterDto dto) {
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
        return userRepository.getReferenceById(id);
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
