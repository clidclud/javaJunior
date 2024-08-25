package hw1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {

//  Напишите программу, которая использует Stream API для обработки списка чисел.
//  Программа должна вывести на экран среднее значение всех четных чисел в списке.

        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        int length = 10;
        for (int i = 0; i < length; i++) {
            list.add(random.nextInt(0,10));
        }

        System.out.println("Сгенерированный список: " + list);
        List<Integer> listEvenNum = list.stream().filter(num -> num % 2 == 0).collect(toList());
        System.out.println("Список четных элементов: " + listEvenNum);
        double average = listEvenNum.stream().mapToInt(num -> num).average().getAsDouble();
        System.out.println("Среднее арифметическое: " + average);

//        Однострочная запись
//        double average1 = list.stream().filter(num -> num % 2 == 0).
//                mapToInt(Integer::intValue).average().getAsDouble();
//        System.out.println("Среднее арифметическое: " + average1);
    }
}
