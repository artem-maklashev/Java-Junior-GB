package ru.geekbrains.lesson3.hw.task2;

import java.io.*;

public class Student implements Externalizable {
    private String name;
    private int age;
    private double gpa;

    public Student() {

    }

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.gpa = GPA;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return gpa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Имя: ").append(name)
                .append(" | Возраст: ").append(age)
                .append(" | Средний балл: ").append(gpa);
        return sb.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
        out.writeDouble(gpa);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
        gpa = in.readDouble();
    }


}
