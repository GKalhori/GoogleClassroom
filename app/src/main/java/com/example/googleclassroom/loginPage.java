package com.example.googleclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginPage extends AppCompatActivity {
    // main activity = login page

    EditText username, password;
    Button login, register;
    UserLocalStore userLocalStore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = (Button) findViewById(R.id.loginButton);
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);
        register = (Button) findViewById(R.id.registerButton);
        userLocalStore = new UserLocalStore(this) ;
        login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case  R.id.loginButton:
                    User user = new User(null,null) ;
                    userLocalStore.storeUserData(user);
                    userLocalStore.setUserLoggedIn(true);



                    break;
                case R.id.registerButton:
                    startActivity(new Intent(loginPage.this,SignUpPage.class));

                    break;
            }
            String user = username.getText().toString();
            String pass = password.getText().toString();
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            if( username.getText().toString().length()== 0 )
                username.setError("Please Enter Username!");

            else if (password.getText().toString().length() == 0 )
            username.setError("Please Enter Password!");

            else if (password.getText().toString().length()<6)
                username.setError("Password Must Be Longer than 5 Characters, Please Enter a Valid Password!");

            else {
                // do async task
                username.setError(null);
                password.setError(null);
            }
            enter(user,pass);
        }
    });
        register = (Button) findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginPage.this, SignUpPage.class));
            }
        });
    }
    public void enter(String user,String pass){
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.execute(user,pass);

    }
}
