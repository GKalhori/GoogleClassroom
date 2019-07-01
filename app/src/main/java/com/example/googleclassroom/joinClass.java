package com.example.googleclassroom;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class joinClass extends AppCompatActivity {
private ActionBar actionBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);
//        actionBar=getSupportActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CED0D8")));
        Toolbar toolbarjoin = findViewById(R.id.toolbarjoin) ;
        setSupportActionBar(toolbarjoin);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menujoin,menu);
        return true ;
    }
}
