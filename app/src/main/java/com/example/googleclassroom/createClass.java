package com.example.googleclassroom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class createClass extends AppCompatActivity {
    private ActionBar actionBar ;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.menuclasswork,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.more_vertt:
                Toast.makeText(getApplicationContext(),"Let's go to About Us Page",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(createClass.this,aboutUs.class) ;
                startActivity(intent1);
                return true;
            case R.id.createclass:
                Toast.makeText(getApplicationContext(),"You Created Class!",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(createClass.this,classPage.class) ;
                startActivity(intent2);
                return true;
            case R.id.close:
                Intent intent3 = new Intent(createClass.this,firstPage.class) ;
                startActivity(intent3);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
