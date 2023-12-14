package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;

import java.util.List;

public class Program {

    /**
     * Задание
     * =======
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */
    public static void main(String[] args) {
        DB db = new DB();
        // Create three Course records
        for (int i = 0; i < 3; i++) {
            try (Session session = db.openSession()) {
                CourseRepository cr = new CourseRepository(session);
                cr.create();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Read all Course records and print them
        try (Session session = db.openSession()) {
            CourseRepository cr = new CourseRepository(session);
            List<Course> courses = cr.readAll();
            courses.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
