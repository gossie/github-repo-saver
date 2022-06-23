package com.github.gossie.githubreposaver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GitHubConnectionService gitHubConnectionService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .or(() -> Optional.of(new User(username)))
                .filter(user -> gitHubConnectionService.userExists(user.getUsername()))
                .map(userRepository::save);
    }

}
