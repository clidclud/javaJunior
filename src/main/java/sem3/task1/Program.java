package sem3.task1;

import java.io.*;

public class Program {
    public static void main(String[] args) throws RuntimeException, IOException, ClassNotFoundException {
        UserData user = new UserData("Станислав", 37, "secret");

        try (FileOutputStream fileOutputStream = new FileOutputStream("userdata.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(user);
            System.out.println("Объект UserData сериализован.");
        }

        try (FileInputStream fileInputStream = new FileInputStream("userdata.bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            user = (UserData)objectInputStream.readObject();
            System.out.println("Объект UserData десериализован.");
        }

        System.out.println("Имя: " + user.getName());
        System.out.println("Возраст: " + user.getAge());
        System.out.println("Пароль: (должен быть null т.к. transient)" + user.getPassword());
    }
}
