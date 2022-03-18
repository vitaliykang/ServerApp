package com.example.serverapp;

import javafx.fxml.FXMLLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.example.serverapp.Main.controller;

public class ServerConnection {
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;

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
                        controller.updateLog(message);
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
