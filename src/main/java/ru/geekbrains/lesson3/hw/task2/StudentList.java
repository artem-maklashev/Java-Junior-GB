package ru.geekbrains.lesson3.hw.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.geekbrains.lesson3.task2.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentList {
    public static final String FILE_JSON = "student.json";

    public static final String FILE_XML = "student.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();



    public static void addNewStudent(Student student, List<Student> students) {
        students.add(student);
        saveTasksToFile(FILE_JSON, students);
        saveTasksToFile(FILE_XML, students);
        System.out.println("Новая задача добавлена.");
    }

    public static void saveTasksToFile(String fileName, List<Student> students) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), students);
            }  else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), students);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadTasksFromFile(String fileName) {
        List<Student> students = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    students = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                } else if (fileName.endsWith(".xml")) {
                    students = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return students;
    }



    public static void displayStudents(List<Student> students) {
        System.out.println("Список студентов:");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + " " + student.toString());
        }
    }

}
