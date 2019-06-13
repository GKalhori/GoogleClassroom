package com.example.googleclassroom;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends AsyncTask<String,void,void> {
    DataOutputStream dos ;
    PrintWriter pw ;

    @Override
    protected void doInBackground(String... strings) {
        try {
            Socket s = new Socket("192.168.204.1",7800);
            pw = new PrintWriter(s.getOutputStream());

        }

        catch (Exception e){
            e.printStackTrace();
        }

    }

}
