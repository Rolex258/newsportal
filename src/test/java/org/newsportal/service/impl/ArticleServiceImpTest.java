package org.newsportal.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newsportal.database.repository.ArticleRepository;
import org.newsportal.service.impl.ArticleServiceImpl;
import org.newsportal.service.mapper.ArticleMapper;
import org.newsportal.service.model.Article;
import org.newsportal.service.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ArticleServiceImpTest {
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleMapper articleMapper;
    @InjectMocks
    private ArticleServiceImpl articleService;

    private static User user;
    private static Article article;

    private List<Article> list = new ArrayList<>(Collections.singletonList(article));

    @BeforeAll
    public static void init() {
//        user = new User(10L,"testname","testpass");
//        article = new Article(10L, "testtitle", "somenews",user);
    }

    @Test
    void getAll() {
        articleService.getAll();
        verify(articleRepository).findAll();
    }

    @Test
    void getById() {
        org.newsportal.database.entity.Article article = null;
        org.newsportal.database.entity.User user = null;

        when(articleRepository.findById(Mockito.anyLong()))
                .thenReturn(article = new org.newsportal.database.entity.Article
                        (5L, "testna", "pas",
                                user = new org.newsportal.database.entity.User(5L, "a", "b")));
        when(articleMapper.mapToService(article))
                .thenReturn(new Article(article.getId(), article.getTitle(), article.getContent(),
                        new User(user.getId(), user.getUsername(), user.getPassword())));

        articleService.getById(Mockito.anyLong());
        Mockito.verify(articleRepository).findById(Mockito.anyLong());

    }

    @Test
    void getByTitle() {
        org.newsportal.database.entity.Article article = null;
        org.newsportal.database.entity.User user = null;

        when(articleRepository.findByTitle(Mockito.anyString()))
                .thenReturn(article = new org.newsportal.database.entity.Article
                        (5L, "testna", "pas",
                                user = new org.newsportal.database.entity.User(5L, "a", "b")));
        when(articleMapper.mapToService(article))
                .thenReturn(new Article(article.getId(), article.getTitle(), article.getContent(),
                        new User(user.getId(), user.getUsername(), user.getPassword())));

        articleService.getByTitle(Mockito.anyString());
        Mockito.verify(articleRepository).findByTitle(Mockito.anyString());
    }

    @Test
    void addArticle() {
        articleService.addArticle(Mockito.any());
        Mockito.verify(articleRepository).addArticle(Mockito.any());
    }

    @Test
    void updateArticleById() {
        articleService.updateArticleById(1L, Mockito.any());
        Mockito.verify(articleRepository).updateArticleById(ArgumentMatchers.anyLong(), Mockito.any());
    }

    @Test
    void deleteArticleById() {
        articleService.deleteArticleById(Mockito.anyLong());
        Mockito.verify(articleRepository).deleteArticleById(Mockito.anyLong());
    }

    @Test
    void getByUserId() {
        org.newsportal.database.entity.Article article = null;
        org.newsportal.database.entity.User user = null;

        when(articleRepository.findByUserId(Mockito.anyLong()))
                .thenReturn(article = new org.newsportal.database.entity.Article
                        (5L, "testna", "pas",
                                user = new org.newsportal.database.entity.User(5L, "a", "b")));
        when(articleMapper.mapToService(article))
                .thenReturn(new Article(article.getId(), article.getTitle(), article.getContent(),
                        new User(user.getId(), user.getUsername(), user.getPassword())));

        articleService.getByUserId(Mockito.anyLong());
        Mockito.verify(articleRepository).findByUserId(Mockito.anyLong());
    }
}
