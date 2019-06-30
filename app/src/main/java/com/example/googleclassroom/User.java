package com.example.googleclassroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User {
    final static int portNumber = 7800;
    ArrayList<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> index = new HashMap<>();
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        index.put(username, password);
        this.users.add(index);
        this.username = username;
        this.password = password;
    }

//    public static void main(String[] args) throws Exception {
//        Scanner input = new Scanner(System.in);
//        Socket clientSocket = new Socket("localhost", portNumber);
//        DataInputStream clientInput = new DataInputStream(clientSocket.getInputStream());
//        DataOutputStream clientOutput = new DataOutputStream(clientSocket.getOutputStream());
//        Thread sendMessage = new Thread(() -> {
//            while (true) {
//                try {
//                    String message = input.nextLine();
//                    clientOutput.writeUTF(message);
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        });
//        Thread getMessage = new Thread(() -> {
//            while (true) {
//                try {
//                    String recievedMessage = clientInput.readUTF();
//                    System.out.println(recievedMessage);
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
//        });
//        sendMessage.start();
//        getMessage.start();
//        clientInput.close();
//        clientOutput.close();
//    }
//
//    }
}