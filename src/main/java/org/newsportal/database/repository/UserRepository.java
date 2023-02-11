package org.newsportal.database.repository;

import org.newsportal.database.repository.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User getById(Long id);
    User getByUsername(String username);
    void updateUserById(Long id, User user);
    void deleteUserById(Long id);
}
