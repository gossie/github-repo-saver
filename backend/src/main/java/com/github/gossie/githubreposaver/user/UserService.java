package com.github.gossie.githubreposaver.user;

import com.github.gossie.githubreposaver.Favorite;
import com.github.gossie.githubreposaver.GitHubConnectionService;
import com.github.gossie.githubreposaver.user.oauth.GitHubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GitHubConnectionService gitHubConnectionService;

    public User createOrGet(GitHubUser gitHubUser) {
        return userRepository.findByGitHubUserId(gitHubUser.getId())
                .orElseGet(() -> {
                    User user = new User();
                    user.setGitHubUserId(gitHubUser.getId());
                    user.setGitHubUsername(gitHubUser.getLogin());
                    return userRepository.save(user);
                });
    }

    public Optional<User> findByGitHubUsername(String username) {
        return userRepository.findByGitHubUsername(username);
    }

    public Optional<User> addFavorite(String username, Favorite favorite) {
        return findByGitHubUsername(username)
                .map(user -> {
                    user.addFavorite(favorite);
                    return userRepository.save(user);
                });
    }

    public Optional<User> removeFavorite(String username, String repoName) {
        return findByGitHubUsername(username)
                .map(user -> {
                    user.removedFavorite(repoName);
                    return userRepository.save(user);
                });
    }


}
