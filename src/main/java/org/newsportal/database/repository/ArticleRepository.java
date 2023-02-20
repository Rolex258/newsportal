package org.newsportal.database.repository;

import org.newsportal.database.entity.Article;

import java.util.List;

public interface ArticleRepository {
    List<Article> findAll();

    Article findById(Long id);

    Article findByTitle(String username);

    void addArticle(Article article);

    void updateArticleById(Long id, Article article);

    void deleteArticleById(Long id);

    Article findByUserId(Long userId);
}
