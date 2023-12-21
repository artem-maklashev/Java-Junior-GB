package ru.geekbrains.chat.server;

import java.io.*;
import java.net.Socket;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ClientManager implements Runnable {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public final static Map<String, ClientManager> clients = new TreeMap<>();

    public ClientManager(Socket socket) {
        this.socket = socket;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.put(name, this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }


    }

    @Override
    public void run() {
        String massageFromClient;

        while (socket.isConnected()) {
            try {
                massageFromClient = bufferedReader.readLine();
                /*if (massageFromClient == null){
                    // для  macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }*/
                broadcastMessage(massageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private void broadcastMessage(String message) {
        if (privateMessage(message)) {
            sendPrivateMessage(message);
        } else {
            for (String key : clients.keySet()) {
                if (!key.equals(name)) {
                    ClientManager clientManager = clients.get(key);
                    sendMessage(clientManager,message);
                }
            }
        }
    }

    private void sendPrivateMessage(String message) {
        String userName = message.split(" ")[1].substring(1);
        ClientManager clientManager = clients.get(userName);
        sendMessage(clientManager, message);
    }

    /**
     * Метод определяет указан ли конкретный пользователь в сообщени при помощи "@"
     * @param message сообщение
     * @return true - если указан конкретный получатель, false - если сообщение для всех пользователей
     */
    private boolean privateMessage(String message) {
        return message.split(" ")[1].startsWith("@");
    }

    /**
     * Метод осуществляет отправку сообщения подключенному пользователю
     * @param clientManager пользователь
     * @param message сообщение
     */
    private void sendMessage(ClientManager clientManager, String message) {
        try {
            clientManager.bufferedWriter.write(message);
            clientManager.bufferedWriter.newLine();
            clientManager.bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClient() {
        clients.remove(name);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

}
