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

    @GetMapping(value = "/user_id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id).orElseThrow());
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username).orElseThrow());
    }

    @PatchMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateById(@PathVariable Long id, @RequestBody User user) {
        userService.updateUserById(id, user);
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }


}
