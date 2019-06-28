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

        String user_get = username.getText().toString();
        String pass_get = password.getText().toString();

        if (username.getText().toString().length() == 0)
            username.setError("Username field can't be empty!");

        if (password.getText().toString().length() == 0)
            password.setError("Password field can't be empty!");
            // if (username is taken)

        else if (password.getText().toString().length() < 6)
            password.setError("Password must me longer than 5 characters,\nPlease Enter a Valid Password!");

        else {
            username.setError(null);
            password.setError(null);
        }

        User registeredData = new User(user_get, pass_get);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpPage.this, firstPage.class));
            }
        });
    }
}