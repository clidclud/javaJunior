package hw2;

import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {

//      Используя Reflection API, напишите программу,
//      которая выводит на экран все методы класса String.

        Class stringMethods = String.class;
        Method[] methods = stringMethods.getDeclaredMethods();
        for(Method method : methods){
            System.out.println(method.getName());
        }
    }
}
