package com.example.googleclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpPage extends AppCompatActivity {
    EditText username, password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);
        signUp = (Button) findViewById(R.id.loginButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.registerButton:

                        break;
                }
                String user = username.getText().toString();
                String pass = password.getText().toString();
                User registeredData = new User(user,pass);
                if (username.getText().toString().length() == 0)
                    username.setError("Please Enter Username!");

                else if (password.getText().toString().length() == 0)
                    username.setError("Please Enter Password!");

                else {
                    // do async task
                    username.setError(null);
                    password.setError(null);
                }
            }
        });
    }
}