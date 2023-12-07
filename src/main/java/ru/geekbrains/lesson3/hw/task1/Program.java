package ru.geekbrains.lesson3.hw.task1;

import java.io.*;

/**
 * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
 * Обеспечьте поддержку сериализации для этого класса. Создайте объект класса Student и инициализируйте его данными.
 * Сериализуйте этот объект в файл. Десериализуйте объект обратно в программу из файла. Выведите все поля объекта,
 * включая GPA, и обсудите, почему значение GPA не было сохранено/восстановлено.
 */
public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Артем", 45, 4.75);
        try (FileOutputStream fos = new FileOutputStream("student.bin")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.close();
        }
        try (FileInputStream fis = new FileInputStream("student.bin")){
            ObjectInputStream ois = new ObjectInputStream(fis);
            student = (Student) ois.readObject();
        }
        System.out.printf("Имя студента: %s\n", student.getName());
        System.out.printf("Возраст: %s\n", student.getAge());
        System.out.printf("Средний балл (должен быть 0,\n т.к. поле GPA с модификатором transient и не должно быть сериализовано: %s\n", student.getGPA());
    }
}
