package org.newsportal.database.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.newsportal.database.entity.User;
import org.newsportal.database.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where username = :name", User.class);
            query.setParameter("name", username);
            return (User) query.getSingleResult();
        }
    }

    @Override
    public void addUser(User user) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void updateUserById(Long id, User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            user.setId(id);
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        }
    }
}
