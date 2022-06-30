package com.github.gossie.githubreposaver.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByGitHubUsername(String username);

    Optional<User> findByGitHubUserId(long id);
}
