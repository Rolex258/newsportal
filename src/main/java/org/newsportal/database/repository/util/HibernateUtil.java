package org.newsportal.database.repository.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.newsportal.database.repository.entity.Article;
import org.newsportal.database.repository.entity.User;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    public static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }

    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static void shutdown(){
        getSessionFactory().close();
    }
}
