package org.newsportal.service.impl;

import org.newsportal.service.model.User;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.service.UserService;
import org.newsportal.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.of(userMapper.mapToService(userRepository.findAll()));
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.of(userMapper.mapToService(userRepository.findById(id)));
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return Optional.of(userMapper.mapToService(userRepository.findByUsername(username)));
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(userMapper.mapToDatabase(user));
    }

    @Override
    public void updateUserById(Long id, User user) {
        userRepository.updateUserById(id, userMapper.mapToDatabase(user));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }
}
