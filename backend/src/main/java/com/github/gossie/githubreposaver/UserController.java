package com.github.gossie.githubreposaver;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{username}")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return ResponseEntity.of(userService.findByUsername(username));
    }

    @PostMapping("/favorites")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addFavorite(@PathVariable String username, @RequestBody Favorite favorite) {
        return ResponseEntity.of(userService.addFavorite(username, favorite));
    }

    @DeleteMapping("/favorites")
    public ResponseEntity<User> removeFavorite(@PathVariable String username, @RequestParam String repoName) {
        return ResponseEntity.of(userService.removeFavorite(username, repoName));
    }

}
