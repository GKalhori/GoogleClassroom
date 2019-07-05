package com.example.googleclassroom;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class firstPage extends AppCompatActivity {

    //a list to store all the products
    List<ClassData> productList;

    //the recyclerview
    RecyclerView recyclerView;

    static ImageView imageViewBoard;
    static TextView textViewFirstclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
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
        productList = new ArrayList<>();


        //adding some items to our list
        productList.add(
                new ClassData(
                        "AP",
                        "Dr. Vahidi",
                        "Advanced Programming",
                        004,
                        R.drawable.b1));

        productList.add(
                new ClassData(
                        "Dis. Math",
                        "Dr. Malek",
                        "Discrete Mathematics",
                        101,
                        R.drawable.b2));

        productList.add(
                new ClassData(
                        "Physics",
                        "Dr. Gooshe",
                        "Basic Physics 2",
                        108,
                        R.drawable.b3));


        //creating recyclerview adapter
        ClassDataAdapter adapter = new ClassDataAdapter(this, productList);

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
