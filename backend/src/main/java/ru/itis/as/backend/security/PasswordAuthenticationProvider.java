package ru.itis.as.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.as.backend.exception.CustomAuthenticationException;
import ru.itis.as.backend.model.User;
import ru.itis.as.backend.user.UserRepository;

import java.util.Optional;

@Component
public class PasswordAuthenticationProvider implements AuthenticationProvider {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        Optional<User> userOpt = userRepository.findByEmail(authentication.getName());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
                UserDetails userDetails = new UserPrincipal(user);
                return new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities());
            }
        }

        throw new CustomAuthenticationException("invalid_data");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
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
