package org.newsportal.database.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.newsportal.database.entity.Article;
import org.newsportal.database.repository.ArticleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.util.List;

//@Component @Repository @Service @Bean @RestController - декларация бина
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private final SessionFactory sessionFactory;

    public ArticleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Article> findAll() {
        try (Session session = sessionFactory.openSession()) {
/*          CriteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Article.class);
            Root root = criteriaQuery.from(Article.class);
            criteriaQuery.select(root);
            Query query = session.createQuery(criteriaQuery);
            return query.getResultList();*/

            Query query = session.createQuery("from Article", Article.class);
            return query.list();
        }
    }

    @Override
    public Article findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Article.class, id);
        }
    }

    @Override
    public Article findByTitle(String searchTitle) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Article where title = :title", Article.class);
            query.setParameter("title", searchTitle);
            Article article = (Article) query.getSingleResult();
            return article;
        }
    }

    @Override
    public void addArticle(Article article) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(article);
            transaction.commit();
        }
    }

    @Override
    public void updateArticleById(Long id, Article article) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            article.setId(id);
            session.update(article);
            transaction.commit();
        }
    }

    @Override
    public void deleteArticleById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(findById(id));
            transaction.commit();
        }
    }

    @Override
    public Article findByUserId(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Article> query = session.createQuery("from Article where user.id = :id");
            query.setParameter("id", userId);
            Article article = query.getSingleResult();
            return article;
        }

    }
}
