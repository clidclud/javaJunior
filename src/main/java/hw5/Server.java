package hw5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        System.out.println("Сервер запущен.");
        try {
            while (!serverSocket.isClosed()) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Новый клиент подключен");
                    ClientHandler clientHandler = new ClientHandler(socket);
                    clients.add(clientHandler);
                    Thread thread = new Thread(clientHandler);
                    thread.start();
                } catch (IOException e) {
                    System.err.println("Ошибка при подключении клиента: " + e.getMessage());
                }
            }
        } finally {
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Серверный сокет закрыт.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcast(String message, ClientHandler excludeClient) {
        System.out.println(message);
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != excludeClient) {
                    client.sendMessage(message);
                }
            }
        }
    }

    public static void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Клиент удалён: " + clientHandler.getClientName());
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1400);
            Server server = new Server(serverSocket);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}