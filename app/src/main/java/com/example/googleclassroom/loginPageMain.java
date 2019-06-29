package com.example.googleclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class loginPageMain extends AppCompatActivity implements View.OnClickListener {

    EditText usernameLogin, passwordLogin;
    Button loginButton, signUpButton;
    TextView textView;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_main);

        usernameLogin = (EditText) findViewById(R.id.usernameSignUp);
        passwordLogin = (EditText) findViewById(R.id.passwordSignUp);
        loginButton = (Button) findViewById(R.id.registerButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        textView = (TextView) findViewById(R.id.textView);
        // userLocalStore = new UserLocalStore(this);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }


    public void enter(String user, String pass) {
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.execute(user, pass);
    }

    @Override
    public void onClick(View v) {
        enter(usernameLogin.getText().toString(), passwordLogin.getText().toString()); // where is the result????
        switch (v.getId()) {
            case R.id.signUpButton:
                startActivity(new Intent(loginPageMain.this, signUpPageMain.class));
                break;
            case R.id.registerButton:

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

//                User user = new User(null, null);
//                userLocalStore.storeUserData(user);
//                userLocalStore.setUserLoggedIn(true);
//                userLocalStore.clearUserData();
//                userLocalStore.setUserLoggedIn(false);

                // if everthing in server data is ok fo to the first page
                String user_get = usernameLogin.getText().toString();
                String pass_get = passwordLogin.getText().toString();
                if (user_get.equals("a") && pass_get.equals("a")) //sample case
                    startActivity(new Intent(loginPageMain.this, firstPage.class));
                break;

        }
    }
}