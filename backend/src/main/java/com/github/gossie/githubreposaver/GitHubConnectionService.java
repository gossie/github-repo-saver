package com.github.gossie.githubreposaver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubConnectionService {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private final RestTemplate restTemplate;

    public GitHubRepository[] findByUsername(String username) {
        return restTemplate.getForObject(GITHUB_BASE_URL + "users/" + username + "/repos", GitHubRepository[].class);
    }

}
