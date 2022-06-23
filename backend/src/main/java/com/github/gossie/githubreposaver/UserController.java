package com.github.gossie.githubreposaver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return ResponseEntity.of(userService.findByUsername(username));
    }

    @PostMapping("/{username}/favorites")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFavorite(@PathVariable String username, @RequestBody Favorite favorite) {
        userService.addFavorite(username, favorite);
    }

}
