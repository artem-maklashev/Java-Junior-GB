package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DB {
    public static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernateHW.cfg.xml")
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();

    private final Session session;

    public DB() {
        this.session = sessionFactory.getCurrentSession();
    }

    public Session getSession() {
        return session;
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }
}
