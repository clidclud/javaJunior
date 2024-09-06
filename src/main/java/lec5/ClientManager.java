package lec5;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        try{
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            broadcastManager("Server: " + name + " подключился к чату.");
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String massageFromClient;
        while(socket.isConnected()){
            try{
                massageFromClient = bufferedReader.readLine();
                broadcastManager(massageFromClient);
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }

    }

    private void broadcastManager(String massageToSent){
        for(ClientManager client : clients){
            try{
                if (!client.name.equals(name)){
                    client.bufferedWriter.write(massageToSent);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClient();
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(this.bufferedWriter != null){
                this.bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void removeClient(){
        clients.remove(this);
        broadcastManager("SERVER: " + name + " покинул чат.");
    }
}
