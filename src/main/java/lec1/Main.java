package lec1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        PlainInterface plainInterface = new PlainInterface() {
//            @Override
//            public String action(int x, int y) {
//                return String.valueOf(x+y);
//            }
//        };
//        System.out.println(plainInterface.action(5,1));

//        PlainInterface plainInterface = Integer::sum;
//        System.out.println(plainInterface.action(1,2));
//
//        PlainInterface plainInterface1 = Integer::compare;
//        System.out.println(plainInterface1.action(1,2));
//
//        List<String> list = Arrays.asList("Привет", "мир", "!");
//
//        list.stream().filter(str -> str.length() > 4).filter(str -> str.contains("о")).forEach(System.out::println);
//
//        Arrays.asList(1,2,3,4,5).stream().map(num -> num*num).forEach(System.out::println);
//        Arrays.asList(1,5,5,3,3,4,2).stream().sorted().distinct().forEach(System.out::println);

        List<User> list = Arrays.asList(new User("Павел", 25),
                new User("Аркадий", 40),
                new User("Валера", 30));

        list.stream().map(user -> new User(user.name, user.age-5)).filter(user -> user.age <= 25).forEach(System.out::println);



    }
}
