package org.newsportal.database.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.newsportal.database.repository.entity.Article;
import org.newsportal.database.repository.ArticleRepository;
import org.newsportal.database.repository.util.HibernateUtil;

import java.util.List;

//@Component @Repository @Service @Bean @RestController - декларация бина
public class ArticleRepositoryImpl implements ArticleRepository {
    private final SessionFactory sessionFactory;

    public ArticleRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
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
    public Article getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Article article = session.get(Article.class, id);
            return article;
        }
    }

    @Override
    public Article getByTitle(String searchtitle) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Article where title = :title", Article.class);
            query.setParameter("title", searchtitle);
            Article article = (Article) query.getSingleResult();
            return article;
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
            session.remove(getById(id));
            transaction.commit();
        }
    }


    public static void main(String[] args) {
        ArticleRepositoryImpl articleRepository = new ArticleRepositoryImpl();
        System.out.println(articleRepository.findAll());

        articleRepository.deleteArticleById(3L);

        System.out.println(articleRepository.findAll());

    }
}
