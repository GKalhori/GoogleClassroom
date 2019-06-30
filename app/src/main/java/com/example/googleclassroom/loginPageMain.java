package com.example.googleclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class loginPageMain extends AppCompatActivity implements View.OnClickListener, Runnable {

    EditText usernameLogin, passwordLogin;
    Button loginButton, signUpButton;
    TextView textView;

    static ObjectOutputStream dos;
    static ObjectInputStream dis;
    static PrintWriter pw;
    static Socket socket;

    static {
        try {
            socket = new Socket("192.168.10.1", 6934);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_main);

        usernameLogin = (EditText) findViewById(R.id.usernameSignUp);
        passwordLogin = (EditText) findViewById(R.id.passwordSignUp);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        textView = (TextView) findViewById(R.id.textView);
        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpButton:
                startActivity(new Intent(loginPageMain.this, signUpPageMain.class));
                break;
            case R.id.loginButton:

                if (passwordLogin.getText().toString().length() == 0 && usernameLogin.getText().toString().length() == 0) {
                    passwordLogin.setError("Password field can't be empty!");
                    usernameLogin.setError("Username field can't be empty!");
                } else if (usernameLogin.getText().toString().length() == 0)
                    usernameLogin.setError("Username field can't be empty!");

                else if (passwordLogin.getText().toString().length() == 0)
                    passwordLogin.setError("Password field can't be empty!");

                else {
                    usernameLogin.setError(null);
                    passwordLogin.setError(null);
                }

                String user_get = usernameLogin.getText().toString();
                String pass_get = passwordLogin.getText().toString();
                try {
                    dos.writeObject(user_get);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    dos.writeObject(pass_get);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        if (!(dis.readObject() == false)) break;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Username is taken, enter another username", Toast.LENGTH_SHORT).show();
                }
                try {
                    dos.writeObject(usernameLogin.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println((String) dis.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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