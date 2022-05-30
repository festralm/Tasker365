package ru.itis.as.backend.authentication;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String password;
    private String name;
}
