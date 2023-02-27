package org.newsportal.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.newsportal.database.entity.Article;
import org.newsportal.database.entity.User;
import org.newsportal.database.repository.ArticleRepository;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.database.repository.impl.ArticleRepositoryImpl;
import org.newsportal.database.repository.impl.UserRepositoryImpl;
import org.newsportal.service.impl.ArticleServiceImpl;
import org.newsportal.service.impl.UserServiceImpl;
import org.newsportal.service.mapper.ArticleMapper;
import org.newsportal.service.mapper.UserMapper;
import org.newsportal.service.mapper.impl.ArticleMapperImpl;
import org.newsportal.service.mapper.impl.UserMapperImpl;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Properties;


@Testcontainers
public class NewsPortalIntegrationTest {
    @Container
    private static final MySQLContainer container = new MySQLContainer();

    private static ArticleService articleService;
    private static ArticleRepository articleRepository;
    private static ArticleMapper articleMapper;
    private static UserService userService;
    private static UserRepository userRepository;
    private static UserMapper userMapper;
    private static SessionFactory sessionFactory;
    private static Properties properties;

    @BeforeAll
    public static void init() {
        container.start();

        properties = new Properties();
        properties.put("hibernate.connection.driver_class", container.getDriverClassName());
        properties.put("hibernate.connection.password", container.getPassword());
        properties.put("hibernate.connection.url", container.getJdbcUrl());
        properties.put("hibernate.connection.username", container.getUsername());
        properties.put("hibernate.hbm2ddl.auto", "create-drop");

        sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Article.class)
                .buildSessionFactory();

        articleMapper = new ArticleMapperImpl();
        articleRepository = new ArticleRepositoryImpl(sessionFactory);
        articleService = new ArticleServiceImpl(articleRepository, articleMapper);

        userMapper = new UserMapperImpl();
        userRepository = new UserRepositoryImpl(sessionFactory);
        userService = new UserServiceImpl(userRepository, userMapper);

    }

    @Test
    public void articleIntegrationTest() {

        //Creating a new User and add to the DB
        org.newsportal.service.model.User user = new org.newsportal.service.model.User();
        user.setId(1L);
        user.setUsername("log1");
        user.setPassword("pass1");
        userService.addUser(user);

        //Creating a new Article and add to the DB
        org.newsportal.service.model.Article article = new org.newsportal.service.model.Article();
        article.setId(1L);
        article.setTitle("title1");
        article.setContent("content1");
        article.setUser(user);
        articleService.addArticle(article);

        //testing userService methods
        userService.getAll();
        userService.getById(user.getId());
        userService.updateUserById(1L, new org.newsportal.service.model.User(2L, "oass", "asd"));

        //Testing articleService methods
        articleService.getById(article.getId());
        articleService.updateArticleById(1L, new org.newsportal.service.model.Article
                (2L, "qwe", "eqw", user));
        articleService.getByUserId(1L);

        //Deleting method
        userService.deleteUserById(1L);
    }


    @AfterAll
    public static void shutdown() {
        container.stop();
    }

}
