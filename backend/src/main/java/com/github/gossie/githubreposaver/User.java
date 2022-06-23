package com.github.gossie.githubreposaver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String username;
    private List<Favorite> favoriteRepositories = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public void addFavorite(Favorite favorite) {
        favoriteRepositories.add(favorite);
    }
}
