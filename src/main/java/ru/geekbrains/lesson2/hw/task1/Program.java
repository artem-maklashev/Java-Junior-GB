package ru.geekbrains.lesson2.hw.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Задача 1:
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */
public class Program {
    public static void main(String[] args) throws IllegalAccessException {
        List<Animal> animals = List.of(new Cat("Барсик", 5, false),
                new Dog("Палкан", 10, true, false, false),
                new Cat("Мурка", 3, true));

        for (Animal animal : animals) {
            String className = animal.getClass().getName();
            System.out.printf("Class name: %s\n", className);

            Class<?> superClass = animal.getClass().getSuperclass();
            System.out.printf("\tSuperclass name: %s\n", superClass.getName());

            Field[] superClassFields = superClass.getDeclaredFields();
            System.out.println("\tSuperclass Fields:");
            for (Field field : superClassFields) {
                field.setAccessible(true);
                System.out.printf("\t\t%s: %s: %s\n", field.getName(), field.getType(), field.get(animal));
            }

            Method[] superClassMethods = superClass.getMethods();
            System.out.println("\tSuperclass Methods:");
            for (Method method : superClassMethods) {
                System.out.printf("\t\t%s\n", method.getName());
            }

            Field[] fields = animal.getClass().getDeclaredFields();
            System.out.println("\tFields:");
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.printf("\t\t%s: %s: %s\n", field.getName(), field.getType(), field.get(animal));
            }

            Method[] methods = animal.getClass().getMethods();
            System.out.println("\tMethods:");
            for (Method method : methods) {
                System.out.printf("\t\t%s\n", method.getName());
            }

            Constructor<?>[] constructors = animal.getClass().getDeclaredConstructors();
            System.out.println("\tConstructors:");
            for (Constructor<?> constructor : constructors) {
                System.out.printf("\t\t%s\n", constructor.getName());
            }

            try {
                Method makeSoundMethod = animal.getClass().getMethod("makeSound");
                makeSoundMethod.invoke(animal);
            } catch (NoSuchMethodException e) {
                System.out.println("\tУ этого объекта отсутствует метод makeSound()");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



