package com.example.googleclassroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.googleclassroom.createClass;
import com.example.googleclassroom.utility.Classes;

public class firstPage extends AppCompatActivity {

    //a list to store all the products
    List<ClassData> classList;

    //the recyclerview
    RecyclerView recyclerView;

    static ImageView imageViewBoard;
    static TextView textViewFirstclass;

    public static Object input;
    public static Object output;
    static Classes createdclass;

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        new MyTaskFirstPage().execute();

        imageViewBoard = (ImageView) findViewById(R.id.imageViewBoard);
        textViewFirstclass = (TextView) findViewById(R.id.textViewFirstclass);
//        Toolbar toolbar = findViewById(R.id.toolbar) ;
//        setSupportActionBar(toolbar);
//        android.support.v7.app.ActionBar ab = getSupportActionBar();
//       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
//       // setSupportActionBar(toolbar);
//        ab.setLogo(R.drawable.ic_launcher_background);
//        ab.setDisplayUseLogoEnabled(true);
//        ab.setDisplayShowHomeEnabled(true);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        classList = new ArrayList<>();

        if (createClass.input.equals("created")) {
            output = "addClass";
            createdclass = (Classes) firstPage.input;
        }
        //adding some items to our list
        classList.add(
                new ClassData(
                        createdclass.getClassName(),
                        "unknown", // how to know login username or register one by the entering
                        "Advanced Programming",
                        createdclass.getRoomNumber(),
                        R.drawable.b1));


        //creating recyclerview adapter
        ClassDataAdapter adapter = new ClassDataAdapter(this, classList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
        // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_vertt:
                startActivity(new Intent(firstPage.this, aboutUs.class));
                break;

            case R.id.create:
                Toast.makeText(getApplicationContext(), "Let's create class", Toast.LENGTH_LONG).show();
                startActivity(new Intent(firstPage.this, createClass.class));
                break;
            case R.id.join:
                Toast.makeText(getApplicationContext(), "Let's join class", Toast.LENGTH_LONG).show();
                startActivity(new Intent(firstPage.this, joinClass.class));
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }
}

class MyTaskFirstPage extends AsyncTask<String, Void, Void> {
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
