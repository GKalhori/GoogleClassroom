package com.example.googleclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.concurrent.atomic.AtomicReference;

public class loginPageMain extends AppCompatActivity implements View.OnClickListener {

    EditText usernameLogin, passwordLogin;
    Button loginButton, signUpButton;
    TextView textView;

    static ObjectOutputStream dos;
    static ObjectInputStream dis;
    static PrintWriter pw;
    static Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_main);

        usernameLogin = (EditText) findViewById(R.id.usernameSignUp);
        passwordLogin = (EditText) findViewById(R.id.passwordSignUp);
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
               // try {
//                    if (loginCheck())
                        startActivity(new Intent(loginPageMain.this, MainActivity.class));
                  //  else
                        Toast.makeText(getApplicationContext(), "Username or password is wrong!", Toast.LENGTH_SHORT).show();

              //  } catch (IOException e) {
                  //  e.printStackTrace();
               // }
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
        //Log.d("infochecktag","info check runed");
    }
}