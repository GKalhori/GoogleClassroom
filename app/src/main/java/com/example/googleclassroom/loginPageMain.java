package com.example.googleclassroom;

import com.example.googleclassroom.utility.User;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class loginPageMain extends AppCompatActivity implements View.OnClickListener {

    EditText usernameLogin, passwordLogin;
    Button loginButton, signUpButton;

    public static Object input;
    public static Object output;

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_main);

        getSupportActionBar().hide();

        new MyTaskLogin().execute();

        usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

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
                infoCheck();
                loginCheck();
                break;
        }
    }

    public void infoCheck() {
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
    }

    public void loginCheck() {
        String user = usernameLogin.getText().toString();
        String pass = passwordLogin.getText().toString();
        User userLogin = new User("login", user, pass);
        output = userLogin;
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input);

        try {
            if (input.equals("notValid"))
                Toast.makeText(loginPageMain.this, "Username is not valid!", Toast.LENGTH_LONG).show();
            if (input.equals("wrongPassword"))
                Toast.makeText(loginPageMain.this, "Password is wrong!", Toast.LENGTH_LONG).show();
            if (input.equals("loggedIn")) {
                Toast.makeText(loginPageMain.this, "You logged in successfully :)", Toast.LENGTH_LONG).show();
                startActivity(new Intent(loginPageMain.this, firstPage.class));
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }
}

class MyTaskLogin extends AsyncTask<String, Void, Void> {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    @Override
    protected Void doInBackground(String... strings) {
        try {

            socket = new Socket("192.168.0.12", 8888);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() { //for sending information
            @Override
            public void run() {
                while (true) {
                    if (loginPageMain.output != null) {
                        try {
                            output.writeObject(loginPageMain.output);
                            output.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        loginPageMain.output = null;
                    }
                }
            }
        }).start();
        new Thread(new Runnable() { //for receiving information
            @Override
            public void run() {
                while (true) {
                    try {
                        loginPageMain.input = input.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return null;
    }
}
