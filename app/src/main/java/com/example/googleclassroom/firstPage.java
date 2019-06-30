package com.example.googleclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class firstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
       // setSupportActionBar(toolbar);
        ab.setLogo(R.drawable.ic_launcher_background);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.menu,menu);
        return true ;
       // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.more_vertt:
                Toast.makeText(getApplicationContext(),"Welcome To About Us Page",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(firstPage.this,aboutUs.class) ;
                startActivity(intent);
                return true ;
          //  case R.id.add:
               // Toast.makeText(getApplicationContext(),"you choosed add!",Toast.LENGTH_LONG).show();
               // return true ;
                default:
                    return super.onOptionsItemSelected(item);

        }

    }
}
