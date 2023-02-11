package org.newsportal.database.dao.impl;

import org.newsportal.database.dao.ArticleDao;
import org.newsportal.database.dao.entity.Article;
import org.newsportal.database.dao.util.ConnectionPool;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
//    private final Connection connection;

    public ArticleDaoImpl() {
//        try {
//            this.connection = ConnectionPool.getConnectionPool().getConnection();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Article");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                articles.add(getArticle(resultSet.getLong("id"), resultSet.getString("title"),
                        resultSet.getString("content"), resultSet.getLong("userId")));
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    @Override
    public Article getById(Long id) {
        Article article = null;
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Article WHERE id=?")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                article = getArticle(resultSet.getLong("id"), resultSet.getString("title"),
                        resultSet.getString("content"), resultSet.getLong("userId"));
            }
            resultSet.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    @Override
    public Article getByTitle(String title) {
        Article article = null;
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Article WHERE title=?")) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                article = getArticle(resultSet.getLong("id"), resultSet.getString("title"),
                        resultSet.getString("content"), resultSet.getLong("userId"));
            }
            resultSet.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    @Override
    public void updateArticleById(Long id, Article article) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Article SET title=?, content=? WHERE id=?")) {
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteArticleById(Long id) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Article WHERE id=?")) {

            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Article getArticle(Long id, String title, String content, Long userId) {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setUserId(userId);
        return article;
    }

    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDaoImpl();
        System.out.println(articleDao.findAll());
        System.out.println(articleDao.getById(1L));

    }
}
