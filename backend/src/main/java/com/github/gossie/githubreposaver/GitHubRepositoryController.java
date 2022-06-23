package com.github.gossie.githubreposaver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/githubrepositories")
@RequiredArgsConstructor
public class GitHubRepositoryController {

    private final GitHubConnectionService gitHubConnectionService;

    @GetMapping
    public GitHubRepository[] getRepositories(@RequestParam String gitHubUsername) {
        return gitHubConnectionService.findByUsername(gitHubUsername);
    }

}
