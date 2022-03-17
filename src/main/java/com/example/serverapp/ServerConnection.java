package com.example.serverapp;

import javafx.fxml.FXMLLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;
    static MainController controller;

    static {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        controller = loader.getController();
    }

    public static void launch() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            Socket socket = serverSocket.accept();

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Thread messageReceiver = new Thread(() -> {
                while (true) {
                    try {
                        String message = dataInputStream.readUTF();
//                        controller.updateLog(message);
                        System.out.printf("client: %s%n", message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            messageReceiver.start();

            System.out.println("Server started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(String message) {
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
