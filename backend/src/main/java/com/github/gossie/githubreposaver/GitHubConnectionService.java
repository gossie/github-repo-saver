package com.github.gossie.githubreposaver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GitHubConnectionService {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private final RestTemplate restTemplate;

    public GitHubRepository[] findByUsername(String username) {
        return restTemplate.getForObject(GITHUB_BASE_URL + "users/" + username + "/repos", GitHubRepository[].class);
    }

    public boolean userExists(String username) {
        try {
            restTemplate.getForObject(GITHUB_BASE_URL + "users/" + username, Void.class);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean repoExists(String fullName) {
        try {
            restTemplate.getForObject(GITHUB_BASE_URL + "repos/" + fullName, Void.class);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
