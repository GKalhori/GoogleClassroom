package com.example.googleclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class classworkPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_classwork_page);
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
            case R.id.Classess:
                Toast.makeText(getApplicationContext(),"Let's go tu main page", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(classworkPage.this,firstPage.class) ;
                startActivity(intent);
                return true ;
            case R.id.more_vertt:
                Toast.makeText(getApplicationContext(),"Let's go to About Us Page",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(classworkPage.this,aboutUs.class) ;
                startActivity(intent1);
                return true ;
            case R.id.setting:
                Toast.makeText(getApplicationContext(),"Let's go to Setting page ",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(classworkPage.this,aboutUs.class) ;
                startActivity(intent2);
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
