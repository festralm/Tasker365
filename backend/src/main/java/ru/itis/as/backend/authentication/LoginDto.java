package ru.itis.as.backend.authentication;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
