package com.github.gossie.githubreposaver.user.oauth;

import com.github.gossie.githubreposaver.security.JwtService;
import com.github.gossie.githubreposaver.user.LoginResponse;
import com.github.gossie.githubreposaver.user.User;
import com.github.gossie.githubreposaver.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth")
public class GitHubController {

    private final RestTemplate restTemplate;
    private final String clientId;
    private final String clientSecret;
    private final UserService userService;
    private final JwtService jwtService;

    public GitHubController(RestTemplate restTemplate, UserService userService, JwtService jwtService, @Value("${app.oauth.clientId}") String clientId, @Value("${app.oauth.clientSecret}")  String clientSecret) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.jwtService = jwtService;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @PostMapping
    public LoginResponse handleGitHubRedirect(@RequestParam String code) {

        String url = "https://github.com/login/oauth/access_token?"
                + "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&code=" + code;

        GithubAccessTokenResponse accessTokenResponse = restTemplate.postForObject(url, null, GithubAccessTokenResponse.class);

        ResponseEntity<GitHubUser> gitHubUser = restTemplate.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(accessTokenResponse.getAccessToken())),
                GitHubUser.class
        );

        User user = userService.createOrGet(gitHubUser.getBody());

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", List.of());
        String jwt = jwtService.createToken(claims, user.getGitHubUsername());

        return new LoginResponse(jwt);
    }

    private HttpHeaders createHeaders(String token){
        return new HttpHeaders() {{
            String authHeader = "Bearer " + token;
            set( "Authorization", authHeader );
        }};
    }

}
