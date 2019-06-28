package com.example.googleclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginPage extends AppCompatActivity {
    // main activity = login page (Youtube)

    EditText username, password;
    Button login, register;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);
        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.registerButton);
        userLocalStore = new UserLocalStore(this);

        String user_get = username.getText().toString();
        String pass_get = password.getText().toString();

        if (username.getText().toString().length() == 0)
            username.setError("Username field can't be empty!");

        if (password.getText().toString().length() == 0)
            password.setError("Password field can't be empty!");

        else {
            username.setError(null);
            password.setError(null);
        }
        enter(user_get, pass_get);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                User user = new User(null, null);
//                userLocalStore.storeUserData(user);
//                userLocalStore.setUserLoggedIn(true);
//                userLocalStore.clearUserData();
//                userLocalStore.setUserLoggedIn(false);

                // if everthing in server data is ok fo to the first page
                //startActivity(new Intent(loginPage.this, firstPage.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginPage.this, SignUpPage.class));
            }
        });
    }

    public void enter(String user, String pass) {
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.execute(user, pass);
    }
}
