package com.example.googleclassroom;

import android.content.Intent;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class classworkPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.fragment_classwork);

        FabSpeedDial fabSpeedDial = (FabSpeedDial)findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Toast.makeText(classworkPage.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
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
