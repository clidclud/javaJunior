package hw3;

import java.io.*;

public class PersonSerialization {

    //Задание 1: Создайте класс Person с полями name и age.
    //Реализуйте сериализацию и десериализацию этого класса в файл.
    //Задание 2: Используя JPA, создайте базу данных для хранения объектов класса Person.
    //Реализуйте методы для добавления, обновления и удаления объектов Person.

    public static void serialize(Person person, String fileName) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(person);
        }
    }

    public static Person deSerialize(String fileName) throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            return (Person) ois.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("Ivan", 30);
        String fileName = "person.ser";
        try{
            serialize(person, fileName);
            System.out.println("Person сериализован в " + fileName);

            Person deserializedPerson = deSerialize(fileName);
            System.out.println("Person десериализован" + deserializedPerson);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}