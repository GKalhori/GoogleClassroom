package com.example.googleclassroom;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends AsyncTask<String, Void, Void> {
    DataOutputStream dos;
    PrintWriter pw;
    Socket socket;

    @Override
    protected Void doInBackground(String... voids) {
        String username = voids[0];
        String password = voids[1];
        try {
            socket = new Socket("192.168.204.1", 7800);
            pw = new PrintWriter(socket.getOutputStream());
            pw.write(username);
            pw.write(password);
            pw.flush();
            pw.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
