package com.github.gossie.githubreposaver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GitHubConnectionServiceTest {

    @Test
    void shouldRetrieveRepositories() {
        var restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForObject("https://api.github.com/users/anyUser/repos", GitHubRepository[].class))
                .thenReturn(new GitHubRepository[]{new GitHubRepository("Repo 1"), new GitHubRepository("Repo 2")});

        var gitHubConnectionService = new GitHubConnectionService(restTemplate);

        GitHubRepository[] actual = gitHubConnectionService.findByUsername("anyUser");

        assertThat(actual).containsExactly(new GitHubRepository("Repo 1"), new GitHubRepository("Repo 2"));
    }

}