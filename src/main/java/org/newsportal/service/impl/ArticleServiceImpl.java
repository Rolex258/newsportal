package org.newsportal.service.impl;

import org.newsportal.database.repository.ArticleRepository;
import org.newsportal.service.ArticleService;
import org.newsportal.service.mapper.ArticleMapper;
import org.newsportal.service.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public Optional<List<Article>> getAll() {
        return Optional.of(articleMapper.mapToService(articleRepository.findAll()));
    }

    @Override
    public Optional<Article> getById(Long id) {
        return Optional.of(articleMapper.mapToService(articleRepository.findById(id)));
    }

    @Override
    public Optional<Article> getByTitle(String username) {
        return Optional.of(articleMapper.mapToService(articleRepository.findByTitle(username)));
    }

    @Override
    public void updateArticleById(Long id, Article article) {
        articleRepository.updateArticleById(id, articleMapper.mapToDatabase(article));
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteArticleById(id);
    }

    @Override
    public Optional<Article> getByUserId(Long userId) {
        return Optional.of(articleMapper.mapToService(articleRepository.findByUserId(userId)));
    }
}
