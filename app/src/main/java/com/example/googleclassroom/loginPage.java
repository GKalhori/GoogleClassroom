package com.example.googleclassroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginPage extends AppCompatActivity {
    // main activity = login page
    EditText username,password;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = (Button) findViewById(R.id.loginButton);
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);

        login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if( username.getText().toString().length()== 0 )
                username.setError("Please Enter Username!");

            else if (password.getText().toString().length() == 0 )
            username.setError("Please Enter Password!");

            // pass must be longer than 5 chars


            else {
                // do async task
                username.setError(null);
                password.setError(null);
            }
            enter();
        }
    });
    }
    public void enter(){
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.execute(username.getText().toString(),password.getText().toString());

    }
}
