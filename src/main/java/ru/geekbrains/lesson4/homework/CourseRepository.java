package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Random;

public class CourseRepository {
    private final DB db;
    private static final Random random = new Random();

    public CourseRepository() {
        this.db = new DB();
    }

    public void create() {
        try (Session session = db.openSession()) {
            Transaction transaction = session.beginTransaction();
            Course course = Course.create(); // You need to implement the static create() method in your Course class
            session.save(course);
            System.out.println("Курс сохранен");
            transaction.commit();
        }
    }

    public List<Course> readAll() {
        try (Session session = db.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Course> courses = session.createQuery("from Course ").list();
            transaction.commit();
            return courses;
        }
    }

    public Course readById(int courseId) {
        try (Session session = db.openSession()) {
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            transaction.commit();
            return course;
        }
    }


    public void update(int id) {
        try (Session session = db.openSession()) {
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            course.updateTitle();
            course.updateDuration();
            session.update(course);
            System.out.println("Курс обновлен");
            transaction.commit();
        }
    }

    public void delete(Course course) {
        try (Session session = db.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(course);
            System.out.println("Курс удален");
            transaction.commit();
        }
    }

    public List<Integer> getIndexesArray() {
        try (Session session = db.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery("SELECT id FROM Course ", Integer.class);
            List<Integer> result = query.getResultList();
            transaction.commit();
            return result;
        }
    }

    Integer getRandomId() {
        return random.nextInt(getIndexesArray().size());
    }
}
