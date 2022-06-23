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

    public Optional<User> addFavorite(String username, Favorite favorite) {
        return findByUsername(username)
                .map(user -> {
                    user.addFavorite(favorite);
                    return userRepository.save(user);
                });
    }

    public Optional<User> removeFavorite(String username, String repoName) {
        return findByUsername(username)
                .map(user -> {
                    user.removedFavorite(repoName);
                    return userRepository.save(user);
                });
    }
}
