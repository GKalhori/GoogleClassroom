package com.example.googleclassroom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class signUpPageMain extends AppCompatActivity {

    EditText usernameSignUp, passwordSignUp, confirmPassword;
    Button registerButton, getImageButton;
    ImageView getImage;

    private static final int imagePickCode = 1000;
    private static final int permissionCode = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page_main);

        usernameSignUp = (EditText) findViewById(R.id.usernameSignUp);
        passwordSignUp = (EditText) findViewById(R.id.passwordSignUp);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        registerButton = (Button) findViewById(R.id.registerButton);

        getImage = (ImageView) findViewById(R.id.getImage);
        getImageButton = (Button) findViewById(R.id.getImageButton);

        // handle button click
        getImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordSignUp.getText().toString().length() == 0 && usernameSignUp.getText().toString().length() == 0 && confirmPassword.getText().toString().length() == 0) {
                    passwordSignUp.setError("Password field can't be empty!");
                    usernameSignUp.setError("Username field can't be empty!");
                    confirmPassword.setError("Confirm field can't be empty!");
                } else if (usernameSignUp.getText().toString().length() == 0)
                    usernameSignUp.setError("Username field can't be empty!");

                else if (passwordSignUp.getText().toString().length() == 0)
                    passwordSignUp.setError("Password field can't be empty!");

                else if (confirmPassword.getText().toString().length() == 0)
                    confirmPassword.setError("Confirm field can't be empty!");

                else {
                    usernameSignUp.setError(null);
                    passwordSignUp.setError(null);
                }
                if (passwordSignUp.getText().toString().length() < 6)
                    passwordSignUp.setError("Password must be longer than 5 characters");
                if (!(confirmPassword.getText().toString().equals(passwordSignUp.getText().toString())) && confirmPassword.getText().toString().length() != 0)
                    confirmPassword.setError("Password doesn't match");
                else //everything is ok
                    // check username and send information
                    startActivity(new Intent(signUpPageMain.this, firstPage.class));
            }
        });
    }

    private void pickImageFromGallery() {
        // intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, imagePickCode);
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
