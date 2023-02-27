package org.newsportal.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.service.mapper.UserMapper;
import org.newsportal.service.model.User;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    public void init() {
        user = new User(10L, "testname", "pass");
    }

    @Test
    void getAll() {
        userService.getAll();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void getById() {
        userService.getById(Mockito.anyLong());
        Mockito.verify(userRepository).findById(Mockito.anyLong());
    }

    @Test
    void getByUsername() {
        userService.getByUsername(Mockito.anyString());
        Mockito.verify(userRepository).findByUsername(Mockito.anyString());
    }

    @Test
    void addUser() {
        userService.addUser(Mockito.any());
        Mockito.verify(userRepository).addUser(Mockito.any());
    }

    @Test
    void updateUserById() {
        userService.updateUserById(1L, Mockito.any());
        Mockito.verify(userRepository).updateUserById(ArgumentMatchers.anyLong(), Mockito.any());
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(Mockito.anyLong());
        Mockito.verify(userRepository).deleteUserById(Mockito.anyLong());
    }
}