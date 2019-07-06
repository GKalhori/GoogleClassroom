package com.example.googleclassroom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class joinClass extends AppCompatActivity {
    private ActionBar actionBar;
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
        getSupportActionBar().setTitle("Join Class");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F3939")));
//        actionBar=getSupportActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CED0D8")));
        Toolbar toolbarjoin = findViewById(R.id.toolbarjoin);
        setSupportActionBar(toolbarjoin);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        new MyTaskJoinClass().execute();

        classCodeText = (EditText) findViewById(R.id.classCodeText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menujoin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_vertt:
                Toast.makeText(getApplicationContext(), "Let's go to About Us Page", Toast.LENGTH_LONG).show();
                startActivity(new Intent(joinClass.this, aboutUs.class));
                break;
            case R.id.join:
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
    public void joinProcess(){
        String classCode = classCodeText.getText().toString();
        output = classCode;
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input);
        try {
            if (input.equals("joined")) {
                Toast.makeText(getApplicationContext(), "You Joined Class!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(joinClass.this, classPage.class));
            }
        }
        catch (NullPointerException e)
        {
            e.getMessage();
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
