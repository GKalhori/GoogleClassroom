package com.example.googleclassroom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.googleclassroom.utility.Classes;
import com.example.googleclassroom.utility.Codes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class joinClass extends AppCompatActivity {

    EditText classCodeText;

    public static Object input;
    public static Object output;

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);

        new MyTaskJoinClass().execute();

        classCodeText = (EditText) findViewById(R.id.classCodeText);

        getSupportActionBar().setTitle("Join Class");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAA8BF")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menujoin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_vertt:
                startActivity(new Intent(joinClass.this, aboutUs.class));
                break;
            case R.id.joinclass:
                joinProcess();
                break;
            case R.id.close:
                startActivity(new Intent(joinClass.this, firstPage.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void joinProcess() {
        int classCode = Integer.parseInt(classCodeText.getText().toString());
        if (classCodeText.getText().toString().length() == 0) {
            classCodeText.setError("Class code can't be empty!");
        } else {
            Codes classCode1 = new Codes(classCode);
            output = classCode1;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(input);
            try {
                if (input.equals("wrongCode")) {
                    Toast.makeText(getApplicationContext(), "Wrong code!", Toast.LENGTH_LONG).show();
                } else {
                    Classes joinedClass = (Classes) input;
                    int[] images = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5};
                    Random rand = new Random();
                    int pic = images[rand.nextInt(images.length)];
                    firstPage.classList.add(
                            new ClassData(
                                    joinedClass.getClassName(),
                                    joinedClass.getProductor(),
                                    joinedClass.getDescription(),
                                    joinedClass.getRoomNumber(),
                                    pic));
                    //creating recyclerview adapter
                    ClassDataAdapter adapter = new ClassDataAdapter(this, firstPage.classList);
                    //setting adapter to recyclerview
                    firstPage.recyclerView.setAdapter(adapter);

                    Toast.makeText(getApplicationContext(), "You Joined Class!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(joinClass.this, classPage.class));
                    firstPage.imageViewBoard.setVisibility(View.GONE);
                    firstPage.textViewFirstclass.setVisibility(View.GONE);
                    firstPage.buttonNav.setVisibility(View.GONE);
                }

            } catch (NullPointerException e) {
                e.getMessage();
            }
        }
    }
}

class MyTaskJoinClass extends AsyncTask<String, Void, Void> {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    @Override
    protected Void doInBackground(String... strings) {
        try {
            socket = new Socket("192.168.43.81", 8888);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() { //for sending information
            @Override
            public void run() {
                while (true) {
                    if (joinClass.output != null) {
                        try {
                            output.writeObject(joinClass.output);
                            output.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        joinClass.output = null;
                    }
                }

            }
        }).start();
        new Thread(new Runnable() { //for receiving information
            @Override
            public void run() {
                while (true) {
                    try {
                        joinClass.input = input.readObject();
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
