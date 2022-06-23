package com.github.gossie.githubreposaver;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    void shouldFindUserByUsername() {
        var userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("anyUser")).thenReturn(Optional.of(new User("4711", "anyUser", List.of())));

        var userService = new UserService(userRepository);

        assertThat(userService.findByUsername("anyUser")).isEqualTo(new User("4711", "anyUser", List.of()));
    }

    @Test
    void shouldCreateNewUser() {
        var userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("anyuser")).thenReturn(Optional.empty());
        when(userRepository.save(new User("anyUser"))).thenReturn(new User("4711", "anyUser", List.of()));

        var userService = new UserService(userRepository);

        assertThat(userService.findByUsername("anyUser")).isEqualTo(new User("4711", "anyUser", List.of()));
    }

}