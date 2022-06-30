package com.github.gossie.githubreposaver;

import com.github.gossie.githubreposaver.user.User;
import com.github.gossie.githubreposaver.user.UserRepository;
import com.github.gossie.githubreposaver.user.UserService;
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
        when(userRepository.findByGitHubUsername("anyUser")).thenReturn(Optional.of(new User("4711", "anyUser", 1L, List.of())));
        when(userRepository.save(new User("4711", "anyUser", 1L, List.of()))).thenReturn(new User("4711", "anyUser", 1L, List.of()));

        var gitHubConnectionService = mock(GitHubConnectionService.class);
        when(gitHubConnectionService.userExists("anyUser")).thenReturn(true);

        var userService = new UserService(userRepository, gitHubConnectionService);

        assertThat(userService.findByGitHubUsername("anyUser")).contains(new User("4711", "anyUser", 1L, List.of()));
    }

}