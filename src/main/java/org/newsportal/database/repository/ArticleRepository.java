package org.newsportal.database.repository;

import org.newsportal.database.repository.entity.Article;

import java.util.List;

public interface ArticleRepository {
    List<Article> findAll();
    Article getById(Long id);
    Article getByTitle(String username);
    void updateArticleById(Long id, Article article);
    void deleteArticleById(Long id);
}
