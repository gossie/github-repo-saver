package com.github.gossie.githubreposaver.user;

import com.github.gossie.githubreposaver.Favorite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String gitHubUsername;
    private long gitHubUserId;
    private List<Favorite> favoriteRepositories = new ArrayList<>();

    public User(String gitHubUsername) {
        this.gitHubUsername = gitHubUsername;
    }

    public void addFavorite(Favorite favorite) {
        favoriteRepositories.add(favorite);
    }

    public void removedFavorite(String repoName) {
        favoriteRepositories.removeIf(f -> Objects.equals(f.getRepositoryName(), repoName));
    }
}
