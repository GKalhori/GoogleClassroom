package com.example.googleclassroom;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    static DataOutputStream dos;
    static DataInputStream dis;
    static PrintWriter pw;
    static Socket socket;
    final static int port = 7800;
//
//    @Override
//    public void run() {
//        while (true)
//        {
//            try{
//                System.out.println((String) dis.readUTF());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        socket = new Socket("192.168.204.1", 7800);
        dos.writeUTF("Hello");
    }
    public void execute(String username,String password){

    }

    @Override
    public void run() {

    }


    //    @Override
//    protected Void doInBackground(String... voids) {
//        String username = voids[0];
//        String password = voids[1];
//        try {
//            socket = new Socket("192.168.204.1", 7800);
//            pw = new PrintWriter(socket.getOutputStream());
//            pw.write(username);
//            pw.write(password);
//            pw.flush();
//            pw.close();
//            socket.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
