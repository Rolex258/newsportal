package org.newsportal.database.repository;

import org.newsportal.database.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    void addUser(User user);
    void updateUserById(Long id, User user);
    void deleteUserById(Long id);
}
