package com.example.googleclassroom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class joinClass extends AppCompatActivity {
private ActionBar actionBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);
        getSupportActionBar().setTitle("Join Class");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F3939")));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.more_vertt:
                Toast.makeText(getApplicationContext(),"Let's go to About Us Page",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(joinClass.this,aboutUs.class) ;
                startActivity(intent1);
                return true;
            case R.id.join:
                Toast.makeText(getApplicationContext(),"You Joined Class!",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(joinClass.this,classPage.class) ;
                startActivity(intent2);
                return true;
            case R.id.close:
                Intent intent3 = new Intent(joinClass.this,firstPage.class) ;
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
