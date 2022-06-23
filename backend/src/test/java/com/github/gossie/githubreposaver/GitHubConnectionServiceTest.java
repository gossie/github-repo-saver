package com.github.gossie.githubreposaver;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void shouldFindUser() {
        var restTemplate = mock(RestTemplate.class);
        var gitHubConnectionService = new GitHubConnectionService(restTemplate);

        assertThat(gitHubConnectionService.userExists("anyUser")).isTrue();
    }

    @Test
    void shouldNotFindUser() {
        var restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForObject("https://api.github.com/users/anyUser", Void.class)).thenThrow(new RuntimeException());
        var gitHubConnectionService = new GitHubConnectionService(restTemplate);

        assertThat(gitHubConnectionService.userExists("anyUser")).isFalse();
    }

    @Test
    void shouldFindRepo() {
        var restTemplate = mock(RestTemplate.class);
        var gitHubConnectionService = new GitHubConnectionService(restTemplate);

        assertThat(gitHubConnectionService.repoExists("fullname")).isTrue();
    }

    @Test
    void shouldNotFindRepo() {
        var restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForObject("https://api.github.com/repos/fullname", Void.class)).thenThrow(new RuntimeException());
        var gitHubConnectionService = new GitHubConnectionService(restTemplate);

        assertThat(gitHubConnectionService.repoExists("fullname")).isFalse();
    }

}