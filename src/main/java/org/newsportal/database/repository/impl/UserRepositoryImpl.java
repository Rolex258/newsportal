package org.newsportal.database.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.newsportal.database.repository.entity.User;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.database.repository.util.HibernateUtil;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<User> findAll() {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    @Override
    public User getById(Long id) {
        try(Session session = sessionFactory.openSession()){
            return session.get(User.class, id);
        }
    }

    @Override
    public User getByUsername(String username) {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from User where username = :name", User.class);
            query.setParameter("name", username);
            return (User)query.getSingleResult();
        }
    }

    @Override
    public void updateUserById(Long id, User user) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            user.setId(id);
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(getById(id));
            transaction.commit();
        }
    }

    public static void main(String[] args) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = new User();
        user.setUsername("newnew23");
        user.setPassword("smpass22");
        userRepository.updateUserById(1L, user);
        //userRepository.deleteUserById(3L);
        System.out.println(userRepository.findAll());

    }



}
