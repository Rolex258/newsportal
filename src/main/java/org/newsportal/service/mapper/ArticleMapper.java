package org.newsportal.service.mapper;

import org.newsportal.service.model.Article;

import java.util.List;

public interface ArticleMapper {
    Article mapToService(org.newsportal.database.entity.Article source);
    org.newsportal.database.entity.Article mapToDatabase(Article source);
    List<Article> mapToService(List<org.newsportal.database.entity.Article> source);
    List<org.newsportal.database.entity.Article> mapToDatabase(List<Article> source);
}
