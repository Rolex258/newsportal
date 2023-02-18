package org.newsportal.service.mapper.impl;

import org.newsportal.service.mapper.UserMapper;
import org.newsportal.service.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User mapToService(org.newsportal.database.entity.User source) {
        if (source == null) {
            return null;
        }
        return new User(source.getId(), source.getUsername(), source.getPassword());
    }

    @Override
    public org.newsportal.database.entity.User mapToDatabase(User source) {
        if (source == null) {
            return null;
        }
        return new org.newsportal.database.entity.User(source.getId(), source.getUsername(), source.getPassword());
    }

    @Override
    public List<org.newsportal.database.entity.User> mapToDatabase(List<User> source) {
        return source.stream().filter(Objects::nonNull).map(this::mapToDatabase).collect(Collectors.toList());
    }

    @Override
    public List<User> mapToService(List<org.newsportal.database.entity.User> source) {
        return source.stream().filter(Objects::nonNull).map(this::mapToService).collect(Collectors.toList());
    }
}
