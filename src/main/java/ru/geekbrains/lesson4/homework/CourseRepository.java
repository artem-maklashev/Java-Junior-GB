package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseRepository {
    private final Session session;

    public CourseRepository(Session session) {
        this.session = session;
    }

    public void create() {
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            Course course = Course.create(); // You need to implement the static create() method in your Course class
            session.save(course);
            System.out.println("Курс сохранен");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Course> readAll() {
        Transaction transaction = session.beginTransaction();
        List<Course> courses = session.createQuery("from Course ").list();
        transaction.commit();
        return courses;
    }

    public Course readById(int courseId) {
        Transaction transaction = session.beginTransaction();
        Course course = session.get(Course.class, courseId);
        transaction.commit();
        return course;
    }

    public void update(int id) {
        Transaction transaction = session.beginTransaction();
        Course course = session.get(Course.class, id);
        course.updateTitle();
        course.updateDuration();
        session.update(course);
        System.out.println("Курс обновлен");
        transaction.commit();
    }

    public void delete(Course course) {
        Transaction transaction = session.beginTransaction();
        session.delete(course);
        System.out.println("Курс удален");
        transaction.commit();
    }
}
