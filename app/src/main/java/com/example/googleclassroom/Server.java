package com.example.googleclassroom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {
    static File file;
    static ArrayList<String> usernames = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();

    public Server() {
        usernames.add("ali");
        passwords.add("111111");
        usernames.add("amir");
        passwords.add("222222");
    }

    public static void main(String[] args) throws Exception {
        file = new java.io.File("file.txt");
        ServerSocket serverSocket = new ServerSocket(1800);
        Socket clientSocket;
        PrintWriter writer = new PrintWriter(file);
        // Log.d(TAG, "main: ");
        // new MainThread().start();
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                DataInputStream severInput = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream serverOutput = new DataOutputStream(clientSocket.getOutputStream());
                // Clienthandler clienthandler = new Clienthandler(clientSocket, serverDataInputStream, serverDataOutputstrean, gameNumber, numOfClients);
                // Thread thread = new Thread(clienthandler);
                // thread.start();
            } catch (SocketException ex) {
                serverSocket.close();
            }
        }
    }
}
