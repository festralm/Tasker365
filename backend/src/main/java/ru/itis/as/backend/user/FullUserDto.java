package ru.itis.as.backend.user;

import lombok.Data;

import java.util.UUID;

@Data
public class FullUserDto {
    private UUID id;
    private String name;
    private String email;
    private String avatarUrl;
}
