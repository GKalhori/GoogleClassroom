package com.example.googleclassroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginPage extends AppCompatActivity {
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
            //String user = username.getText().toString();
            //String pass = password.getText().toString();
            String user;
            if( username.getText().toString().length()== 0 )
                username.setError("Please Enter Username!");
            else if (password.getText().toString().length() == 0 )
            username.setError("Please Enter Password!");
            // pass must be longet than 5 chars
            else {
                // do async task
                username.setError(null);
                password.setError(null);
            }
            enter(username.getText().toString(), password.getText().toString());
        }
    });
    }
    public void enter(){
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.execute(username.getText().toString());

    }
}
