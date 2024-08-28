package lec3;

import java.io.*;
import java.util.ArrayList;

public class Main3 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //MyFCs myFCs = new MyFCs("Ivanov", "Ivan", "Ivanovich");
        //serialObj(myFCs,"ser.txt");

        MyFCs myFCs = (MyFCs) deSerialObj("ser.txt");
        System.out.println(myFCs);

    }



    public static void serialObj(Object o, String file) throws IOException {
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

    public static class MyFCs implements Serializable {

        public String lName;
        public String fName;
        public String patronymic;

        public MyFCs(String lName, String fName, String patronymic) {
            this.lName = lName;
            this.fName = fName;
            this.patronymic = patronymic;
        }

        @Override
        public String toString() {
            return String.format("%s %s. %s.",
                    lName,
                    fName.toUpperCase().charAt(0),
                    patronymic.toUpperCase().charAt(0));
        }
    }
}