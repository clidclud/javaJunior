package hw5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientName;

    public Client(Socket socket, String clientName) {
        try {
            this.socket = socket;
            this.clientName = clientName;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bufferedWriter.write(clientName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println("Ошибка при создании клиента: " + e.getMessage());
            closeEverything();
        }
    }

    public void sendMessage() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything();
        }
    }

    public void listenForMessage() {
        new Thread(() -> {
            String messageFromChat;
            while (socket.isConnected()) {
                try {
                    messageFromChat = bufferedReader.readLine();
                    if (messageFromChat != null) {
                        System.out.println(messageFromChat);
                    }
                } catch (IOException e) {
                    closeEverything();
                }
            }
        }).start();
    }

    public void closeEverything() {
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии ресурсов: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите ваше имя: ");
            String name = scanner.nextLine();
            Socket socket = new Socket("localhost", 1400);
            Client client = new Client(socket, name);
            client.listenForMessage();
            client.sendMessage();
        } catch (IOException e) {
            System.err.println("Ошибка подключения к серверу: " + e.getMessage());
        }
    }
}