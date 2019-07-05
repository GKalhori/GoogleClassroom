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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.googleclassroom.utility.Classes;
import com.example.googleclassroom.firstPage;

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
        getSupportActionBar().setTitle("Create Class");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#032205")));
//         setSupportActionBar(toolbar);
//         actionBar=getSupportActionBar();
//         actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#312727")));
//         actionBar.setTitle("Create class");

//        if (classNameText.toString()!=null && roomNumberText.toString()!=null)
//        {
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.more_vertt:
                startActivity(new Intent(createClass.this, aboutUs.class));
                break;
            case R.id.createclass:
                createProcess();
                Toast.makeText(getApplicationContext(), "Class created!", Toast.LENGTH_LONG).show();
                firstPage.imageViewBoard.setVisibility(View.GONE);
                firstPage.textViewFirstclass.setVisibility(View.GONE);
                startActivity(new Intent(createClass.this, classPage.class));
                break;
            case R.id.close:
                startActivity(new Intent(createClass.this, firstPage.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void createProcess() {
        String name = classNameText.getText().toString();
        int room = Integer.parseInt(roomNumberText.getText().toString());
        String description = classDescriptionText.getText().toString();
        Classes class1 = new Classes("create", name, room, description);
        output = class1;

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input);
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
