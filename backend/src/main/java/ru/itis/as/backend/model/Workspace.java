package ru.itis.as.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Workspace {
    @Id
    private UUID id;

    @ManyToOne
    private User owner;

    private String name;

    @ManyToMany
    private List<User> users;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards;

    public Integer getUserCount() {
        return users.size();
    }

    public Integer getBoardCount() {
        return boards.size();
    }
}
