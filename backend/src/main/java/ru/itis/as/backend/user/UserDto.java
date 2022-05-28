package ru.itis.as.backend.user;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String avatarUrl;
}
