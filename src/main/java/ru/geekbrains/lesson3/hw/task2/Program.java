package ru.geekbrains.lesson3.hw.task2;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.geekbrains.lesson3.hw.task2.StudentList.*;


public class Program {
    public static void main(String[] args) {
        List<Student> students;
        File f = new File(FILE_JSON);
        if (f.exists() && !f.isDirectory())
            students = loadTasksFromFile(FILE_JSON);
        else
            students = prepareStudents();
        StudentList.saveTasksToFile(FILE_JSON, students);
        StudentList.saveTasksToFile(FILE_XML, students);

        displayStudents(students);

    }

    private static List<Student> prepareStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Артем", 45, 4.75));
        list.add(new Student("Евгений", 47, 3.43));
        return list;
    }
}
