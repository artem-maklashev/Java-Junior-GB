package ru.geekbrains.lesson1.hw.task1;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Напишите программу, которая использует Stream API для обработки списка чисел.
 * Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> numbersList = List.of(1, 5, 4, 8, 6, 9, 13, 24);

        double  result = numbersList.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.averagingDouble(Integer::doubleValue));
        System.out.println("Average of even numbers is: " + result);
    }
}
