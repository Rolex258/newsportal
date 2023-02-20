package org.newsportal.service;

import org.newsportal.service.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    Optional<List<org.newsportal.service.model.User>> getAll();
    Optional<User> getById(Long id);
    Optional<User> getByUsername(String username);
    void addUser(User user);
    void updateUserById(Long id, User user);
    void deleteUserById(Long id);

}
