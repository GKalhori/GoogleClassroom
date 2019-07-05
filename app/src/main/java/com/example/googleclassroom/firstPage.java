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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class firstPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<ProductModel> listData = new ArrayList<ProductModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
//        Toolbar toolbar = findViewById(R.id.toolbar) ;
//        setSupportActionBar(toolbar);


//        android.support.v7.app.ActionBar ab = getSupportActionBar();
//       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
//       // setSupportActionBar(toolbar);
//        ab.setLogo(R.drawable.ic_launcher_background);
//        ab.setDisplayUseLogoEnabled(true);
//        ab.setDisplayShowHomeEnabled(true);


        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
       // recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       // adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        // here to describe
        listData.add(new ProductModel(R.drawable.b1, "ap", "vahidi", 50));
        listData.add(new ProductModel(R.drawable.b1, "math", "alavi", 30));

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
                Toast.makeText(getApplicationContext(), "Welcome To About Us Page", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(firstPage.this, aboutUs.class);
                startActivity(intent);
                return true;
            //  case R.id.add:
            // Toast.makeText(getApplicationContext(),"you choosed add!",Toast.LENGTH_LONG).show();
            // return true ;
            case R.id.create:
                Toast.makeText(getApplicationContext(), "Let's create class", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(firstPage.this, createClass.class);
                startActivity(intent1);
                return true;
            case R.id.join:
                Toast.makeText(getApplicationContext(), "Let's join class", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(firstPage.this, joinClass.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
