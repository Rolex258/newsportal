package org.newsportal.rest.controller;

import org.newsportal.service.UserService;
import org.newsportal.service.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestController, Component, Repository, Service, Bean
@RestController
@RequestMapping("/news-portal")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll().orElseThrow());
    }

    @GetMapping(value = "users", params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getById(id).orElseThrow());
    }

    @GetMapping(value = "users", params = "username", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.getByUsername(username).orElseThrow());
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PatchMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateById(@PathVariable Long id, @RequestBody User user) {
        userService.updateUserById(id, user);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
