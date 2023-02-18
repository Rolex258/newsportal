package org.newsportal.service.mapper.impl;

import org.newsportal.service.mapper.ArticleMapper;
import org.newsportal.service.model.Article;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ArticleMapperImpl implements ArticleMapper {
    private UserMapperImpl userMapper;

    public ArticleMapperImpl(UserMapperImpl userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Article mapToService(org.newsportal.database.entity.Article source) {
        if (source == null) return null;
        return new Article(source.getId(), source.getTitle(), source.getContent(), userMapper.mapToService(source.getUser()));
    }

    @Override
    public org.newsportal.database.entity.Article mapToDatabase(Article source) {
        if (source == null) return null;
        return new org.newsportal.database.entity.Article(source.getId(), source.getTitle(), source.getContent(), userMapper.mapToDatabase(source.getUser()));
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
