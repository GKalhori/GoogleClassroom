package com.example.googleclassroom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.googleclassroom.utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class signUpPageMain extends AppCompatActivity implements View.OnClickListener {

    EditText usernameSignUp, passwordSignUp, confirmPassword;
    Button registerButton, getImageButton;
    ImageView getImage;

    private static final int imagePickCode = 1000;
    private static final int permissionCode = 1001;

    public static Object input;
    public static Object output;

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page_main);
        getSupportActionBar().setTitle("Sign Up");

        new MyTaskRegister().execute();

        usernameSignUp = (EditText) findViewById(R.id.usernameSignUp);
        passwordSignUp = (EditText) findViewById(R.id.passwordSignUp);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        registerButton = (Button) findViewById(R.id.registerButton);

        getImage = (ImageView) findViewById(R.id.getImage);
        getImageButton = (Button) findViewById(R.id.getImageButton);

        registerButton.setOnClickListener(this);
        getImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.registerButton:
                if (infoCheck())
                    registerCheck();
                break;
            case R.id.getImageButton:
                handleImage();
                break;
        }
    }


    public void handleImage() {
        // check runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //  // permission not granted, request it
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                // show popup for runtime permission
                requestPermissions(permissions, permissionCode);
            } else {
                // permission already granted
                pickImageFromGallery();
            }
        } else {
            // system os is less than oreo
            pickImageFromGallery();
        }
    }


    private void pickImageFromGallery() {
        // intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, imagePickCode);
    }

    public Boolean infoCheck() {
        if (passwordSignUp.getText().toString().length() == 0 && usernameSignUp.getText().toString().length() == 0 && confirmPassword.getText().toString().length() == 0) {
            passwordSignUp.setError("Password field can't be empty!");
            usernameSignUp.setError("Username field can't be empty!");
            confirmPassword.setError("Confirm field can't be empty!");
            return false;
        }
        if (usernameSignUp.getText().toString().length() == 0) {
            usernameSignUp.setError("Username field can't be empty!");
            return false;
        }

        if (passwordSignUp.getText().toString().length() == 0) {
            passwordSignUp.setError("Password field can't be empty!");
            return false;
        }

        if (confirmPassword.getText().toString().length() == 0) {
            confirmPassword.setError("Confirm field can't be empty!");
            return false;
        }
        if (passwordSignUp.getText().toString().length() != 0 && passwordSignUp.getText().toString().length() < 6) {
            passwordSignUp.setError("Password must be longer than 5 characters");
            return false;
        }
        if (!(confirmPassword.getText().toString().equals(passwordSignUp.getText().toString()))) {
            confirmPassword.setError("Password doesn't match");
            return false;
        } else {
            usernameSignUp.setError(null);
            passwordSignUp.setError(null);
            confirmPassword.setError(null);
        }
        return true;
    }

    public void registerCheck() {
        String user = usernameSignUp.getText().toString();
        String pass = passwordSignUp.getText().toString();
        String messageType = "register";
        User userRegister = new User(messageType, user, pass);

        output = userRegister;

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input);

        try {
            if (input.equals("taken"))
                Toast.makeText(signUpPageMain.this, "Username is taken!", Toast.LENGTH_LONG).show();
            if (input.equals("registered")) {
                Toast.makeText(signUpPageMain.this, "You registered successfully :)", Toast.LENGTH_LONG).show();
                startActivity(new Intent(signUpPageMain.this, firstPage.class));
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case permissionCode: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    pickImageFromGallery();
                } else {
                    // permission was denied
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT);

                }
            }
            break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == imagePickCode) {
            // set image to image view
            getImage.setImageURI(data.getData());
        }
    }


}

class MyTaskRegister extends AsyncTask<String, Void, Void> {
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
        new Thread(new Runnable() { // for sending information
            @Override
            public void run() {
                while (true) {
                    if (signUpPageMain.output != null) {
                        try {
                            output.writeObject(signUpPageMain.output);
                            output.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        signUpPageMain.output = null;
                    }
                }
            }
        }).start();
        new Thread(new Runnable() { // for receiving information
            @Override
            public void run() {
                while (true) {
                    try {
                        signUpPageMain.input = input.readObject();
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
