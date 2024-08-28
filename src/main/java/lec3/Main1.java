package lec3;

import java.io.*;

public class Main1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("ser.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        String s = (String) objectInputStream.readObject();
        System.out.println(s);
    }
}
