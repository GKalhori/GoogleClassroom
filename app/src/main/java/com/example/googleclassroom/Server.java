package com.example.googleclassroom;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
public class Server {
static File file ;

    public static void main(String[] args) throws Exception {
        file = new java.io.File("file.txt") ;
        PrintWriter writer = new PrintWriter(file) ;
        ArrayList<User> usernames = new ArrayList<>() ;
        ServerSocket serverSocket = new ServerSocket(1800);
        Socket clientSocket;
       // new MainThread().start();
        while (true){
            try {
                clientSocket = serverSocket.accept() ;
                DataOutputStream serverOutput = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream severInput = new DataInputStream(clientSocket.getInputStream());

            }
            catch (SocketException ex){
                serverSocket.close();
            }
        }
    }
}
