package com.github.gossie.githubreposaver;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String username;
    private List<Long> favoriteRepositories;

    public User(String username) {
        this.username = username;
    }

}
