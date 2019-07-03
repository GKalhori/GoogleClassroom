package com.example.googleclassroom;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class classPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_page);

        BottomNavigationView bottomNav = findViewById(R.id.navbar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Peoplefrag()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null ;
        switch (menuItem.getItemId()){
            case R.id.people:
                selectedFragment = new Peoplefrag();
                break;
            case R.id.classwork:
                selectedFragment = new Classworkfrag();
                break;
//            case R.id.people:
//                selectedFragment = new ();
//                break;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit() ;
        return true ;
    }
};
}
