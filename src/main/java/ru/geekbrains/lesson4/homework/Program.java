package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

        try {
            CourseRepository cr = new CourseRepository();
            //Создание экземпляров класса Course
            for (int i = 0; i < 3; i++) {
                cr.create();
            }

            //Чтение всех записей из таблицы
            List<Course> courses = cr.readAll();
            courses.forEach(System.out::println);


            //Чтение записи по Id
            int id = cr.getRandomId();
            Course course = cr.readById(id);
            System.out.println(course);

            //Изменение записи
            cr.update(id);
            System.out.println(cr.readById(id));

            //удаление записи
            cr.delete(course);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
