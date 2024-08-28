package lec3;

import java.io.*;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Character.getName(i));
        }
        serialObj(list, "ser.txt");

        ArrayList<String> list1 = null;
        list1 = (ArrayList<String>) deSerialObj("ser.txt");
        System.out.println(list1);
    }

    public static void serialObj(Object o, String file) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
    }

    public static Object deSerialObj(String file) throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }
}
