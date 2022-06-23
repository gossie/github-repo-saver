package com.github.gossie.githubreposaver;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void shouldFindUserByUsername() {
        var userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("anyUser")).thenReturn(Optional.of(new User("4711", "anyUser", List.of())));
        when(userRepository.save(new User("4711", "anyUser", List.of()))).thenReturn(new User("4711", "anyUser", List.of()));

        var gitHubConnectionService = mock(GitHubConnectionService.class);
        when(gitHubConnectionService.userExists("anyUser")).thenReturn(true);

        var userService = new UserService(userRepository, gitHubConnectionService);

        assertThat(userService.findByUsername("anyUser")).contains(new User("4711", "anyUser", List.of()));
    }

    @Test
    void shouldCreateNewUser() {
        var userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("anyuser")).thenReturn(Optional.empty());
        when(userRepository.save(new User("anyUser"))).thenReturn(new User("4711", "anyUser", List.of()));

        var gitHubConnectionService = mock(GitHubConnectionService.class);
        when(gitHubConnectionService.userExists("anyUser")).thenReturn(true);

        var userService = new UserService(userRepository, gitHubConnectionService);

        assertThat(userService.findByUsername("anyUser")).contains(new User("4711", "anyUser", List.of()));
    }

    @Test
    void shouldNotFindUserOnGitHub() {
        var userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("anyuser")).thenReturn(Optional.empty());

        var gitHubConnectionService = mock(GitHubConnectionService.class);
        when(gitHubConnectionService.userExists("anyUser")).thenReturn(false);

        var userService = new UserService(userRepository, gitHubConnectionService);

        assertThat(userService.findByUsername("anyUser")).isEmpty();
        verify(userRepository, never()).save(any(User.class));
    }

}