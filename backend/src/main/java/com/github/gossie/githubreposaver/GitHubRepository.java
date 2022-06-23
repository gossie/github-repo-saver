package com.github.gossie.githubreposaver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubRepository {

    private Long id;
    private String name;

    public GitHubRepository(String name) {
        this.name = name;
    }

}
