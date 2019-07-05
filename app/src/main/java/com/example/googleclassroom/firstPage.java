package com.example.googleclassroom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class firstPage extends AppCompatActivity {
    private DrawerLayout mDrawerlayuot ;
    private ActionBarDrawerToggle mToggle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        getSupportActionBar().setTitle("First Page");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#032205")));
        mDrawerlayuot = (DrawerLayout) findViewById(R.id.material_drawer_account_header);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayuot,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setSupportActionBar(toolbar);


//        android.support.v7.app.ActionBar ab = getSupportActionBar();
//       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
//       // setSupportActionBar(toolbar);
//        ab.setLogo(R.drawable.ic_launcher_background);
//        ab.setDisplayUseLogoEnabled(true);
//        ab.setDisplayShowHomeEnabled(true);
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
            case R.id.create:
                Toast.makeText(getApplicationContext(),"Let's create class",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(firstPage.this,createClass.class) ;
                startActivity(intent1);
                return true ;
            case R.id.join:
                Toast.makeText(getApplicationContext(),"Let's join class",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(firstPage.this,joinClass.class) ;
                startActivity(intent2);
                return true ;

        }

        if (mToggle.onOptionsItemSelected(item)){
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }
}
