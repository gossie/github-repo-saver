package com.github.gossie.githubreposaver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubRepository {

    private String name;

    @JsonProperty("full_name")
    private String fullName;

}
