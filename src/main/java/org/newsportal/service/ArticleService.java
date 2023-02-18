package org.newsportal.service;

import org.newsportal.service.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Optional<List<Article>> getAll();

    Optional<Article> getById(Long id);

    Optional<Article> getByTitle(String username);

    void updateArticleById(Long id, Article article);

    void deleteArticleById(Long id);

    Optional<Article> getByUserId(Long userId);
}
