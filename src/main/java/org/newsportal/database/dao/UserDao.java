package org.newsportal.database.dao;

import org.newsportal.database.dao.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User getById(Long id);
    User getByUsername(String username);
    void updateUserById(Long id, User user);
    void deleteUserById(Long id);
}
