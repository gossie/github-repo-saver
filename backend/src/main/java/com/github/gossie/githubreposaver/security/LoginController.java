package com.github.gossie.githubreposaver.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final String gitHubClientId;

    public LoginController(@Value("${app.github.clientId}") String gitHubClientId) {
        this.gitHubClientId = gitHubClientId;
    }

    @GetMapping
    public AuthData getAuthData() {
        return new AuthData(gitHubClientId);
    }

}
