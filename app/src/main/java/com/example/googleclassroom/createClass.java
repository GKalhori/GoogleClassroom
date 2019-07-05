package com.example.googleclassroom;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.googleclassroom.utility.Classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class createClass extends AppCompatActivity {

    EditText classNameText, roomNumberText, classDescriptionText;

    public static Object input;
    public static Object output;

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        new MyTaskCreateClass().execute();

        classNameText = (EditText) findViewById(R.id.classNameText);
        roomNumberText = (EditText) findViewById(R.id.roomNumberText);
        classDescriptionText = (EditText) findViewById(R.id.classDescriptionText);

        Toolbar toolbar = (Toolbar) findViewById(R.id.create);
        getSupportActionBar().setTitle("Create class");
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        // setSupportActionBar(toolbar);
        // actionBar=getSupportActionBar();
        // actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#312727")));
        // actionBar.setTitle("Create class");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuclasswork, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_vertt:
                Toast.makeText(getApplicationContext(), "Let's go to About Us Page", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(createClass.this, aboutUs.class);
                startActivity(intent1);
                return true;
            case R.id.createclass:

                String name = classNameText.getText().toString();
                int room = Integer.parseInt(roomNumberText.getText().toString());
                String description = classDescriptionText.getText().toString();
                String action = "create";
                Classes class1 = new Classes(action, name, room, description);
                output = class1;

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(input);
                try {
                    if (input.equals("notValid")) {
                        Toast.makeText(getApplicationContext(), "Class created!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(createClass.this, classPage.class));
                    }
                } catch (NullPointerException e) {
                    e.getMessage();
                }

                return true;
            case R.id.close:
                Intent intent3 = new Intent(createClass.this, firstPage.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

class MyTaskCreateClass extends AsyncTask<String, Void, Void> {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    @Override
    protected Void doInBackground(String... strings) {
        try {

            socket = new Socket("192.168.1.52", 8888);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() { //for sending information
            @Override
            public void run() {
                while (true) {
                    if (createClass.output != null) {
                        try {
                            output.writeObject(createClass.output);
                            output.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        createClass.output = null;
                    }
                }
            }
        }).start();
        new Thread(new Runnable() { //for receiving information
            @Override
            public void run() {
                while (true) {
                    try {
                        createClass.input = input.readObject();
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
