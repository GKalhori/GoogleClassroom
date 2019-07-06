package com.example.googleclassroom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
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
import java.util.Random;

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

        getSupportActionBar().setTitle("Create Class");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAA8BF")));
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
                if (infoCheck())
                    createProcess();
                try {
                    firstPage.imageViewBoard.setVisibility(View.GONE);
                    firstPage.textViewFirstclass.setVisibility(View.GONE);
                    firstPage.buttonNav.setVisibility(View.GONE);
                } catch (NullPointerException e) {
                    e.getMessage();
                }
                createScroll();
                break;
            case R.id.close:
                startActivity(new Intent(createClass.this, firstPage.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void createScroll() {
        try {
            if (input.equals("created")) {
                output = "addClass";
            }

        } catch (NullPointerException e) {
            e.getMessage();
        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input);
        try {

            Classes createdclass = (Classes) input;
            int[] images = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5};
            Random rand = new Random();
            int pic = images[rand.nextInt(images.length)];
            String des = createdclass.getDescription();
            if (des.equals(null))
                des = "";
            output = "type";
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (input.equals("master")) {
                firstPage.classList.add(
                        new ClassData(
                                createdclass.getClassName(),
                                String.valueOf(createdclass.studentsNumber()),
                                des,
                                createdclass.getRoomNumber(),
                                pic));
            } else if (input.equals("student")) {
                firstPage.classList.add(
                        new ClassData(
                                createdclass.getClassName(),
                                createdclass.getProductor(),
                                des,
                                createdclass.getRoomNumber(),
                                pic));
            }
            //creating recyclerview adapter
            ClassDataAdapter adapter = new ClassDataAdapter(this, firstPage.classList);
            //setting adapter to recyclerview
            firstPage.recyclerView.setAdapter(adapter);

        } catch (ClassCastException ce) {
            ce.getCause();
        } catch (NullPointerException e) {
            e.getMessage();
        }
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
        startActivity(new Intent(createClass.this, classPage.class));
        Toast.makeText(getApplicationContext(), "Class created!", Toast.LENGTH_LONG).show();
    }

    public Boolean infoCheck() {
        if (classNameText.getText().toString().length() == 0 && roomNumberText.getText().toString().length() == 0) {
            classNameText.setError("Class name can't be empty!");
            roomNumberText.setError("Classroom number can't be empty!");
            return false;
        }
        if (roomNumberText.getText().toString().length() == 0) {
            roomNumberText.setError("Classroom number can't be empty!");
            return false;
        }
        if (classNameText.getText().toString().length() == 0) {

            classNameText.setError("Class name can't be empty!");
            return false;
        } else {
            roomNumberText.setError(null);
            classNameText.setError(null);
        }
        return true;
    }
}

class MyTaskCreateClass extends AsyncTask<String, Void, Void> {
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