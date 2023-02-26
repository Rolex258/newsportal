package org.newsportal.service.mapper.impl;

import org.newsportal.service.mapper.ArticleMapper;
import org.newsportal.service.model.Article;
import org.newsportal.service.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ArticleMapperImpl implements ArticleMapper {
    @Override
    public Article mapToService(org.newsportal.database.entity.Article source) {
        if (source == null) return null;

        User user = null;

        if (source.getUser() != null) {
            user = new User();
            user.setId(source.getUser().getId());
            user.setPassword(source.getUser().getPassword());
            user.setUsername(source.getUser().getUsername());
        }

        return new Article(source.getId(), source.getTitle(), source.getContent(), user);
    }

    @Override
    public org.newsportal.database.entity.Article mapToDatabase(Article source) {
        if (source == null) return null;
        org.newsportal.database.entity.User user = null;

        if (source.getUser() != null) {
            user = new org.newsportal.database.entity.User();
            user.setUsername(source.getUser().getUsername());
            user.setPassword(source.getUser().getPassword());
            user.setId(source.getUser().getId());
        }
        return new org.newsportal.database.entity.Article(source.getId(), source.getTitle(), source.getContent(), user);
    }

    @Override
    public List<Article> mapToService(List<org.newsportal.database.entity.Article> source) {
        return source.stream().filter(Objects::nonNull).map(this::mapToService).collect(Collectors.toList());
    }

    @Override
    public List<org.newsportal.database.entity.Article> mapToDatabase(List<Article> source) {
        return source.stream().filter(Objects::nonNull).map(this::mapToDatabase).collect(Collectors.toList());
    }


}
