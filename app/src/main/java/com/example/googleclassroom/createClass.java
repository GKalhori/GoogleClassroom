package com.example.googleclassroom;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class createClass extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.create) ;
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create class");
//        actionBar=getSupportActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#312727")));
//        actionBar.setTitle("Create class");

        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }
}
