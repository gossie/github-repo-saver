package com.github.gossie.githubreposaver.user;

import com.github.gossie.githubreposaver.Favorite;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getUser(Principal principal) {
        return ResponseEntity.of(userService.findByGitHubUsername(principal.getName()));
    }

    @PostMapping("/me/favorites")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addFavorite(@RequestBody Favorite favorite, Principal principal) {
        return ResponseEntity.of(userService.addFavorite(principal.getName(), favorite));
    }

    @DeleteMapping("/me/favorites")
    public ResponseEntity<User> removeFavorite(@RequestParam String repoName, Principal principal) {
        return ResponseEntity.of(userService.removeFavorite(principal.getName(), repoName));
    }

}
