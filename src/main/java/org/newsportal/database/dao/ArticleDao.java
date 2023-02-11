package org.newsportal.database.dao;

import org.newsportal.database.dao.entity.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> findAll();
    Article getById(Long id);
    Article getByTitle(String username);
    void updateArticleById(Long id, Article article);
    void deleteArticleById(Long id);
}
